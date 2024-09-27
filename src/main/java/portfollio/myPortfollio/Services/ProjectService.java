package portfollio.myPortfollio.Services;

import java.util.List;

import portfollio.myPortfollio.pojos.Project;

public interface ProjectService {
    List<Project> findAll();

    Project findById(Integer id);

    Project add(Project theProject);

    Project update(Project theProject);

    void deleteById(Integer id);
}
