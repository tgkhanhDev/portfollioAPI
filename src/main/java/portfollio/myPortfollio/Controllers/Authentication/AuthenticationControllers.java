package portfollio.myPortfollio.Controllers.Authentication;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AuthenticationService;
import portfollio.myPortfollio.request.AuthenticationRequest;
import portfollio.myPortfollio.request.IntrospectRequest;
import portfollio.myPortfollio.response.ApiResponse;
import portfollio.myPortfollio.response.AuthenticationResponse;
import portfollio.myPortfollio.response.IntrospectResponse;

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




}
