package portfollio.myPortfollio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfollio.myPortfollio.pojos.Permission;
import portfollio.myPortfollio.pojos.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>{
    Role getByName(String name);
}
