package portfollio.myPortfollio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfollio.myPortfollio.pojos.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {
}
