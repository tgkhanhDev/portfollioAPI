package portfollio.myPortfollio.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {
    String username;
    String password;
    Set<String> role;
}
