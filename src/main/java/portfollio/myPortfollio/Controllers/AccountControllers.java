package portfollio.myPortfollio.Controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AccountService;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.request.AccountUpdateRequest;
import portfollio.myPortfollio.response.AccountResponse;
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
    public ApiResponse<List<AccountResponse>> getAllAccount() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Username: {}"+ authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> System.out.println(grantedAuthority.getAuthority()));
//
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.getAllAccount())
                .code("200")
                .message("Get list account success")
                .build();
    }

    @GetMapping("/myInfo")
    public ApiResponse<AccountResponse> getMyInfo() {

        return ApiResponse.<AccountResponse>builder()
                .data(accountService.getMyInfo())
                .code("200")
                .message("Get list account success")
                .build();
    }

    @PostMapping
    public ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountRequest account) {
        return ApiResponse.<AccountResponse>builder()
                .code("200")
                .message("Create account success")
                .data(accountService.createAccount(account))
                .build();
    }

    @PutMapping("/update/{username}")
    public ApiResponse<AccountResponse> updateAccount(@PathVariable String username, @RequestBody AccountUpdateRequest account) {
        return ApiResponse.<AccountResponse>builder()
                .code("200")
                .message("Update account success")
                .data(accountService.updateAccount(username, account))
                .build();
    }

}
