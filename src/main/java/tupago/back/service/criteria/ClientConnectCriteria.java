package tupago.back.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tupago.back.domain.ClientConnect} entity. This class is used
 * in {@link tupago.back.web.rest.ClientConnectResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /client-connects?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientConnectCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter type;

    private StringFilter identifier;

    private StringFilter located;

    private StringFilter editBy;

    private InstantFilter editDate;

    private StringFilter createdBy;

    private InstantFilter createsDate;

    private LongFilter transactionId;

    private LongFilter accountuserId;

    private Boolean distinct;

    public ClientConnectCriteria() {}

    public ClientConnectCriteria(ClientConnectCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.identifier = other.identifier == null ? null : other.identifier.copy();
        this.located = other.located == null ? null : other.located.copy();
        this.editBy = other.editBy == null ? null : other.editBy.copy();
        this.editDate = other.editDate == null ? null : other.editDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createsDate = other.createsDate == null ? null : other.createsDate.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.accountuserId = other.accountuserId == null ? null : other.accountuserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ClientConnectCriteria copy() {
        return new ClientConnectCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getType() {
        return type;
    }

    public StringFilter type() {
        if (type == null) {
            type = new StringFilter();
        }
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getIdentifier() {
        return identifier;
    }

    public StringFilter identifier() {
        if (identifier == null) {
            identifier = new StringFilter();
        }
        return identifier;
    }

    public void setIdentifier(StringFilter identifier) {
        this.identifier = identifier;
    }

    public StringFilter getLocated() {
        return located;
    }

    public StringFilter located() {
        if (located == null) {
            located = new StringFilter();
        }
        return located;
    }

    public void setLocated(StringFilter located) {
        this.located = located;
    }

    public StringFilter getEditBy() {
        return editBy;
    }

    public StringFilter editBy() {
        if (editBy == null) {
            editBy = new StringFilter();
        }
        return editBy;
    }

    public void setEditBy(StringFilter editBy) {
        this.editBy = editBy;
    }

    public InstantFilter getEditDate() {
        return editDate;
    }

    public InstantFilter editDate() {
        if (editDate == null) {
            editDate = new InstantFilter();
        }
        return editDate;
    }

    public void setEditDate(InstantFilter editDate) {
        this.editDate = editDate;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public InstantFilter getCreatesDate() {
        return createsDate;
    }

    public InstantFilter createsDate() {
        if (createsDate == null) {
            createsDate = new InstantFilter();
        }
        return createsDate;
    }

    public void setCreatesDate(InstantFilter createsDate) {
        this.createsDate = createsDate;
    }

    public LongFilter getTransactionId() {
        return transactionId;
    }

    public LongFilter transactionId() {
        if (transactionId == null) {
            transactionId = new LongFilter();
        }
        return transactionId;
    }

    public void setTransactionId(LongFilter transactionId) {
        this.transactionId = transactionId;
    }

    public LongFilter getAccountuserId() {
        return accountuserId;
    }

    public LongFilter accountuserId() {
        if (accountuserId == null) {
            accountuserId = new LongFilter();
        }
        return accountuserId;
    }

    public void setAccountuserId(LongFilter accountuserId) {
        this.accountuserId = accountuserId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ClientConnectCriteria that = (ClientConnectCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(type, that.type) &&
            Objects.equals(identifier, that.identifier) &&
            Objects.equals(located, that.located) &&
            Objects.equals(editBy, that.editBy) &&
            Objects.equals(editDate, that.editDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createsDate, that.createsDate) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(accountuserId, that.accountuserId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            name,
            type,
            identifier,
            located,
            editBy,
            editDate,
            createdBy,
            createsDate,
            transactionId,
            accountuserId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientConnectCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (name != null ? "name=" + name + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (identifier != null ? "identifier=" + identifier + ", " : "") +
            (located != null ? "located=" + located + ", " : "") +
            (editBy != null ? "editBy=" + editBy + ", " : "") +
            (editDate != null ? "editDate=" + editDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createsDate != null ? "createsDate=" + createsDate + ", " : "") +
            (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
            (accountuserId != null ? "accountuserId=" + accountuserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
