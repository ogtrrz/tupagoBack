package tupago.back.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link tupago.back.domain.AccountUser} entity. This class is used
 * in {@link tupago.back.web.rest.AccountUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /account-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AccountUserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter user;

    private UUIDFilter userAccount;

    private InstantFilter inscription;

    private StringFilter userTelephone;

    private StringFilter userName;

    private StringFilter editBy;

    private InstantFilter editDate;

    private StringFilter createdBy;

    private InstantFilter createsDate;

    private LongFilter bankId;

    private LongFilter clientconnectId;

    private LongFilter transactionId;

    private Boolean distinct;

    public AccountUserCriteria() {}

    public AccountUserCriteria(AccountUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.user = other.user == null ? null : other.user.copy();
        this.userAccount = other.userAccount == null ? null : other.userAccount.copy();
        this.inscription = other.inscription == null ? null : other.inscription.copy();
        this.userTelephone = other.userTelephone == null ? null : other.userTelephone.copy();
        this.userName = other.userName == null ? null : other.userName.copy();
        this.editBy = other.editBy == null ? null : other.editBy.copy();
        this.editDate = other.editDate == null ? null : other.editDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createsDate = other.createsDate == null ? null : other.createsDate.copy();
        this.bankId = other.bankId == null ? null : other.bankId.copy();
        this.clientconnectId = other.clientconnectId == null ? null : other.clientconnectId.copy();
        this.transactionId = other.transactionId == null ? null : other.transactionId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AccountUserCriteria copy() {
        return new AccountUserCriteria(this);
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

    public StringFilter getUser() {
        return user;
    }

    public StringFilter user() {
        if (user == null) {
            user = new StringFilter();
        }
        return user;
    }

    public void setUser(StringFilter user) {
        this.user = user;
    }

    public UUIDFilter getUserAccount() {
        return userAccount;
    }

    public UUIDFilter userAccount() {
        if (userAccount == null) {
            userAccount = new UUIDFilter();
        }
        return userAccount;
    }

    public void setUserAccount(UUIDFilter userAccount) {
        this.userAccount = userAccount;
    }

    public InstantFilter getInscription() {
        return inscription;
    }

    public InstantFilter inscription() {
        if (inscription == null) {
            inscription = new InstantFilter();
        }
        return inscription;
    }

    public void setInscription(InstantFilter inscription) {
        this.inscription = inscription;
    }

    public StringFilter getUserTelephone() {
        return userTelephone;
    }

    public StringFilter userTelephone() {
        if (userTelephone == null) {
            userTelephone = new StringFilter();
        }
        return userTelephone;
    }

    public void setUserTelephone(StringFilter userTelephone) {
        this.userTelephone = userTelephone;
    }

    public StringFilter getUserName() {
        return userName;
    }

    public StringFilter userName() {
        if (userName == null) {
            userName = new StringFilter();
        }
        return userName;
    }

    public void setUserName(StringFilter userName) {
        this.userName = userName;
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

    public LongFilter getBankId() {
        return bankId;
    }

    public LongFilter bankId() {
        if (bankId == null) {
            bankId = new LongFilter();
        }
        return bankId;
    }

    public void setBankId(LongFilter bankId) {
        this.bankId = bankId;
    }

    public LongFilter getClientconnectId() {
        return clientconnectId;
    }

    public LongFilter clientconnectId() {
        if (clientconnectId == null) {
            clientconnectId = new LongFilter();
        }
        return clientconnectId;
    }

    public void setClientconnectId(LongFilter clientconnectId) {
        this.clientconnectId = clientconnectId;
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
        final AccountUserCriteria that = (AccountUserCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(user, that.user) &&
            Objects.equals(userAccount, that.userAccount) &&
            Objects.equals(inscription, that.inscription) &&
            Objects.equals(userTelephone, that.userTelephone) &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(editBy, that.editBy) &&
            Objects.equals(editDate, that.editDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createsDate, that.createsDate) &&
            Objects.equals(bankId, that.bankId) &&
            Objects.equals(clientconnectId, that.clientconnectId) &&
            Objects.equals(transactionId, that.transactionId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            user,
            userAccount,
            inscription,
            userTelephone,
            userName,
            editBy,
            editDate,
            createdBy,
            createsDate,
            bankId,
            clientconnectId,
            transactionId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (user != null ? "user=" + user + ", " : "") +
            (userAccount != null ? "userAccount=" + userAccount + ", " : "") +
            (inscription != null ? "inscription=" + inscription + ", " : "") +
            (userTelephone != null ? "userTelephone=" + userTelephone + ", " : "") +
            (userName != null ? "userName=" + userName + ", " : "") +
            (editBy != null ? "editBy=" + editBy + ", " : "") +
            (editDate != null ? "editDate=" + editDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createsDate != null ? "createsDate=" + createsDate + ", " : "") +
            (bankId != null ? "bankId=" + bankId + ", " : "") +
            (clientconnectId != null ? "clientconnectId=" + clientconnectId + ", " : "") +
            (transactionId != null ? "transactionId=" + transactionId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
