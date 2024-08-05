package portfollio.myPortfollio.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import portfollio.myPortfollio.Services.AccountService;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;

import java.util.List;
@RestController
@RequestMapping("/account")
public class AccountControllers {
    private final AccountService accountService;

    @Autowired
    public AccountControllers(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountDTO> getAllAccount() {
        return accountService.getAllAccount();
    }

//    @PostMapping("/login")
//    public AccountDTO login(@RequestBody AccountRequest accountRequest) {
//        return accountService.login(accountRequest);
//    }

    @PostMapping("/{Account}")
    public Account getAccountById(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}
