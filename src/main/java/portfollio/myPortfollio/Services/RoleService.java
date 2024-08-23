package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.pojos.Role;
import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.RoleResponse;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles();

    public RoleResponse getRole(String role);

    public RoleResponse create(RoleRequest request);

    public List<RoleResponse> getAll();

    public void delete(String role);
}
