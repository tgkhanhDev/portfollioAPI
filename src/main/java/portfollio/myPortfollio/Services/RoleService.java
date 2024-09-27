package portfollio.myPortfollio.Services;

import java.util.List;

import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.RoleResponse;
import portfollio.myPortfollio.pojos.Role;

public interface RoleService {
    public List<Role> getRoles();

    public RoleResponse getRole(String role);

    public RoleResponse create(RoleRequest request);

    public List<RoleResponse> getAll();

    public void delete(String role);
}
