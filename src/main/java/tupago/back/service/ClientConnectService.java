package tupago.back.service;

import java.util.List;
import java.util.Optional;
import tupago.back.service.dto.ClientConnectDTO;

/**
 * Service Interface for managing {@link tupago.back.domain.ClientConnect}.
 */
public interface ClientConnectService {
    /**
     * Save a clientConnect.
     *
     * @param clientConnectDTO the entity to save.
     * @return the persisted entity.
     */
    ClientConnectDTO save(ClientConnectDTO clientConnectDTO);

    /**
     * Updates a clientConnect.
     *
     * @param clientConnectDTO the entity to update.
     * @return the persisted entity.
     */
    ClientConnectDTO update(ClientConnectDTO clientConnectDTO);

    /**
     * Partially updates a clientConnect.
     *
     * @param clientConnectDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ClientConnectDTO> partialUpdate(ClientConnectDTO clientConnectDTO);

    /**
     * Get all the clientConnects.
     *
     * @return the list of entities.
     */
    List<ClientConnectDTO> findAll();

    /**
     * Get the "id" clientConnect.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ClientConnectDTO> findOne(Long id);

    /**
     * Delete the "id" clientConnect.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
