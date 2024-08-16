package portfollio.myPortfollio.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AccountService;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.response.ApiResponse;

import java.util.List;
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountControllers {
    private final AccountService accountService;

    @Autowired
    public AccountControllers(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ApiResponse<List<AccountDTO>> getAllAccount() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

//        System.out.println("Username: {}"+ authentication.getName());
//        authentication.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));

        return accountService.getAllAccount();
    }

    @GetMapping("/myInfo")
    public ApiResponse<AccountDTO> getMyInfo() {
        return accountService.getMyInfo();
    }


    @PostMapping
    public Account createAccount(@RequestBody @Valid AccountRequest account) {
        return accountService.createAccount(account);
//            return "Hello World";
    }




}
