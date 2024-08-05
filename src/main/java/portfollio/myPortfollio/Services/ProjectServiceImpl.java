package portfollio.myPortfollio.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import portfollio.myPortfollio.pojos.Project;
import portfollio.myPortfollio.repositories.ProjectRepository;

import java.util.List;

@Repository
public class ProjectServiceImpl implements ProjectService {

//    ProjectDAO projectDAO;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Integer id) {
        return projectRepository.getReferenceById(id);
        //findById: can use methods like isPresent(), ifPresent(), or orElse() to handle the presence or absence of the entity;
        //getReferenceById: it returns the entity if it exists, otherwise it throws an exception
    }

    @Override
    @Transactional
    public Project add(Project theProject) {
        theProject.setProjectID(0);
        return projectRepository.save(theProject);
    }

    @Override
    @Transactional
    public Project update(Project theProject) {
        return projectRepository.save(theProject);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        projectRepository.deleteById(id);
    }
}
