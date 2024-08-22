package portfollio.myPortfollio.Services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import portfollio.myPortfollio.mapper.PermissionMapper;
import portfollio.myPortfollio.pojos.Permission;
import portfollio.myPortfollio.repositories.PermissionRepository;
import portfollio.myPortfollio.dtos.request.PermissionRequest;
import portfollio.myPortfollio.dtos.response.PermissionResponse;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionServiceImpl implements PermissionService{

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;



    @Override
    public List<PermissionResponse> getAllPermisson() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
    }

    @Override
    @Transactional
    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);

        return  permissionMapper.toPermissionResponse(permission);
    }

    @Override
    @Transactional
    public void delete(String name) {
        permissionRepository.deleteById(name);
    }


}
