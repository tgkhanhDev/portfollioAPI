package portfollio.myPortfollio.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import portfollio.myPortfollio.validator.DobConstraint;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AccountRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ username!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ username!")
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @NotNull(message = "Vui lòng nhập đầy đủ password!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ password!")
    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
    String password;

    @DobConstraint(min = 6, message = "INVALID_DOB")
    LocalDate dob;
}
