package portfollio.myPortfollio.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    String username;
    String password;
    Set<RoleResponse> roles;
    LocalDate dob;
}
