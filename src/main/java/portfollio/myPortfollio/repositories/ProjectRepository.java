package portfollio.myPortfollio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import portfollio.myPortfollio.pojos.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
