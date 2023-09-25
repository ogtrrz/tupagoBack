package tupago.back.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tupago.back.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
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
import tupago.back.domain.Transaction;
import tupago.back.domain.enumeration.Status;
import tupago.back.repository.TransactionRepository;
import tupago.back.service.dto.TransactionDTO;
import tupago.back.service.mapper.TransactionMapper;

/**
 * Integration tests for the {@link TransactionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransactionResourceIT {

    private static final Instant DEFAULT_INICIAL_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INICIAL_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREADO_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREADO_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ENVIADO_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENVIADO_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ERROR_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ERROR_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PAGADO_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PAGADO_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Status DEFAULT_STATUS = Status.INICIAL;
    private static final Status UPDATED_STATUS = Status.CREADO;

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final Boolean DEFAULT_TYPE = false;
    private static final Boolean UPDATED_TYPE = true;

    private static final String DEFAULT_FROM = "AAAAAAAAAA";
    private static final String UPDATED_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_FROM = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_MESSAGE_FROM = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_FROM = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_STRING = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_STRING = "BBBBBBBBBB";

    private static final String DEFAULT_EDIT_BY = "AAAAAAAAAA";
    private static final String UPDATED_EDIT_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_EDIT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EDIT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATES_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATES_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionMockMvc;

    private Transaction transaction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .inicialDate(DEFAULT_INICIAL_DATE)
            .creadoDate(DEFAULT_CREADO_DATE)
            .enviadoDate(DEFAULT_ENVIADO_DATE)
            .errorDate(DEFAULT_ERROR_DATE)
            .pagadoDate(DEFAULT_PAGADO_DATE)
            .status(DEFAULT_STATUS)
            .reference(DEFAULT_REFERENCE)
            .amount(DEFAULT_AMOUNT)
            .type(DEFAULT_TYPE)
            .from(DEFAULT_FROM)
            .accountFrom(DEFAULT_ACCOUNT_FROM)
            .referenceFrom(DEFAULT_REFERENCE_FROM)
            .messageFrom(DEFAULT_MESSAGE_FROM)
            .paymentString(DEFAULT_PAYMENT_STRING)
            .editBy(DEFAULT_EDIT_BY)
            .editDate(DEFAULT_EDIT_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .createsDate(DEFAULT_CREATES_DATE);
        return transaction;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Transaction createUpdatedEntity(EntityManager em) {
        Transaction transaction = new Transaction()
            .inicialDate(UPDATED_INICIAL_DATE)
            .creadoDate(UPDATED_CREADO_DATE)
            .enviadoDate(UPDATED_ENVIADO_DATE)
            .errorDate(UPDATED_ERROR_DATE)
            .pagadoDate(UPDATED_PAGADO_DATE)
            .status(UPDATED_STATUS)
            .reference(UPDATED_REFERENCE)
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .from(UPDATED_FROM)
            .accountFrom(UPDATED_ACCOUNT_FROM)
            .referenceFrom(UPDATED_REFERENCE_FROM)
            .messageFrom(UPDATED_MESSAGE_FROM)
            .paymentString(UPDATED_PAYMENT_STRING)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        return transaction;
    }

    @BeforeEach
    public void initTest() {
        transaction = createEntity(em);
    }

    @Test
    @Transactional
    void createTransaction() throws Exception {
        int databaseSizeBeforeCreate = transactionRepository.findAll().size();
        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);
        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate + 1);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getInicialDate()).isEqualTo(DEFAULT_INICIAL_DATE);
        assertThat(testTransaction.getCreadoDate()).isEqualTo(DEFAULT_CREADO_DATE);
        assertThat(testTransaction.getEnviadoDate()).isEqualTo(DEFAULT_ENVIADO_DATE);
        assertThat(testTransaction.getErrorDate()).isEqualTo(DEFAULT_ERROR_DATE);
        assertThat(testTransaction.getPagadoDate()).isEqualTo(DEFAULT_PAGADO_DATE);
        assertThat(testTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTransaction.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testTransaction.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testTransaction.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testTransaction.getFrom()).isEqualTo(DEFAULT_FROM);
        assertThat(testTransaction.getAccountFrom()).isEqualTo(DEFAULT_ACCOUNT_FROM);
        assertThat(testTransaction.getReferenceFrom()).isEqualTo(DEFAULT_REFERENCE_FROM);
        assertThat(testTransaction.getMessageFrom()).isEqualTo(DEFAULT_MESSAGE_FROM);
        assertThat(testTransaction.getPaymentString()).isEqualTo(DEFAULT_PAYMENT_STRING);
        assertThat(testTransaction.getEditBy()).isEqualTo(DEFAULT_EDIT_BY);
        assertThat(testTransaction.getEditDate()).isEqualTo(DEFAULT_EDIT_DATE);
        assertThat(testTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTransaction.getCreatesDate()).isEqualTo(DEFAULT_CREATES_DATE);
    }

    @Test
    @Transactional
    void createTransactionWithExistingId() throws Exception {
        // Create the Transaction with an existing ID
        transaction.setId(1L);
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        int databaseSizeBeforeCreate = transactionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setStatus(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setReference(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setAmount(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setType(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setFrom(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAccountFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setAccountFrom(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditByIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setEditBy(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setEditDate(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setCreatedBy(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatesDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionRepository.findAll().size();
        // set the field null
        transaction.setCreatesDate(null);

        // Create the Transaction, which fails.
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        restTransactionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTransactions() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get all the transactionList
        restTransactionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transaction.getId().intValue())))
            .andExpect(jsonPath("$.[*].inicialDate").value(hasItem(DEFAULT_INICIAL_DATE.toString())))
            .andExpect(jsonPath("$.[*].creadoDate").value(hasItem(DEFAULT_CREADO_DATE.toString())))
            .andExpect(jsonPath("$.[*].enviadoDate").value(hasItem(DEFAULT_ENVIADO_DATE.toString())))
            .andExpect(jsonPath("$.[*].errorDate").value(hasItem(DEFAULT_ERROR_DATE.toString())))
            .andExpect(jsonPath("$.[*].pagadoDate").value(hasItem(DEFAULT_PAGADO_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(sameNumber(DEFAULT_AMOUNT))))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.booleanValue())))
            .andExpect(jsonPath("$.[*].from").value(hasItem(DEFAULT_FROM)))
            .andExpect(jsonPath("$.[*].accountFrom").value(hasItem(DEFAULT_ACCOUNT_FROM)))
            .andExpect(jsonPath("$.[*].referenceFrom").value(hasItem(DEFAULT_REFERENCE_FROM)))
            .andExpect(jsonPath("$.[*].messageFrom").value(hasItem(DEFAULT_MESSAGE_FROM)))
            .andExpect(jsonPath("$.[*].paymentString").value(hasItem(DEFAULT_PAYMENT_STRING)))
            .andExpect(jsonPath("$.[*].editBy").value(hasItem(DEFAULT_EDIT_BY)))
            .andExpect(jsonPath("$.[*].editDate").value(hasItem(DEFAULT_EDIT_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].createsDate").value(hasItem(DEFAULT_CREATES_DATE.toString())));
    }

    @Test
    @Transactional
    void getTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        // Get the transaction
        restTransactionMockMvc
            .perform(get(ENTITY_API_URL_ID, transaction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transaction.getId().intValue()))
            .andExpect(jsonPath("$.inicialDate").value(DEFAULT_INICIAL_DATE.toString()))
            .andExpect(jsonPath("$.creadoDate").value(DEFAULT_CREADO_DATE.toString()))
            .andExpect(jsonPath("$.enviadoDate").value(DEFAULT_ENVIADO_DATE.toString()))
            .andExpect(jsonPath("$.errorDate").value(DEFAULT_ERROR_DATE.toString()))
            .andExpect(jsonPath("$.pagadoDate").value(DEFAULT_PAGADO_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE))
            .andExpect(jsonPath("$.amount").value(sameNumber(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.booleanValue()))
            .andExpect(jsonPath("$.from").value(DEFAULT_FROM))
            .andExpect(jsonPath("$.accountFrom").value(DEFAULT_ACCOUNT_FROM))
            .andExpect(jsonPath("$.referenceFrom").value(DEFAULT_REFERENCE_FROM))
            .andExpect(jsonPath("$.messageFrom").value(DEFAULT_MESSAGE_FROM))
            .andExpect(jsonPath("$.paymentString").value(DEFAULT_PAYMENT_STRING))
            .andExpect(jsonPath("$.editBy").value(DEFAULT_EDIT_BY))
            .andExpect(jsonPath("$.editDate").value(DEFAULT_EDIT_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.createsDate").value(DEFAULT_CREATES_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTransaction() throws Exception {
        // Get the transaction
        restTransactionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction
        Transaction updatedTransaction = transactionRepository.findById(transaction.getId()).get();
        // Disconnect from session so that the updates on updatedTransaction are not directly saved in db
        em.detach(updatedTransaction);
        updatedTransaction
            .inicialDate(UPDATED_INICIAL_DATE)
            .creadoDate(UPDATED_CREADO_DATE)
            .enviadoDate(UPDATED_ENVIADO_DATE)
            .errorDate(UPDATED_ERROR_DATE)
            .pagadoDate(UPDATED_PAGADO_DATE)
            .status(UPDATED_STATUS)
            .reference(UPDATED_REFERENCE)
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .from(UPDATED_FROM)
            .accountFrom(UPDATED_ACCOUNT_FROM)
            .referenceFrom(UPDATED_REFERENCE_FROM)
            .messageFrom(UPDATED_MESSAGE_FROM)
            .paymentString(UPDATED_PAYMENT_STRING)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);
        TransactionDTO transactionDTO = transactionMapper.toDto(updatedTransaction);

        restTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transactionDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getInicialDate()).isEqualTo(UPDATED_INICIAL_DATE);
        assertThat(testTransaction.getCreadoDate()).isEqualTo(UPDATED_CREADO_DATE);
        assertThat(testTransaction.getEnviadoDate()).isEqualTo(UPDATED_ENVIADO_DATE);
        assertThat(testTransaction.getErrorDate()).isEqualTo(UPDATED_ERROR_DATE);
        assertThat(testTransaction.getPagadoDate()).isEqualTo(UPDATED_PAGADO_DATE);
        assertThat(testTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransaction.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testTransaction.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testTransaction.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTransaction.getFrom()).isEqualTo(UPDATED_FROM);
        assertThat(testTransaction.getAccountFrom()).isEqualTo(UPDATED_ACCOUNT_FROM);
        assertThat(testTransaction.getReferenceFrom()).isEqualTo(UPDATED_REFERENCE_FROM);
        assertThat(testTransaction.getMessageFrom()).isEqualTo(UPDATED_MESSAGE_FROM);
        assertThat(testTransaction.getPaymentString()).isEqualTo(UPDATED_PAYMENT_STRING);
        assertThat(testTransaction.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testTransaction.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testTransaction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTransaction.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void putNonExistingTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transactionDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransactionWithPatch() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction using partial update
        Transaction partialUpdatedTransaction = new Transaction();
        partialUpdatedTransaction.setId(transaction.getId());

        partialUpdatedTransaction
            .creadoDate(UPDATED_CREADO_DATE)
            .type(UPDATED_TYPE)
            .from(UPDATED_FROM)
            .accountFrom(UPDATED_ACCOUNT_FROM)
            .referenceFrom(UPDATED_REFERENCE_FROM)
            .messageFrom(UPDATED_MESSAGE_FROM);

        restTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransaction.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransaction))
            )
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getInicialDate()).isEqualTo(DEFAULT_INICIAL_DATE);
        assertThat(testTransaction.getCreadoDate()).isEqualTo(UPDATED_CREADO_DATE);
        assertThat(testTransaction.getEnviadoDate()).isEqualTo(DEFAULT_ENVIADO_DATE);
        assertThat(testTransaction.getErrorDate()).isEqualTo(DEFAULT_ERROR_DATE);
        assertThat(testTransaction.getPagadoDate()).isEqualTo(DEFAULT_PAGADO_DATE);
        assertThat(testTransaction.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testTransaction.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testTransaction.getAmount()).isEqualByComparingTo(DEFAULT_AMOUNT);
        assertThat(testTransaction.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTransaction.getFrom()).isEqualTo(UPDATED_FROM);
        assertThat(testTransaction.getAccountFrom()).isEqualTo(UPDATED_ACCOUNT_FROM);
        assertThat(testTransaction.getReferenceFrom()).isEqualTo(UPDATED_REFERENCE_FROM);
        assertThat(testTransaction.getMessageFrom()).isEqualTo(UPDATED_MESSAGE_FROM);
        assertThat(testTransaction.getPaymentString()).isEqualTo(DEFAULT_PAYMENT_STRING);
        assertThat(testTransaction.getEditBy()).isEqualTo(DEFAULT_EDIT_BY);
        assertThat(testTransaction.getEditDate()).isEqualTo(DEFAULT_EDIT_DATE);
        assertThat(testTransaction.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTransaction.getCreatesDate()).isEqualTo(DEFAULT_CREATES_DATE);
    }

    @Test
    @Transactional
    void fullUpdateTransactionWithPatch() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();

        // Update the transaction using partial update
        Transaction partialUpdatedTransaction = new Transaction();
        partialUpdatedTransaction.setId(transaction.getId());

        partialUpdatedTransaction
            .inicialDate(UPDATED_INICIAL_DATE)
            .creadoDate(UPDATED_CREADO_DATE)
            .enviadoDate(UPDATED_ENVIADO_DATE)
            .errorDate(UPDATED_ERROR_DATE)
            .pagadoDate(UPDATED_PAGADO_DATE)
            .status(UPDATED_STATUS)
            .reference(UPDATED_REFERENCE)
            .amount(UPDATED_AMOUNT)
            .type(UPDATED_TYPE)
            .from(UPDATED_FROM)
            .accountFrom(UPDATED_ACCOUNT_FROM)
            .referenceFrom(UPDATED_REFERENCE_FROM)
            .messageFrom(UPDATED_MESSAGE_FROM)
            .paymentString(UPDATED_PAYMENT_STRING)
            .editBy(UPDATED_EDIT_BY)
            .editDate(UPDATED_EDIT_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .createsDate(UPDATED_CREATES_DATE);

        restTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransaction.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTransaction))
            )
            .andExpect(status().isOk());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
        Transaction testTransaction = transactionList.get(transactionList.size() - 1);
        assertThat(testTransaction.getInicialDate()).isEqualTo(UPDATED_INICIAL_DATE);
        assertThat(testTransaction.getCreadoDate()).isEqualTo(UPDATED_CREADO_DATE);
        assertThat(testTransaction.getEnviadoDate()).isEqualTo(UPDATED_ENVIADO_DATE);
        assertThat(testTransaction.getErrorDate()).isEqualTo(UPDATED_ERROR_DATE);
        assertThat(testTransaction.getPagadoDate()).isEqualTo(UPDATED_PAGADO_DATE);
        assertThat(testTransaction.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testTransaction.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testTransaction.getAmount()).isEqualByComparingTo(UPDATED_AMOUNT);
        assertThat(testTransaction.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testTransaction.getFrom()).isEqualTo(UPDATED_FROM);
        assertThat(testTransaction.getAccountFrom()).isEqualTo(UPDATED_ACCOUNT_FROM);
        assertThat(testTransaction.getReferenceFrom()).isEqualTo(UPDATED_REFERENCE_FROM);
        assertThat(testTransaction.getMessageFrom()).isEqualTo(UPDATED_MESSAGE_FROM);
        assertThat(testTransaction.getPaymentString()).isEqualTo(UPDATED_PAYMENT_STRING);
        assertThat(testTransaction.getEditBy()).isEqualTo(UPDATED_EDIT_BY);
        assertThat(testTransaction.getEditDate()).isEqualTo(UPDATED_EDIT_DATE);
        assertThat(testTransaction.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTransaction.getCreatesDate()).isEqualTo(UPDATED_CREATES_DATE);
    }

    @Test
    @Transactional
    void patchNonExistingTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transactionDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransaction() throws Exception {
        int databaseSizeBeforeUpdate = transactionRepository.findAll().size();
        transaction.setId(count.incrementAndGet());

        // Create the Transaction
        TransactionDTO transactionDTO = transactionMapper.toDto(transaction);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(transactionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Transaction in the database
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransaction() throws Exception {
        // Initialize the database
        transactionRepository.saveAndFlush(transaction);

        int databaseSizeBeforeDelete = transactionRepository.findAll().size();

        // Delete the transaction
        restTransactionMockMvc
            .perform(delete(ENTITY_API_URL_ID, transaction.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
