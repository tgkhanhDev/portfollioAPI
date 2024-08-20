package portfollio.myPortfollio.dtos;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {
    String username;
    String password;
//    Set<RoleDTO> roles;
}
