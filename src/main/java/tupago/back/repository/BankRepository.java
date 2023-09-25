package tupago.back.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tupago.back.domain.Bank;

/**
 * Spring Data JPA repository for the Bank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {}
