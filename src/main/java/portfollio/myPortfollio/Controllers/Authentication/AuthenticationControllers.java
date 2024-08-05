package portfollio.myPortfollio.Controllers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AuthenticationService;
import portfollio.myPortfollio.request.AuthenticationRequest;
import portfollio.myPortfollio.response.ApiResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllers {

    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationControllers(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ApiResponse<Boolean> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        ApiResponse<Boolean> isAuthenticated = authenticationService.authenticate(authenticationRequest);
        return isAuthenticated;
    }


}
