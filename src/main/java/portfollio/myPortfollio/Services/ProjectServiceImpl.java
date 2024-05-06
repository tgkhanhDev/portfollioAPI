package portfollio.myPortfollio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfollio.myPortfollio.DAO.ProjectDAO;
import portfollio.myPortfollio.Entities.Project;

import java.util.List;

@Repository
public class ProjectServiceImpl implements ProjectService {

    ProjectDAO projectDAO;

    @Autowired
    public ProjectServiceImpl(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    @Override
    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    @Override
    public Project findById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public void add(Project theProject) {

    }

    @Override
    @Transactional
    public void update(Project theProject) {

    }

    @Override
    public Project save(Project theProject) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
