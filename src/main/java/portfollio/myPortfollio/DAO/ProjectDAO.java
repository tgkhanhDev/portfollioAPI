package portfollio.myPortfollio.DAO;

import portfollio.myPortfollio.Entities.Project;

import java.util.List;

public interface ProjectDAO {
    List<Project> findAll();

    Project findById(Integer id);

    void add(Project theProject);

    void update(Project theProject);

    Project save(Project theProject);

    void deleteById(Integer id);
}
