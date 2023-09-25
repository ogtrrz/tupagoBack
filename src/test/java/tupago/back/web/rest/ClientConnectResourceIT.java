package tupago.back.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tupago.back.IntegrationTest;
import tupago.back.domain.ClientConnect;
import tupago.back.repository.ClientConnectRepository;
import tupago.back.service.dto.ClientConnectDTO;
import tupago.back.service.mapper.ClientConnectMapper;

/**
 * Integration tests for the {@link ClientConnectResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ClientConnectResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATED = "AAAAAAAAAA";
    private static final String UPDATED_LOCATED = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_BY = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDIT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDIT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATES_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATES_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/client-connects";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientConnectRepository clientConnectRepository;

    @Autowired
    private ClientConnectMapper clientConnectMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restClientConnectMockMvc;

    private ClientConnect clientConnect;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientConnect createEntity(EntityManager em) {
        ClientConnect clientConnect = new ClientConnect()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .identifier(DEFAULT_IDENTIFIER)
            .located(DEFAULT_LOCATED)
            .editBy(DEFAULT_EDIT_BY)
            .editDate(DEFAULT_EDIT_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createsDate(DEFAULT_CREATES_DATE);
        return clientConnect;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientConnect createUpdatedEntity(EntityManager em) {
        ClientConnect clientConnect = new ClientConnect()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .located(UPDATED_LOCATED)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        return clientConnect;
    }

    @BeforeEach
    public void initTest() {
        clientConnect = createEntity(em);
    }

    @Test
    @Transactional
    void createClientConnect() throws Exception {
        int databaseSizeBeforeCreate = clientConnectRepository.findAll().size();
        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);
        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeCreate + 1);
        ClientConnect testClientConnect = clientConnectList.get(clientConnectList.size() - 1);
        assertThat(testClientConnect.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testClientConnect.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testClientConnect.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testClientConnect.getLocated()).isEqualTo(DEFAULT_LOCATED);
        assertThat(testClientConnect.getEditBy()).isEqualTo(DEFAULT_EDIT_BY);
        assertThat(testClientConnect.getEditDate()).isEqualTo(DEFAULT_EDIT_DATE);
        assertThat(testClientConnect.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testClientConnect.getCreatesDate()).isEqualTo(DEFAULT_CREATES_DATE);
    }

    @Test
    @Transactional
    void createClientConnectWithExistingId() throws Exception {
        // Create the ClientConnect with an existing ID
        clientConnect.setId(1L);
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        int databaseSizeBeforeCreate = clientConnectRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setName(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setType(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkIdentifierIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setIdentifier(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditByIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setEditBy(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setEditDate(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setCreatedBy(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatesDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = clientConnectRepository.findAll().size();
        // set the field null
        clientConnect.setCreatesDate(null);

        // Create the ClientConnect, which fails.
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        restClientConnectMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllClientConnects() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        // Get all the clientConnectList
        restClientConnectMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(clientConnect.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].located").value(hasItem(DEFAULT_LOCATED)))
            .andExpect(jsonPath("$.[*].editBy").value(hasItem(DEFAULT_EDIT_BY)))
            .andExpect(jsonPath("$.[*].editDate").value(hasItem(DEFAULT_EDIT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createsDate").value(hasItem(DEFAULT_CREATES_DATE.toString())));
    }

    @Test
    @Transactional
    void getClientConnect() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        // Get the clientConnect
        restClientConnectMockMvc
            .perform(get(ENTITY_API_URL_ID, clientConnect.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(clientConnect.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.located").value(DEFAULT_LOCATED))
            .andExpect(jsonPath("$.editBy").value(DEFAULT_EDIT_BY))
            .andExpect(jsonPath("$.editDate").value(DEFAULT_EDIT_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createsDate").value(DEFAULT_CREATES_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingClientConnect() throws Exception {
        // Get the clientConnect
        restClientConnectMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingClientConnect() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();

        // Update the clientConnect
        ClientConnect updatedClientConnect = clientConnectRepository.findById(clientConnect.getId()).get();
        // Disconnect from session so that the updates on updatedClientConnect are not directly saved in db
        em.detach(updatedClientConnect);
        updatedClientConnect
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .located(UPDATED_LOCATED)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(updatedClientConnect);

        restClientConnectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientConnectDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isOk());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
        ClientConnect testClientConnect = clientConnectList.get(clientConnectList.size() - 1);
        assertThat(testClientConnect.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClientConnect.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testClientConnect.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testClientConnect.getLocated()).isEqualTo(UPDATED_LOCATED);
        assertThat(testClientConnect.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testClientConnect.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testClientConnect.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testClientConnect.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void putNonExistingClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, clientConnectDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateClientConnectWithPatch() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();

        // Update the clientConnect using partial update
        ClientConnect partialUpdatedClientConnect = new ClientConnect();
        partialUpdatedClientConnect.setId(clientConnect.getId());

        partialUpdatedClientConnect
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE);

        restClientConnectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientConnect.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientConnect))
            )
            .andExpect(status().isOk());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
        ClientConnect testClientConnect = clientConnectList.get(clientConnectList.size() - 1);
        assertThat(testClientConnect.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClientConnect.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testClientConnect.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testClientConnect.getLocated()).isEqualTo(DEFAULT_LOCATED);
        assertThat(testClientConnect.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testClientConnect.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testClientConnect.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testClientConnect.getCreatesDate()).isEqualTo(DEFAULT_CREATES_DATE);
    }

    @Test
    @Transactional
    void fullUpdateClientConnectWithPatch() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();

        // Update the clientConnect using partial update
        ClientConnect partialUpdatedClientConnect = new ClientConnect();
        partialUpdatedClientConnect.setId(clientConnect.getId());

        partialUpdatedClientConnect
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .identifier(UPDATED_IDENTIFIER)
            .located(UPDATED_LOCATED)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);

        restClientConnectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedClientConnect.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedClientConnect))
            )
            .andExpect(status().isOk());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
        ClientConnect testClientConnect = clientConnectList.get(clientConnectList.size() - 1);
        assertThat(testClientConnect.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testClientConnect.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testClientConnect.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testClientConnect.getLocated()).isEqualTo(UPDATED_LOCATED);
        assertThat(testClientConnect.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testClientConnect.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testClientConnect.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testClientConnect.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, clientConnectDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamClientConnect() throws Exception {
        int databaseSizeBeforeUpdate = clientConnectRepository.findAll().size();
        clientConnect.setId(count.incrementAndGet());

        // Create the ClientConnect
        ClientConnectDTO clientConnectDTO = clientConnectMapper.toDto(clientConnect);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restClientConnectMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(clientConnectDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ClientConnect in the database
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteClientConnect() throws Exception {
        // Initialize the database
        clientConnectRepository.saveAndFlush(clientConnect);

        int databaseSizeBeforeDelete = clientConnectRepository.findAll().size();

        // Delete the clientConnect
        restClientConnectMockMvc
            .perform(delete(ENTITY_API_URL_ID, clientConnect.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ClientConnect> clientConnectList = clientConnectRepository.findAll();
        assertThat(clientConnectList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
