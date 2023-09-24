package tupago.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tupago.back.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
