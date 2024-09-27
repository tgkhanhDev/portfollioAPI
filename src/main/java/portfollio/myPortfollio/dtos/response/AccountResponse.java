package portfollio.myPortfollio.dtos.response;

import java.time.LocalDate;
import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
