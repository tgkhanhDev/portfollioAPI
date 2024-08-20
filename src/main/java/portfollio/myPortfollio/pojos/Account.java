package portfollio.myPortfollio.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Table(name = "account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    String username;
    String password;

    @ManyToMany
    Set<Role> roles;


    public Account(@NotNull(message = "Vui lòng nhập đầy đủ username!") @NotEmpty(message = "Vui lòng nhập đầy đủ username!") @Size(min = 3, message = "USERNAME_INVALID") String username, String number) {
    }
}
