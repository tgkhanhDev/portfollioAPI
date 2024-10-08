package portfollio.myPortfollio.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import portfollio.myPortfollio.pojos.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    //    boolean existByUsername(String username);
    Optional<Account> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);
}
