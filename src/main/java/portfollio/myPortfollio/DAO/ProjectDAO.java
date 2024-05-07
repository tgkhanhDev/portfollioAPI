package portfollio.myPortfollio.DAO;

import portfollio.myPortfollio.Entities.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> findAll();

    Project findById(Integer id);

    Project save(Project theProject);

    void deleteById(Integer id);
}
