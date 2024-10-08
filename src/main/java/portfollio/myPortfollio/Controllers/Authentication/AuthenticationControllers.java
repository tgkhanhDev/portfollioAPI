package portfollio.myPortfollio.Controllers.Authentication;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nimbusds.jose.JOSEException;

import portfollio.myPortfollio.Services.AuthenticationService;
import portfollio.myPortfollio.dtos.request.AuthenticationRequest;
import portfollio.myPortfollio.dtos.request.IntrospectRequest;
import portfollio.myPortfollio.dtos.request.LogoutRequest;
import portfollio.myPortfollio.dtos.request.RefreshRequest;
import portfollio.myPortfollio.dtos.response.ApiResponse;
import portfollio.myPortfollio.dtos.response.AuthenticationResponse;
import portfollio.myPortfollio.dtos.response.IntrospectResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllers {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationControllers(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse isAuthenticated = authenticationService.authenticate(authenticationRequest);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(isAuthenticated)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws JOSEException, ParseException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().data(result).build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().message("Logout success").build();
    }

    @PostMapping("/refresh")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        AuthenticationResponse isAuthenticated = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .data(isAuthenticated)
                .build();
    }
}
