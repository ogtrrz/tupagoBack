package tupago.back.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tupago.back.domain.AccountUser;
import tupago.back.domain.Transaction;

/**
 * Spring Data JPA repository for the Transaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {}
