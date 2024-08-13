package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;

import java.util.List;

public interface AccountService {

    public List<AccountDTO> getAllAccount();

    public AccountDTO login(AccountRequest accountRequest);
    public AccountDTO updateAccount(Account account);

    public Account createAccount(Account account) throws Exception;
}
