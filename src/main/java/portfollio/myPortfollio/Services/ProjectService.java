package portfollio.myPortfollio.Services;

import portfollio.myPortfollio.pojos.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findAll();

    Project findById(Integer id);

    Project add(Project theProject);

    Project update(Project theProject);

    void deleteById(Integer id);
}
