package tupago.back.service;

import java.util.List;
import java.util.Optional;
import tupago.back.service.dto.AccountUserDTO;

/**
 * Service Interface for managing {@link tupago.back.domain.AccountUser}.
 */
public interface AccountUserService {
    /**
     * Save a accountUser.
     *
     * @param accountUserDTO the entity to save.
     * @return the persisted entity.
     */
    AccountUserDTO save(AccountUserDTO accountUserDTO);

    /**
     * Updates a accountUser.
     *
     * @param accountUserDTO the entity to update.
     * @return the persisted entity.
     */
    AccountUserDTO update(AccountUserDTO accountUserDTO);

    /**
     * Partially updates a accountUser.
     *
     * @param accountUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AccountUserDTO> partialUpdate(AccountUserDTO accountUserDTO);

    /**
     * Get all the accountUsers.
     *
     * @return the list of entities.
     */
    List<AccountUserDTO> findAll();

    /**
     * Get the "id" accountUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AccountUserDTO> findOne(Long id);

    /**
     * Delete the "id" accountUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
