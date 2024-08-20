package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.pojos.Role;
import portfollio.myPortfollio.request.RoleRequest;
import portfollio.myPortfollio.response.RoleResponse;

import java.util.HashSet;
import java.util.List;

public interface RoleService {
    public List<Role> getRoles();

    public RoleResponse create(RoleRequest request);

    public List<RoleResponse> getAll();

    public void delete(String role);
}
