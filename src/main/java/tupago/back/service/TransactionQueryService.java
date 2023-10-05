package tupago.back.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tupago.back.domain.*; // for static metamodels
import tupago.back.domain.Transaction;
import tupago.back.repository.TransactionRepository;
import tupago.back.service.criteria.TransactionCriteria;
import tupago.back.service.dto.TransactionDTO;
import tupago.back.service.mapper.TransactionMapper;

/**
 * Service for executing complex queries for {@link Transaction} entities in the database.
 * The main input is a {@link TransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TransactionDTO} or a {@link Page} of {@link TransactionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TransactionQueryService extends QueryService<Transaction> {

    private final Logger log = LoggerFactory.getLogger(TransactionQueryService.class);

    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    public TransactionQueryService(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    /**
     * Return a {@link List} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TransactionDTO> findByCriteria(TransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionMapper.toDto(transactionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TransactionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TransactionDTO> findByCriteria(TransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.findAll(specification, page).map(transactionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Transaction> specification = createSpecification(criteria);
        return transactionRepository.count(specification);
    }

    /**
     * Function to convert {@link TransactionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Transaction> createSpecification(TransactionCriteria criteria) {
        Specification<Transaction> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Transaction_.id));
            }
            if (criteria.getInicialDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInicialDate(), Transaction_.inicialDate));
            }
            if (criteria.getCreadoDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreadoDate(), Transaction_.creadoDate));
            }
            if (criteria.getEnviadoDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEnviadoDate(), Transaction_.enviadoDate));
            }
            if (criteria.getErrorDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getErrorDate(), Transaction_.errorDate));
            }
            if (criteria.getPagadoDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPagadoDate(), Transaction_.pagadoDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Transaction_.status));
            }
            if (criteria.getReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReference(), Transaction_.reference));
            }
            if (criteria.getAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAmount(), Transaction_.amount));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildSpecification(criteria.getType(), Transaction_.type));
            }
            if (criteria.getFrom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFrom(), Transaction_.from));
            }
            if (criteria.getAccountFrom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountFrom(), Transaction_.accountFrom));
            }
            if (criteria.getReferenceFrom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReferenceFrom(), Transaction_.referenceFrom));
            }
            if (criteria.getMessageFrom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMessageFrom(), Transaction_.messageFrom));
            }
            if (criteria.getPaymentString() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPaymentString(), Transaction_.paymentString));
            }
            if (criteria.getEditBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditBy(), Transaction_.editBy));
            }
            if (criteria.getEditDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEditDate(), Transaction_.editDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Transaction_.createdBy));
            }
            if (criteria.getCreatesDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatesDate(), Transaction_.createsDate));
            }
            if (criteria.getAccountuserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAccountuserId(),
                            root -> root.join(Transaction_.accountuser, JoinType.LEFT).get(AccountUser_.id)
                        )
                    );
            }
            if (criteria.getBankId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getBankId(), root -> root.join(Transaction_.bank, JoinType.LEFT).get(Bank_.id))
                    );
            }
            if (criteria.getClientconnectId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getClientconnectId(),
                            root -> root.join(Transaction_.clientconnect, JoinType.LEFT).get(ClientConnect_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
