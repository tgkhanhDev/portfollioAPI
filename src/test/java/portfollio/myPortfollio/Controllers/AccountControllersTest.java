package portfollio.myPortfollio.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import portfollio.myPortfollio.Services.AccountService;
import portfollio.myPortfollio.dtos.request.AccountRequest;
import portfollio.myPortfollio.dtos.response.AccountResponse;
import portfollio.myPortfollio.pojos.Role;
import portfollio.myPortfollio.repositories.RoleRepository;
import java.time.LocalDate;
import java.util.Set;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.properties")
public class AccountControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @MockBean
    private RoleRepository roleRepository;

    private AccountResponse accountResponse;
    private AccountRequest request;

    LocalDate dob;
    Set<Role> roles;

    @BeforeEach
    void initData() {
        dob = LocalDate.of(1990, 1, 1);
        request = AccountRequest.builder()
                .username("JohnDoe")
                .password("12345678")
                .dob(dob)
                .build();


        Role roleGuest = roleRepository.getByName("GUEST");
        System.out.println("RoleGuest:::" + roleGuest);
//        roles.add(roleGuest);

        accountResponse = AccountResponse.builder()
                .username("JohnDoe")
                .dob(dob)
//                .roles(roles.stream().map(roleMapper::toRoleResponse).collect(Collectors.toSet()))
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        //Will mock data when access accountService.createAccount
        Mockito.when(accountService.createAccount(ArgumentMatchers.any())).thenReturn(accountResponse);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/account")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
        ;
        // THEN
    }

    @Test
    void createUser_usernameInValid_failed() throws Exception {
        // GIVEN
        request.setUsername("g7");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(request);

        //Will mock data when access accountService.createAccount
        Mockito.when(accountService.createAccount(ArgumentMatchers.any())).thenReturn(accountResponse);

        // WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/account")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1002"))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Độ dài tên phải tối thiểu 3 ký tự!"))
        ;
        // THEN
    }
}
