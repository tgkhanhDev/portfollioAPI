package portfollio.myPortfollio.mapper;

import org.mapstruct.*;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.dtos.request.AccountRequest;
import portfollio.myPortfollio.dtos.request.AccountUpdateRequest;
import portfollio.myPortfollio.dtos.response.AccountResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    @Mapping(source="username" ,target = "username")
    @Mapping(source="password" ,target = "password")
    Account toAccount(AccountRequest accountRequest);

    @Mapping(source="username" ,target = "username")
    @Mapping(source="password" ,target = "password")
    AccountResponse toAccountResponse(Account account);

    List<AccountResponse> toAccountResponseList(List<Account> accounts);

    //This will modify but remain roles the same
    @Mapping(target = "roles", ignore = true)
    void updateAccount(@MappingTarget Account user, AccountUpdateRequest request);


}
