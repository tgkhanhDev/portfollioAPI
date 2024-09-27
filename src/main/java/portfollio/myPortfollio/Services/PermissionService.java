package portfollio.myPortfollio.Services;

import java.util.List;

import portfollio.myPortfollio.dtos.request.PermissionRequest;
import portfollio.myPortfollio.dtos.response.PermissionResponse;

public interface PermissionService {
    List<PermissionResponse> getAllPermisson();

    PermissionResponse create(PermissionRequest request);

    void delete(String name);
}
