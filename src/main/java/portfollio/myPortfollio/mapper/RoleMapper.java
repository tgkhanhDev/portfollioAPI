package portfollio.myPortfollio.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.RoleResponse;
import portfollio.myPortfollio.pojos.Role;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
