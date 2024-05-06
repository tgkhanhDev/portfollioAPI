package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.Entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    Project findById(Integer id);

    void add(Project theProject);

    void update(Project theProject);

    Project save(Project theProject);

    void deleteById(Integer id);
}
