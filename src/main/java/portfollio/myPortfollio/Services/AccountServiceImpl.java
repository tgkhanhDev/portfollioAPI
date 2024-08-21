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
import portfollio.myPortfollio.Exception.AppException;
import portfollio.myPortfollio.Exception.ErrorCode;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.mapper.AccountMapper;
import portfollio.myPortfollio.mapper.RoleMapper;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.repositories.AccountRepository;
import portfollio.myPortfollio.repositories.RoleRepository;
import portfollio.myPortfollio.request.AccountRequest;
import portfollio.myPortfollio.request.AccountUpdateRequest;
import portfollio.myPortfollio.response.AccountResponse;
import portfollio.myPortfollio.response.ApiResponse;

import java.util.HashSet;
import java.util.List;
@Service
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final RoleRepository roleRepository;
    //encode
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountResponse> getAllAccount() {
        log.info("In method get Accounts!!!!!!!!!!!!!!");
        List<Account> acc = accountRepository.findAll().stream().toList();
        return accountMapper.toAccountResponseList(acc);
    }

    @Override
    public AccountResponse login(AccountRequest accountRequest) {
        //get account from request
        Account account = accountRepository.getReferenceById(accountRequest.getUsername());
        //save back to account
        account.setPassword(passwordEncoder.encode(accountRequest.getPassword()));
        return accountMapper.toAccountResponse(account);
    }

    @Override
    @Transactional
    public AccountResponse updateAccount(String accountUsername, AccountUpdateRequest accountUpdateRequest) {
        Account account = accountRepository.findByUsername(accountUsername)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_INVALID));

        accountMapper.updateAccount(account, accountUpdateRequest); //modify account

        account.setPassword(passwordEncoder.encode(accountUpdateRequest.getPassword()));
        var roles = roleRepository.findAllById(accountUpdateRequest.getRoles());

        if(roles.stream().count() == 0){
//            System.out.println("DEO CO: "+ roles.stream().count());
            throw new AppException(ErrorCode.ROLE_INVALID);
        }

        account.setRoles(new HashSet<>(roles));
        return accountMapper.toAccountResponse(accountRepository.save(account));

    }

    @Override
    @Transactional
    public AccountResponse createAccount(AccountRequest request) {
        if (accountRepository.existsByUsername(request.getUsername())) {
//            throw new AppException(ErrorCode.USER_EXISTED);
            throw new AppException(ErrorCode.USER_EXISTED);
//            return null;
        }

        Account account = new Account(request.getUsername(), "123");

        account.setPassword(passwordEncoder.encode(request.getPassword()));
//        account.setRole("GUEST");
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
//    @PreAuthorize("hasRole('ADMIN')")
    public AccountResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        Account account = accountRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
        System.out.println("My Info: "+ account.toString());
        return accountMapper.toAccountResponse(account);
    }

    /*
    * Post Authorize will always run everything in that method despite all authorize but not show output
    * Post Authorize useful when you want to check if user the response match something (EX: show detail as long as authentication username match response username)
    * */
    @Override
    @PostAuthorize("returnObject.data.username == authentication.name")
    public AccountResponse getAccount(String name) {
//        var authentication = SecurityContextHolder.getContext().getAuthentication();
//        log.info("adasdsada: "+ authentication.getName());
        Account account = accountRepository.findById(name).orElseThrow(() -> new RuntimeException("Account not found"));
        return accountMapper.toAccountResponse(account);
    }

}
