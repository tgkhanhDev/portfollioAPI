package portfollio.myPortfollio.Services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import portfollio.myPortfollio.repositories.AccountRepository;
import portfollio.myPortfollio.request.AuthenticationRequest;
import portfollio.myPortfollio.response.ApiResponse;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationService {
    AccountRepository accountRepository;

    @Autowired
    public AuthenticationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ApiResponse<Boolean> authenticate(AuthenticationRequest authenticationRequest) {
        var user = accountRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        PasswordEncoder passwordEncoder= new BCryptPasswordEncoder(10);

        if(!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            return ApiResponse.<Boolean>builder()
                    .data(false)
                    .code("401")
                    .message("Login Failed")
                    .build();
        }else{
            return ApiResponse.<Boolean>builder()
                    .data(passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
                    .code("200")
                    .message("Login Success")
                    .build();
        }
    }
}
