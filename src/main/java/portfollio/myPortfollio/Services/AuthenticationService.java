package portfollio.myPortfollio.Services;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import portfollio.myPortfollio.Exception.AppException;
import portfollio.myPortfollio.Exception.ErrorCode;
import portfollio.myPortfollio.dtos.request.AuthenticationRequest;
import portfollio.myPortfollio.dtos.request.IntrospectRequest;
import portfollio.myPortfollio.dtos.request.LogoutRequest;
import portfollio.myPortfollio.dtos.request.RefreshRequest;
import portfollio.myPortfollio.dtos.response.AuthenticationResponse;
import portfollio.myPortfollio.dtos.response.IntrospectResponse;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.pojos.InvalidatedToken;
import portfollio.myPortfollio.repositories.AccountRepository;
import portfollio.myPortfollio.repositories.InvalidatedTokenRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthenticationService {

    AccountRepository accountRepository;

    @NotNull
    @Value("${SIGNER_KEY}")
    protected String SIGNER_KEY;

    @NotNull
    @Value("${valid-duration}")
    protected long VALID_DURATION;

    @NotNull
    @Value("${refreshable-duration}")
    protected long REFRESHABLE_DURATION;

    private final InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    public AuthenticationService(
            AccountRepository accountRepository, InvalidatedTokenRepository invalidatedTokenRepository) {
        this.accountRepository = accountRepository;
        this.invalidatedTokenRepository = invalidatedTokenRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = accountRepository
                .findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        if (!authenticated) {
            return AuthenticationResponse.builder()
                    .token("Unauthorized")
                    .code(401) // 401 Unauthorized
                    .authenticated(false)
                    .build();
        }

        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .code(200) // 200 OK
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (AppException e) {
            isValid = false;
        }

        return IntrospectResponse.builder().valid(isValid).build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {

        try {
            var signToken = verifyToken(request.getToken(), true);
            String jid = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jid).expityTime(expiryTime).build();

            invalidatedTokenRepository.save(invalidatedToken);

        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken(), true);

        String jid = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jid).expityTime(expiryTime).build();

        invalidatedTokenRepository.save(invalidatedToken);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = accountRepository
                .findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_INVALID));
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .code(200) // 200 OK
                .authenticated(true)
                .build();
    }

    private String generateToken(Account account) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("gkhanh.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.SECONDS).toEpochMilli()))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(account))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); // need an algorithm
            return jwsObject.serialize();
        } catch (JOSEException e) {
            System.out.println(("Cannot Create Token" + e));
            throw new RuntimeException(e);
        }
    }

    private String buildScope(Account account) {
        StringJoiner stringJoiner = new StringJoiner(" ");

        if (!CollectionUtils.isEmpty(account.getRoles())) {
            account.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> {
                        stringJoiner.add(permission.getName());
                    });
                }
            });
        }
        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        // Check is token correct?
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Check is token expire?
        Date expiryTime = (isRefresh)
                ? new Date(signedJWT
                        .getJWTClaimsSet()
                        .getIssueTime()
                        .toInstant()
                        .plus(REFRESHABLE_DURATION, ChronoUnit.SECONDS)
                        .toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date()))) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }
}
