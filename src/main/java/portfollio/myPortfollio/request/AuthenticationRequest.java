package portfollio.myPortfollio.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotNull(message = "Vui lòng nhập đầy đủ username!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ username!")
    String username;

    @NotNull(message = "Vui lòng nhập đầy đủ password!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ password!")
    String password;

}
