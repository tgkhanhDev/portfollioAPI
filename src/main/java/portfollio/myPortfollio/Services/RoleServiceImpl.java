package portfollio.myPortfollio.Services;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import portfollio.myPortfollio.Exception.AppException;
import portfollio.myPortfollio.Exception.ErrorCode;
import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.RoleResponse;
import portfollio.myPortfollio.mapper.RoleMapper;
import portfollio.myPortfollio.pojos.Role;
import portfollio.myPortfollio.repositories.PermissionRepository;
import portfollio.myPortfollio.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    PermissionRepository permissionRepository;
    RoleMapper roleMapper;

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public RoleResponse getRole(String name) {
        Role role = roleRepository.getByName(name);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public RoleResponse create(RoleRequest request) {

        var role = roleMapper.toRole(request);

        var permissions = permissionRepository.findAllById(request.getPermissions());

        if (permissions.stream().count() == 0) {
            throw new AppException(ErrorCode.ROLE_INVALID);
        }

        role.setPermissions(new HashSet<>(permissions));

        role = roleRepository.save(role);

        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        var roles = roleRepository.findAll();

        return roles.stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String role) {
        roleRepository.deleteById(role);
    }
}
