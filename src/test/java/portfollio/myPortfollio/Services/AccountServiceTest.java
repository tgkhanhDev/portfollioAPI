package portfollio.myPortfollio.Services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import portfollio.myPortfollio.Exception.AppException;
import portfollio.myPortfollio.dtos.request.AccountRequest;
import portfollio.myPortfollio.dtos.response.AccountResponse;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.repositories.AccountRepository;

@SpringBootTest
@TestPropertySource("/test.properties")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    private AccountResponse accountResponse;
    private AccountRequest request;
    private Account account;
    private LocalDate dob;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = AccountRequest.builder()
                .username("JohnDoe")
                .password("12345678")
                .dob(dob)
                .build();

        //        Role roleGuest = roleRepository.getByName("GUEST");
        //        System.out.println("RoleGuest:::" + roleGuest);
        //        roles.add(roleGuest);

        accountResponse = AccountResponse.builder()
                .username("JohnDoe")
                .dob(dob)
                //                .roles(roles.stream().map(roleMapper::toRoleResponse).collect(Collectors.toSet()))
                .build();

        account = account.builder().username("JohnDoe").dob(dob).build();
    }

    @Test
    //    @WithMockUser(roles = "GUEST")
    void createAccount_validRequest_success() {
        //        // GIVEN
        //        Mockito.when(accountRepository.existsByUsername(Mockito.anyString())).thenReturn(false);
        //        Mockito.when(accountRepository.save(Mockito.any())).thenReturn(account);
        //        // WHEN
        //        var response = accountService.createAccount(request);
        //
        //        // THEN
        //        Assertions.assertThat(response.getUsername()).isEqualTo("JohnDoe");

    }

    @Test
    void createUser_accountExisted_fail() {
        // GIVEN
        Mockito.when(accountRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
        // WHEN
        var exception = assertThrows(AppException.class, () -> accountService.createAccount(request));
        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1001);
    }

    //    @Test
    //    void getMyInfo_userNotFound_error() {
    //        //Given
    //        Mockito.when(accountRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.empty());
    //        // WHEN
    //        var exception = assertThrows(AppException.class, () -> accountService.getMyInfo());
    //        // THEN
    //        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1006);
    //    }
}
