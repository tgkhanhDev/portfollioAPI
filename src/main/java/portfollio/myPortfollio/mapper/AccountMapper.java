package portfollio.myPortfollio.mapper;

import org.springframework.jmx.export.annotation.ManagedOperation;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.InheritInverseConfiguration;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(source="username" ,target = "username")
    @Mapping(source="password" ,target = "password")
    @Mapping(source="role" ,target = "role")
    AccountDTO toAccountDTO(Account account);

    @InheritInverseConfiguration(name = "toAccountDTO")
    Account toAccount(AccountDTO accountDTO);

    List<AccountDTO> toAccountDTOList(List<Account> accounts);
}
