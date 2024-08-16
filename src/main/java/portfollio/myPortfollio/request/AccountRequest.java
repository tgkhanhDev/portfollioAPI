package portfollio.myPortfollio.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountRequest {

    @NotNull(message = "Vui lòng nhập đầy đủ username!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ username!")
    @Size(min = 3, message = "Độ dài tên phải tối thiểu 3 ký tự")
    String username;

    @NotNull(message = "Vui lòng nhập đầy đủ password!")
    @NotEmpty(message = "Vui lòng nhập đầy đủ password!")
    @Size(min = 8, max = 20, message = "Độ dài mật khẩu phải bằng 8 hoặc trên 20")
    String password;
}
