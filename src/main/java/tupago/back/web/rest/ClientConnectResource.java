package tupago.back.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tupago.back.repository.ClientConnectRepository;
import tupago.back.service.ClientConnectService;
import tupago.back.service.dto.ClientConnectDTO;
import tupago.back.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tupago.back.domain.ClientConnect}.
 */
@RestController
@RequestMapping("/api")
public class ClientConnectResource {

    private final Logger log = LoggerFactory.getLogger(ClientConnectResource.class);

    private static final String ENTITY_NAME = "clientConnect";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientConnectService clientConnectService;

    private final ClientConnectRepository clientConnectRepository;

    public ClientConnectResource(ClientConnectService clientConnectService, ClientConnectRepository clientConnectRepository) {
        this.clientConnectService = clientConnectService;
        this.clientConnectRepository = clientConnectRepository;
    }

    /**
     * {@code POST  /client-connects} : Create a new clientConnect.
     *
     * @param clientConnectDTO the clientConnectDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientConnectDTO, or with status {@code 400 (Bad Request)} if the clientConnect has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/client-connects")
    public ResponseEntity<ClientConnectDTO> createClientConnect(@Valid @RequestBody ClientConnectDTO clientConnectDTO)
        throws URISyntaxException {
        log.debug("REST request to save ClientConnect : {}", clientConnectDTO);
        if (clientConnectDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientConnect cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClientConnectDTO result = clientConnectService.save(clientConnectDTO);
        return ResponseEntity
            .created(new URI("/api/client-connects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /client-connects/:id} : Updates an existing clientConnect.
     *
     * @param id the id of the clientConnectDTO to save.
     * @param clientConnectDTO the clientConnectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientConnectDTO,
     * or with status {@code 400 (Bad Request)} if the clientConnectDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientConnectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/client-connects/{id}")
    public ResponseEntity<ClientConnectDTO> updateClientConnect(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ClientConnectDTO clientConnectDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClientConnect : {}, {}", id, clientConnectDTO);
        if (clientConnectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientConnectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientConnectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ClientConnectDTO result = clientConnectService.update(clientConnectDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientConnectDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /client-connects/:id} : Partial updates given fields of an existing clientConnect, field will ignore if it is null
     *
     * @param id the id of the clientConnectDTO to save.
     * @param clientConnectDTO the clientConnectDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientConnectDTO,
     * or with status {@code 400 (Bad Request)} if the clientConnectDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clientConnectDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientConnectDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/client-connects/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ClientConnectDTO> partialUpdateClientConnect(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ClientConnectDTO clientConnectDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientConnect partially : {}, {}", id, clientConnectDTO);
        if (clientConnectDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientConnectDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!clientConnectRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ClientConnectDTO> result = clientConnectService.partialUpdate(clientConnectDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, clientConnectDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /client-connects} : get all the clientConnects.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientConnects in body.
     */
    @GetMapping("/client-connects")
    public List<ClientConnectDTO> getAllClientConnects() {
        log.debug("REST request to get all ClientConnects");
        return clientConnectService.findAll();
    }

    /**
     * {@code GET  /client-connects/:id} : get the "id" clientConnect.
     *
     * @param id the id of the clientConnectDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientConnectDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/client-connects/{id}")
    public ResponseEntity<ClientConnectDTO> getClientConnect(@PathVariable Long id) {
        log.debug("REST request to get ClientConnect : {}", id);
        Optional<ClientConnectDTO> clientConnectDTO = clientConnectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientConnectDTO);
    }

    /**
     * {@code DELETE  /client-connects/:id} : delete the "id" clientConnect.
     *
     * @param id the id of the clientConnectDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/client-connects/{id}")
    public ResponseEntity<Void> deleteClientConnect(@PathVariable Long id) {
        log.debug("REST request to delete ClientConnect : {}", id);
        clientConnectService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
