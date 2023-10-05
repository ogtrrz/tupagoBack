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
import tupago.back.domain.AccountUser;
import tupago.back.repository.AccountUserRepository;
import tupago.back.service.criteria.AccountUserCriteria;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.mapper.AccountUserMapper;

/**
 * Service for executing complex queries for {@link AccountUser} entities in the database.
 * The main input is a {@link AccountUserCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AccountUserDTO} or a {@link Page} of {@link AccountUserDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AccountUserQueryService extends QueryService<AccountUser> {

    private final Logger log = LoggerFactory.getLogger(AccountUserQueryService.class);

    private final AccountUserRepository accountUserRepository;

    private final AccountUserMapper accountUserMapper;

    public AccountUserQueryService(AccountUserRepository accountUserRepository, AccountUserMapper accountUserMapper) {
        this.accountUserRepository = accountUserRepository;
        this.accountUserMapper = accountUserMapper;
    }

    /**
     * Return a {@link List} of {@link AccountUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AccountUserDTO> findByCriteria(AccountUserCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AccountUser> specification = createSpecification(criteria);
        return accountUserMapper.toDto(accountUserRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AccountUserDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AccountUserDTO> findByCriteria(AccountUserCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AccountUser> specification = createSpecification(criteria);
        return accountUserRepository.findAll(specification, page).map(accountUserMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AccountUserCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AccountUser> specification = createSpecification(criteria);
        return accountUserRepository.count(specification);
    }

    /**
     * Function to convert {@link AccountUserCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AccountUser> createSpecification(AccountUserCriteria criteria) {
        Specification<AccountUser> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AccountUser_.id));
            }
            if (criteria.getUser() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUser(), AccountUser_.user));
            }
            if (criteria.getUserAccount() != null) {
                specification = specification.and(buildSpecification(criteria.getUserAccount(), AccountUser_.userAccount));
            }
            if (criteria.getInscription() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInscription(), AccountUser_.inscription));
            }
            if (criteria.getUserTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserTelephone(), AccountUser_.userTelephone));
            }
            if (criteria.getUserName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUserName(), AccountUser_.userName));
            }
            if (criteria.getEditBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditBy(), AccountUser_.editBy));
            }
            if (criteria.getEditDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEditDate(), AccountUser_.editDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), AccountUser_.createdBy));
            }
            if (criteria.getCreatesDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatesDate(), AccountUser_.createsDate));
            }
            if (criteria.getBankId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getBankId(), root -> root.join(AccountUser_.banks, JoinType.LEFT).get(Bank_.id))
                    );
            }
            if (criteria.getClientconnectId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getClientconnectId(),
                            root -> root.join(AccountUser_.clientconnects, JoinType.LEFT).get(ClientConnect_.id)
                        )
                    );
            }
            if (criteria.getTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTransactionId(),
                            root -> root.join(AccountUser_.transactions, JoinType.LEFT).get(Transaction_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
