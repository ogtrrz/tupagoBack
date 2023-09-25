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
import java.util.UUID;
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
import tupago.back.domain.AccountUser;
import tupago.back.repository.AccountUserRepository;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.mapper.AccountUserMapper;

/**
 * Integration tests for the {@link AccountUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AccountUserResourceIT {

    private static final String DEFAULT_USER = "AAAAAAAAAA";
    private static final String UPDATED_USER = "BBBBBBBBBB";

    private static final UUID DEFAULT_USER_ACCOUNT = UUID.randomUUID();
    private static final UUID UPDATED_USER_ACCOUNT = UUID.randomUUID();

    private static final Instant DEFAULT_INSCRIPTION = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INSCRIPTION = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_USER_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_USER_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_BY = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDIT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDIT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATES_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATES_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/account-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private AccountUserMapper accountUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountUserMockMvc;

    private AccountUser accountUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountUser createEntity(EntityManager em) {
        AccountUser accountUser = new AccountUser()
            .user(DEFAULT_USER)
            .userAccount(DEFAULT_USER_ACCOUNT)
            .inscription(DEFAULT_INSCRIPTION)
            .userTelephone(DEFAULT_USER_TELEPHONE)
            .userName(DEFAULT_USER_NAME)
            .editBy(DEFAULT_EDIT_BY)
            .editDate(DEFAULT_EDIT_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createsDate(DEFAULT_CREATES_DATE);
        return accountUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AccountUser createUpdatedEntity(EntityManager em) {
        AccountUser accountUser = new AccountUser()
            .user(UPDATED_USER)
            .userAccount(UPDATED_USER_ACCOUNT)
            .inscription(UPDATED_INSCRIPTION)
            .userTelephone(UPDATED_USER_TELEPHONE)
            .userName(UPDATED_USER_NAME)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        return accountUser;
    }

    @BeforeEach
    public void initTest() {
        accountUser = createEntity(em);
    }

    @Test
    @Transactional
    void createAccountUser() throws Exception {
        int databaseSizeBeforeCreate = accountUserRepository.findAll().size();
        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);
        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeCreate + 1);
        AccountUser testAccountUser = accountUserList.get(accountUserList.size() - 1);
        assertThat(testAccountUser.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testAccountUser.getUserAccount()).isEqualTo(DEFAULT_USER_ACCOUNT);
        assertThat(testAccountUser.getInscription()).isEqualTo(DEFAULT_INSCRIPTION);
        assertThat(testAccountUser.getUserTelephone()).isEqualTo(DEFAULT_USER_TELEPHONE);
        assertThat(testAccountUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testAccountUser.getEditBy()).isEqualTo(DEFAULT_EDIT_BY);
        assertThat(testAccountUser.getEditDate()).isEqualTo(DEFAULT_EDIT_DATE);
        assertThat(testAccountUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAccountUser.getCreatesDate()).isEqualTo(DEFAULT_CREATES_DATE);
    }

    @Test
    @Transactional
    void createAccountUserWithExistingId() throws Exception {
        // Create the AccountUser with an existing ID
        accountUser.setId(1L);
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        int databaseSizeBeforeCreate = accountUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setUser(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUserAccountIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setUserAccount(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInscriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setInscription(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUserTelephoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setUserTelephone(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkUserNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setUserName(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditByIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setEditBy(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setEditDate(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setCreatedBy(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatesDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = accountUserRepository.findAll().size();
        // set the field null
        accountUser.setCreatesDate(null);

        // Create the AccountUser, which fails.
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        restAccountUserMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllAccountUsers() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        // Get all the accountUserList
        restAccountUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accountUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER)))
            .andExpect(jsonPath("$.[*].userAccount").value(hasItem(DEFAULT_USER_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].inscription").value(hasItem(DEFAULT_INSCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].userTelephone").value(hasItem(DEFAULT_USER_TELEPHONE)))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME)))
            .andExpect(jsonPath("$.[*].editBy").value(hasItem(DEFAULT_EDIT_BY)))
            .andExpect(jsonPath("$.[*].editDate").value(hasItem(DEFAULT_EDIT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createsDate").value(hasItem(DEFAULT_CREATES_DATE.toString())));
    }

    @Test
    @Transactional
    void getAccountUser() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        // Get the accountUser
        restAccountUserMockMvc
            .perform(get(ENTITY_API_URL_ID, accountUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accountUser.getId().intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER))
            .andExpect(jsonPath("$.userAccount").value(DEFAULT_USER_ACCOUNT.toString()))
            .andExpect(jsonPath("$.inscription").value(DEFAULT_INSCRIPTION.toString()))
            .andExpect(jsonPath("$.userTelephone").value(DEFAULT_USER_TELEPHONE))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME))
            .andExpect(jsonPath("$.editBy").value(DEFAULT_EDIT_BY))
            .andExpect(jsonPath("$.editDate").value(DEFAULT_EDIT_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createsDate").value(DEFAULT_CREATES_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAccountUser() throws Exception {
        // Get the accountUser
        restAccountUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAccountUser() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();

        // Update the accountUser
        AccountUser updatedAccountUser = accountUserRepository.findById(accountUser.getId()).get();
        // Disconnect from session so that the updates on updatedAccountUser are not directly saved in db
        em.detach(updatedAccountUser);
        updatedAccountUser
            .user(UPDATED_USER)
            .userAccount(UPDATED_USER_ACCOUNT)
            .inscription(UPDATED_INSCRIPTION)
            .userTelephone(UPDATED_USER_TELEPHONE)
            .userName(UPDATED_USER_NAME)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(updatedAccountUser);

        restAccountUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accountUserDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
        AccountUser testAccountUser = accountUserList.get(accountUserList.size() - 1);
        assertThat(testAccountUser.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testAccountUser.getUserAccount()).isEqualTo(UPDATED_USER_ACCOUNT);
        assertThat(testAccountUser.getInscription()).isEqualTo(UPDATED_INSCRIPTION);
        assertThat(testAccountUser.getUserTelephone()).isEqualTo(UPDATED_USER_TELEPHONE);
        assertThat(testAccountUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testAccountUser.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testAccountUser.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testAccountUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAccountUser.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void putNonExistingAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accountUserDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAccountUserWithPatch() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();

        // Update the accountUser using partial update
        AccountUser partialUpdatedAccountUser = new AccountUser();
        partialUpdatedAccountUser.setId(accountUser.getId());

        partialUpdatedAccountUser
            .userAccount(UPDATED_USER_ACCOUNT)
            .inscription(UPDATED_INSCRIPTION)
            .userTelephone(UPDATED_USER_TELEPHONE)
            .userName(UPDATED_USER_NAME)
            .editBy(UPDATED_EDIT_BY)
            .createsDate(UPDATED_CREATES_DATE);

        restAccountUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccountUser.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountUser))
            )
            .andExpect(status().isOk());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
        AccountUser testAccountUser = accountUserList.get(accountUserList.size() - 1);
        assertThat(testAccountUser.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testAccountUser.getUserAccount()).isEqualTo(UPDATED_USER_ACCOUNT);
        assertThat(testAccountUser.getInscription()).isEqualTo(UPDATED_INSCRIPTION);
        assertThat(testAccountUser.getUserTelephone()).isEqualTo(UPDATED_USER_TELEPHONE);
        assertThat(testAccountUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testAccountUser.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testAccountUser.getEditDate()).isEqualTo(DEFAULT_EDIT_DATE);
        assertThat(testAccountUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAccountUser.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void fullUpdateAccountUserWithPatch() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();

        // Update the accountUser using partial update
        AccountUser partialUpdatedAccountUser = new AccountUser();
        partialUpdatedAccountUser.setId(accountUser.getId());

        partialUpdatedAccountUser
            .user(UPDATED_USER)
            .userAccount(UPDATED_USER_ACCOUNT)
            .inscription(UPDATED_INSCRIPTION)
            .userTelephone(UPDATED_USER_TELEPHONE)
            .userName(UPDATED_USER_NAME)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);

        restAccountUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccountUser.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAccountUser))
            )
            .andExpect(status().isOk());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
        AccountUser testAccountUser = accountUserList.get(accountUserList.size() - 1);
        assertThat(testAccountUser.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testAccountUser.getUserAccount()).isEqualTo(UPDATED_USER_ACCOUNT);
        assertThat(testAccountUser.getInscription()).isEqualTo(UPDATED_INSCRIPTION);
        assertThat(testAccountUser.getUserTelephone()).isEqualTo(UPDATED_USER_TELEPHONE);
        assertThat(testAccountUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testAccountUser.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testAccountUser.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testAccountUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAccountUser.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, accountUserDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAccountUser() throws Exception {
        int databaseSizeBeforeUpdate = accountUserRepository.findAll().size();
        accountUser.setId(count.incrementAndGet());

        // Create the AccountUser
        AccountUserDTO accountUserDTO = accountUserMapper.toDto(accountUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(accountUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AccountUser in the database
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAccountUser() throws Exception {
        // Initialize the database
        accountUserRepository.saveAndFlush(accountUser);

        int databaseSizeBeforeDelete = accountUserRepository.findAll().size();

        // Delete the accountUser
        restAccountUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, accountUser.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AccountUser> accountUserList = accountUserRepository.findAll();
        assertThat(accountUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
