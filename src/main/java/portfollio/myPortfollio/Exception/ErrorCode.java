package portfollio.myPortfollio.Exception;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001, "User already existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002, "Độ dài tên phải tối thiểu 3 ký tự!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1002, "Độ dài mật khẩu phải bằng 8 hoặc trên 20!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated, Your TOKEN may expired!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You does not have permission", HttpStatus.FORBIDDEN),
    ROLE_INVALID(1007, "Role invalid", HttpStatus.BAD_REQUEST),
    PERMISSION_INVALID(1007, "Permission invalid", HttpStatus.BAD_REQUEST);

//    USER_NOT_EXISTED("USER_NOT_EXISTED", "User not existed"),
//    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal server error"),
//    UNAUTHORIZED("UNAUTHORIZED", "Unauthorized"),

    int code;
    String message;
    HttpStatusCode statusCode;

}
