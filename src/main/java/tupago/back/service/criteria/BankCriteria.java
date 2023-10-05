package tupago.back.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tupago.back.domain.Bank} entity. This class is used
 * in {@link tupago.back.web.rest.BankResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /banks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter bankName;

    private StringFilter bankAccount;

    private StringFilter editBy;

    private InstantFilter editDate;

    private StringFilter createdBy;

    private InstantFilter createsDate;

    private LongFilter transactionId;

    private LongFilter accountuserId;

    private Boolean distinct;

    public BankCriteria() {}

    public BankCriteria(BankCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.bankName = other.bankName == null ? null : other.bankName.copy();
        this.bankAccount = other.bankAccount == null ? null : other.bankAccount.copy();
        this.editBy = other.editBy == null ? null : other.editBy.copy();
        this.editDate = other.editDate == null ? null : other.editDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createsDate = other.createsDate == null ? null : other.createsDate.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.accountuserId = other.accountuserId == null ? null : other.accountuserId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public BankCriteria copy() {
        return new BankCriteria(this);
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

    public StringFilter getBankName() {
        return bankName;
    }

    public StringFilter bankName() {
        if (bankName == null) {
            bankName = new StringFilter();
        }
        return bankName;
    }

    public void setBankName(StringFilter bankName) {
        this.bankName = bankName;
    }

    public StringFilter getBankAccount() {
        return bankAccount;
    }

    public StringFilter bankAccount() {
        if (bankAccount == null) {
            bankAccount = new StringFilter();
        }
        return bankAccount;
    }

    public void setBankAccount(StringFilter bankAccount) {
        this.bankAccount = bankAccount;
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
        final BankCriteria that = (BankCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(bankName, that.bankName) &&
            Objects.equals(bankAccount, that.bankAccount) &&
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
        return Objects.hash(id, bankName, bankAccount, editBy, editDate, createdBy, createsDate, transactionId, accountuserId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (bankName != null ? "bankName=" + bankName + ", " : "") +
            (bankAccount != null ? "bankAccount=" + bankAccount + ", " : "") +
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
