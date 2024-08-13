package portfollio.myPortfollio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.enums.Role;
import portfollio.myPortfollio.mapper.AccountMapper;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.repositories.AccountRepository;
import portfollio.myPortfollio.request.AccountRequest;

import java.util.HashSet;
import java.util.List;
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }
    @Override
    public List<AccountDTO> getAllAccount() {
        List<Account> acc = accountRepository.findAll();
        return accountMapper.toAccountDTOList(accountRepository.findAll());
    }

    @Override
    public AccountDTO login(AccountRequest accountRequest) {
        //get account from request
        Account account = accountRepository.getReferenceById(accountRequest.getUsername());

        //encode
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

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
    public Account createAccount(Account account) throws Exception {
        if(accountRepository.existsByUsername(account.getUsername())) {
            throw new Exception("Username already exists");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.GUEST.name());

        account.setRole(roles);



        return accountRepository.save(account);
    }
}
