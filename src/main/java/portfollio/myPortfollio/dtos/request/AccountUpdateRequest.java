package portfollio.myPortfollio.dtos.request;

import java.time.LocalDate;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequest {
    //    String username;
    String password;
    Set<String> roles;
    LocalDate dob;
}
