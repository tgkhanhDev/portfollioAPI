package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.response.ApiResponse;

import java.util.List;

public interface AccountService {

    public ApiResponse<List<AccountDTO>> getAllAccount();

    public AccountDTO login(AccountRequest accountRequest);
    public AccountDTO updateAccount(Account account);

    public Account createAccount(AccountRequest account);

    public ApiResponse<AccountDTO> getAccount(String id);

    public ApiResponse<AccountDTO> getMyInfo();
}
