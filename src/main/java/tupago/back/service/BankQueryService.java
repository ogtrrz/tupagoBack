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
import tupago.back.domain.Bank;
import tupago.back.repository.BankRepository;
import tupago.back.service.criteria.BankCriteria;
import tupago.back.service.dto.BankDTO;
import tupago.back.service.mapper.BankMapper;

/**
 * Service for executing complex queries for {@link Bank} entities in the database.
 * The main input is a {@link BankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BankDTO} or a {@link Page} of {@link BankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BankQueryService extends QueryService<Bank> {

    private final Logger log = LoggerFactory.getLogger(BankQueryService.class);

    private final BankRepository bankRepository;

    private final BankMapper bankMapper;

    public BankQueryService(BankRepository bankRepository, BankMapper bankMapper) {
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    /**
     * Return a {@link List} of {@link BankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BankDTO> findByCriteria(BankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankMapper.toDto(bankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BankDTO> findByCriteria(BankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankRepository.findAll(specification, page).map(bankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Bank> specification = createSpecification(criteria);
        return bankRepository.count(specification);
    }

    /**
     * Function to convert {@link BankCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Bank> createSpecification(BankCriteria criteria) {
        Specification<Bank> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Bank_.id));
            }
            if (criteria.getBankName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankName(), Bank_.bankName));
            }
            if (criteria.getBankAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccount(), Bank_.bankAccount));
            }
            if (criteria.getEditBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditBy(), Bank_.editBy));
            }
            if (criteria.getEditDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEditDate(), Bank_.editDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), Bank_.createdBy));
            }
            if (criteria.getCreatesDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatesDate(), Bank_.createsDate));
            }
            if (criteria.getTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTransactionId(),
                            root -> root.join(Bank_.transactions, JoinType.LEFT).get(Transaction_.id)
                        )
                    );
            }
            if (criteria.getAccountuserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAccountuserId(),
                            root -> root.join(Bank_.accountuser, JoinType.LEFT).get(AccountUser_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
