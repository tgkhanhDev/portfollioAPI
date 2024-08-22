package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.dtos.request.PermissionRequest;
import portfollio.myPortfollio.dtos.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAllPermisson();

    PermissionResponse create(PermissionRequest request);

    void delete(String name);
}
