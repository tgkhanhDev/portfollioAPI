package portfollio.myPortfollio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.mapper.AccountMapper;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.repositories.AccountRepository;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.response.ApiResponse;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    //encode
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public ApiResponse<List<AccountDTO>> getAllAccount() {
        List<Account> acc = accountRepository.findAll();
        return ApiResponse.<List<AccountDTO>>builder()
                .code("200")
                .message("success")
                .data(accountMapper.toAccountDTOList(acc))
                .build();
    }

    @Override
    public AccountDTO login(AccountRequest accountRequest) {
        //get account from request
        Account account = accountRepository.getReferenceById(accountRequest.getUsername());


        //save back to account
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));

        return accountMapper.toAccountDTO(account);
    }

    @Override
    @Transactional
    public AccountDTO updateAccount(Account account) {
        return accountMapper.toAccountDTO(accountRepository.save(account));
    }

    @Override
    @Transactional
    public Account createAccount(Account request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
//            throw new AppException(ErrorCode.USER_EXISTED);
            return null;
        }

        Account account = new Account(request.getUsername(),"","");

        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole("GUEST");
        return accountRepository.save(account);
    }
}
