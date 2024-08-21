package portfollio.myPortfollio.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import portfollio.myPortfollio.dtos.RoleDTO;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponse {
    String username;
    String password;
    Set<RoleResponse> roles;
}
