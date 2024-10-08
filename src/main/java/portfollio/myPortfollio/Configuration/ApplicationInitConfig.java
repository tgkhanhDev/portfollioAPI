package portfollio.myPortfollio.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.repositories.AccountRepository;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        return args -> {
            if (accountRepository.findByUsername("administrator").isEmpty()) {
                Account account = Account.builder()
                        .username("administrator")
                        .password(passwordEncoder.encode("admin"))
                        //                        .roles(new Role("ADMIN","abc"))
                        .build();
                accountRepository.save(account);
                log.warn("administrator user has been created with default password: admin, please change it later");
            }
        };
    }
}
