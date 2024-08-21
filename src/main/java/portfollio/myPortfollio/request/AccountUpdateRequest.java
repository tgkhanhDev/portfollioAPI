package portfollio.myPortfollio.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import portfollio.myPortfollio.response.RoleResponse;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
//    String username;
    String password;
    Set<String> roles;
}
