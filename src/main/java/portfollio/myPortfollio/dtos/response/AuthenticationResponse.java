package portfollio.myPortfollio.dtos.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
//    @NotNull(message = "Vui lòng nhập đầy đủ username!")
//    @NotEmpty(message = "Vui lòng nhập đầy đủ username!")
//    String username;
//
//    @NotNull(message = "Vui lòng nhập đầy đủ password!")
//    @NotEmpty(message = "Vui lòng nhập đầy đủ password!")
//    String password;

    @NotNull
    @NotEmpty
    String token;
    int code;
    boolean authenticated;

}
