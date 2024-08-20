package portfollio.myPortfollio.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import portfollio.myPortfollio.dtos.AccountDTO;
import portfollio.myPortfollio.pojos.Account;
import portfollio.myPortfollio.pojos.Permission;
import portfollio.myPortfollio.request.PermissionRequest;
import portfollio.myPortfollio.response.PermissionResponse;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);

    List<PermissionResponse> toPermissionResponseList(List<Permission> permissions);
}
