package portfollio.myPortfollio.Services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
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
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<AccountDTO>> getAllAccount() {
        log.info("In method get Accounts!!!!!!!!!!!!!!");
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
    public Account createAccount(AccountRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
//            throw new AppException(ErrorCode.USER_EXISTED);
            throw new RuntimeException("User existed");
//            return null;
        }

        Account account = new Account(request.getUsername(),"","");

        account.setPassword(passwordEncoder.encode(request.getPassword()));
        account.setRole("GUEST");
        return accountRepository.save(account);
    }

    @Override
    public ApiResponse<AccountDTO> getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not exist"));

        return ApiResponse.<AccountDTO>builder()
                .message("Get My Info Success")
                .code("200")
                .data(accountMapper.toAccountDTO(account))
                .build();
    }

    /*
    * Post Authorize will always run everything in that method despite all authorize but not show output
    * Post Authorize useful when you want to check if user the response match something (EX: show detail as long as authentication username match response username)
    * */
    @Override
    @PostAuthorize("returnObject.data.username == authentication.name")
    public ApiResponse<AccountDTO> getAccount(String name) {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("adasdsada: "+ authentication.getName());
        Account account = accountRepository.findById(name).orElseThrow(() -> new RuntimeException("Account not found"));
        return ApiResponse.<AccountDTO>builder()
                .message("Get Detail Success")
                .code("200")
                .data(accountMapper.toAccountDTO(account))
                .build();
    }

}
