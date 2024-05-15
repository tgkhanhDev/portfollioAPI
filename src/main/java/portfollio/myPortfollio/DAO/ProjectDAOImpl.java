package portfollio.myPortfollio.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import portfollio.myPortfollio.Entities.Project;

import java.util.List;

@Repository
public class ProjectDAOImpl implements ProjectDAO{

    private EntityManager entityManager;

    public ProjectDAOImpl() {
    }
    @Autowired
    public ProjectDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Project> findAll() {
        TypedQuery<Project> theQuery = entityManager.createQuery("FROM project", Project.class);
        return theQuery.getResultList();
    }

    @Override
    public Project findById(Integer id) {
        return entityManager.find(Project.class, id);
    }

    @Override
    public Project save(Project theProject) {
        return entityManager.merge(theProject);
    }

    @Override
    public void deleteById(Integer id) {
        Project project = entityManager.find(Project.class,id);
        entityManager.remove(project);
    }
}
