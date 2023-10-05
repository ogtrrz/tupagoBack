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
import tupago.back.domain.ClientConnect;
import tupago.back.repository.ClientConnectRepository;
import tupago.back.service.criteria.ClientConnectCriteria;
import tupago.back.service.dto.ClientConnectDTO;
import tupago.back.service.mapper.ClientConnectMapper;

/**
 * Service for executing complex queries for {@link ClientConnect} entities in the database.
 * The main input is a {@link ClientConnectCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ClientConnectDTO} or a {@link Page} of {@link ClientConnectDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ClientConnectQueryService extends QueryService<ClientConnect> {

    private final Logger log = LoggerFactory.getLogger(ClientConnectQueryService.class);

    private final ClientConnectRepository clientConnectRepository;

    private final ClientConnectMapper clientConnectMapper;

    public ClientConnectQueryService(ClientConnectRepository clientConnectRepository, ClientConnectMapper clientConnectMapper) {
        this.clientConnectRepository = clientConnectRepository;
        this.clientConnectMapper = clientConnectMapper;
    }

    /**
     * Return a {@link List} of {@link ClientConnectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ClientConnectDTO> findByCriteria(ClientConnectCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ClientConnect> specification = createSpecification(criteria);
        return clientConnectMapper.toDto(clientConnectRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ClientConnectDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ClientConnectDTO> findByCriteria(ClientConnectCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ClientConnect> specification = createSpecification(criteria);
        return clientConnectRepository.findAll(specification, page).map(clientConnectMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ClientConnectCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ClientConnect> specification = createSpecification(criteria);
        return clientConnectRepository.count(specification);
    }

    /**
     * Function to convert {@link ClientConnectCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ClientConnect> createSpecification(ClientConnectCriteria criteria) {
        Specification<ClientConnect> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ClientConnect_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), ClientConnect_.name));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), ClientConnect_.type));
            }
            if (criteria.getIdentifier() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdentifier(), ClientConnect_.identifier));
            }
            if (criteria.getLocated() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocated(), ClientConnect_.located));
            }
            if (criteria.getEditBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditBy(), ClientConnect_.editBy));
            }
            if (criteria.getEditDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEditDate(), ClientConnect_.editDate));
            }
            if (criteria.getCreatedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreatedBy(), ClientConnect_.createdBy));
            }
            if (criteria.getCreatesDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreatesDate(), ClientConnect_.createsDate));
            }
            if (criteria.getTransactionId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getTransactionId(),
                            root -> root.join(ClientConnect_.transactions, JoinType.LEFT).get(Transaction_.id)
                        )
                    );
            }
            if (criteria.getAccountuserId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAccountuserId(),
                            root -> root.join(ClientConnect_.accountuser, JoinType.LEFT).get(AccountUser_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
