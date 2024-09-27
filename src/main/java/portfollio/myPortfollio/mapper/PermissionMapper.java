package portfollio.myPortfollio.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import portfollio.myPortfollio.dtos.request.PermissionRequest;
import portfollio.myPortfollio.dtos.response.PermissionResponse;
import portfollio.myPortfollio.pojos.Permission;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

    Permission toPermission(PermissionRequest permissionRequest);

    PermissionResponse toPermissionResponse(Permission permission);

    List<PermissionResponse> toPermissionResponseList(List<Permission> permissions);
}
