package portfollio.myPortfollio.Controllers.Authentication;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AuthenticationService;
import portfollio.myPortfollio.dtos.request.AuthenticationRequest;
import portfollio.myPortfollio.dtos.request.IntrospectRequest;
import portfollio.myPortfollio.dtos.request.LogoutRequest;
import portfollio.myPortfollio.dtos.response.ApiResponse;
import portfollio.myPortfollio.dtos.response.AuthenticationResponse;
import portfollio.myPortfollio.dtos.response.IntrospectResponse;

import java.text.ParseException;

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
    public ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws JOSEException, ParseException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws JOSEException, ParseException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .message("Logout success")
                .build();
    }


}
