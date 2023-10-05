package tupago.back.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tupago.back.domain.AccountUser;
import tupago.back.domain.ClientConnect;

/**
 * Spring Data JPA repository for the ClientConnect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClientConnectRepository extends JpaRepository<ClientConnect, Long>, JpaSpecificationExecutor<ClientConnect> {}
