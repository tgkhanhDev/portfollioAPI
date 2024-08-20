package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.pojos.Permission;
import portfollio.myPortfollio.request.PermissionRequest;
import portfollio.myPortfollio.response.ApiResponse;
import portfollio.myPortfollio.response.PermissionResponse;

import java.util.List;

public interface PermissionService {
    List<PermissionResponse> getAllPermisson();

    PermissionResponse create(PermissionRequest request);

    void delete(String name);
}
