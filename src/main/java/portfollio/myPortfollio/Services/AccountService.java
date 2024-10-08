package portfollio.myPortfollio.Services;

import java.util.List;

import portfollio.myPortfollio.dtos.request.AccountRequest;
import portfollio.myPortfollio.dtos.request.AccountUpdateRequest;
import portfollio.myPortfollio.dtos.response.AccountResponse;

public interface AccountService {

    public List<AccountResponse> getAllAccount();

    public AccountResponse login(AccountRequest accountRequest);

    public AccountResponse updateAccount(String accountUsername, AccountUpdateRequest accountUpdateRequest);

    public AccountResponse createAccount(AccountRequest account);

    void DeleteAccount(AccountRequest account);

    public AccountResponse getAccount(String id);

    public AccountResponse getMyInfo();
}
