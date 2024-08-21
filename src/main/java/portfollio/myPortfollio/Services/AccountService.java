package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.request.AccountUpdateRequest;
import portfollio.myPortfollio.response.AccountResponse;
import portfollio.myPortfollio.response.ApiResponse;

import java.util.List;

public interface AccountService {

    public List<AccountResponse> getAllAccount();

    public AccountResponse login(AccountRequest accountRequest);
    public AccountResponse updateAccount(String accountUsername, AccountUpdateRequest accountUpdateRequest);

    public AccountResponse createAccount(AccountRequest account);

    public AccountResponse getAccount(String id);

    public AccountResponse getMyInfo();
}
