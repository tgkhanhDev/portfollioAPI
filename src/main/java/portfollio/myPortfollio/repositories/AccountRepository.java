package portfollio.myPortfollio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import portfollio.myPortfollio.pojos.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

}
