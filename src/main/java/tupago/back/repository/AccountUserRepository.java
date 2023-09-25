package tupago.back.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tupago.back.domain.AccountUser;

/**
 * Spring Data JPA repository for the AccountUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Long> {}
