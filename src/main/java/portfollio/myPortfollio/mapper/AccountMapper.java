package portfollio.myPortfollio.mapper;

import java.util.List;

import org.mapstruct.*;

import portfollio.myPortfollio.dtos.request.AccountRequest;
import portfollio.myPortfollio.dtos.request.AccountUpdateRequest;
import portfollio.myPortfollio.dtos.response.AccountResponse;
import portfollio.myPortfollio.pojos.Account;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    Account toAccount(AccountRequest accountRequest);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> accounts);

    // This will modify but remain roles the same
    @Mapping(target = "roles", ignore = true)
    void updateAccount(@MappingTarget Account user, AccountUpdateRequest request);
}
