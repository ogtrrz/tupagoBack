package tupago.back.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;
import tupago.back.domain.enumeration.Status;

/**
 * Criteria class for the {@link tupago.back.domain.Transaction} entity. This class is used
 * in {@link tupago.back.web.rest.TransactionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /transactions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionCriteria implements Serializable, Criteria {

    /**
     * Class for filtering Status
     */
    public static class StatusFilter extends Filter<Status> {

        public StatusFilter() {}

        public StatusFilter(StatusFilter filter) {
            super(filter);
        }

        @Override
        public StatusFilter copy() {
            return new StatusFilter(this);
        }
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private InstantFilter inicialDate;

    private InstantFilter creadoDate;

    private InstantFilter enviadoDate;

    private InstantFilter errorDate;

    private InstantFilter pagadoDate;

    private StatusFilter status;

    private StringFilter reference;

    private BigDecimalFilter amount;

    private BooleanFilter type;

    private StringFilter from;

    private StringFilter accountFrom;

    private StringFilter referenceFrom;

    private StringFilter messageFrom;

    private StringFilter paymentString;

    private StringFilter editBy;

    private InstantFilter editDate;

    private StringFilter createdBy;

    private InstantFilter createsDate;

    private LongFilter accountuserId;

    private LongFilter bankId;

    private LongFilter clientconnectId;

    private Boolean distinct;

    public TransactionCriteria() {}

    public TransactionCriteria(TransactionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.inicialDate = other.inicialDate == null ? null : other.inicialDate.copy();
        this.creadoDate = other.creadoDate == null ? null : other.creadoDate.copy();
        this.enviadoDate = other.enviadoDate == null ? null : other.enviadoDate.copy();
        this.errorDate = other.errorDate == null ? null : other.errorDate.copy();
        this.pagadoDate = other.pagadoDate == null ? null : other.pagadoDate.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.reference = other.reference == null ? null : other.reference.copy();
        this.amount = other.amount == null ? null : other.amount.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.from = other.from == null ? null : other.from.copy();
        this.accountFrom = other.accountFrom == null ? null : other.accountFrom.copy();
        this.referenceFrom = other.referenceFrom == null ? null : other.referenceFrom.copy();
        this.messageFrom = other.messageFrom == null ? null : other.messageFrom.copy();
        this.paymentString = other.paymentString == null ? null : other.paymentString.copy();
        this.editBy = other.editBy == null ? null : other.editBy.copy();
        this.editDate = other.editDate == null ? null : other.editDate.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.createsDate = other.createsDate == null ? null : other.createsDate.copy();
        this.accountuserId = other.accountuserId == null ? null : other.accountuserId.copy();
        this.bankId = other.bankId == null ? null : other.bankId.copy();
        this.clientconnectId = other.clientconnectId == null ? null : other.clientconnectId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public TransactionCriteria copy() {
        return new TransactionCriteria(this);
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

    public InstantFilter getInicialDate() {
        return inicialDate;
    }

    public InstantFilter inicialDate() {
        if (inicialDate == null) {
            inicialDate = new InstantFilter();
        }
        return inicialDate;
    }

    public void setInicialDate(InstantFilter inicialDate) {
        this.inicialDate = inicialDate;
    }

    public InstantFilter getCreadoDate() {
        return creadoDate;
    }

    public InstantFilter creadoDate() {
        if (creadoDate == null) {
            creadoDate = new InstantFilter();
        }
        return creadoDate;
    }

    public void setCreadoDate(InstantFilter creadoDate) {
        this.creadoDate = creadoDate;
    }

    public InstantFilter getEnviadoDate() {
        return enviadoDate;
    }

    public InstantFilter enviadoDate() {
        if (enviadoDate == null) {
            enviadoDate = new InstantFilter();
        }
        return enviadoDate;
    }

    public void setEnviadoDate(InstantFilter enviadoDate) {
        this.enviadoDate = enviadoDate;
    }

    public InstantFilter getErrorDate() {
        return errorDate;
    }

    public InstantFilter errorDate() {
        if (errorDate == null) {
            errorDate = new InstantFilter();
        }
        return errorDate;
    }

    public void setErrorDate(InstantFilter errorDate) {
        this.errorDate = errorDate;
    }

    public InstantFilter getPagadoDate() {
        return pagadoDate;
    }

    public InstantFilter pagadoDate() {
        if (pagadoDate == null) {
            pagadoDate = new InstantFilter();
        }
        return pagadoDate;
    }

    public void setPagadoDate(InstantFilter pagadoDate) {
        this.pagadoDate = pagadoDate;
    }

    public StatusFilter getStatus() {
        return status;
    }

    public StatusFilter status() {
        if (status == null) {
            status = new StatusFilter();
        }
        return status;
    }

    public void setStatus(StatusFilter status) {
        this.status = status;
    }

    public StringFilter getReference() {
        return reference;
    }

    public StringFilter reference() {
        if (reference == null) {
            reference = new StringFilter();
        }
        return reference;
    }

    public void setReference(StringFilter reference) {
        this.reference = reference;
    }

    public BigDecimalFilter getAmount() {
        return amount;
    }

    public BigDecimalFilter amount() {
        if (amount == null) {
            amount = new BigDecimalFilter();
        }
        return amount;
    }

    public void setAmount(BigDecimalFilter amount) {
        this.amount = amount;
    }

    public BooleanFilter getType() {
        return type;
    }

    public BooleanFilter type() {
        if (type == null) {
            type = new BooleanFilter();
        }
        return type;
    }

    public void setType(BooleanFilter type) {
        this.type = type;
    }

    public StringFilter getFrom() {
        return from;
    }

    public StringFilter from() {
        if (from == null) {
            from = new StringFilter();
        }
        return from;
    }

    public void setFrom(StringFilter from) {
        this.from = from;
    }

    public StringFilter getAccountFrom() {
        return accountFrom;
    }

    public StringFilter accountFrom() {
        if (accountFrom == null) {
            accountFrom = new StringFilter();
        }
        return accountFrom;
    }

    public void setAccountFrom(StringFilter accountFrom) {
        this.accountFrom = accountFrom;
    }

    public StringFilter getReferenceFrom() {
        return referenceFrom;
    }

    public StringFilter referenceFrom() {
        if (referenceFrom == null) {
            referenceFrom = new StringFilter();
        }
        return referenceFrom;
    }

    public void setReferenceFrom(StringFilter referenceFrom) {
        this.referenceFrom = referenceFrom;
    }

    public StringFilter getMessageFrom() {
        return messageFrom;
    }

    public StringFilter messageFrom() {
        if (messageFrom == null) {
            messageFrom = new StringFilter();
        }
        return messageFrom;
    }

    public void setMessageFrom(StringFilter messageFrom) {
        this.messageFrom = messageFrom;
    }

    public StringFilter getPaymentString() {
        return paymentString;
    }

    public StringFilter paymentString() {
        if (paymentString == null) {
            paymentString = new StringFilter();
        }
        return paymentString;
    }

    public void setPaymentString(StringFilter paymentString) {
        this.paymentString = paymentString;
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
        final TransactionCriteria that = (TransactionCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(inicialDate, that.inicialDate) &&
            Objects.equals(creadoDate, that.creadoDate) &&
            Objects.equals(enviadoDate, that.enviadoDate) &&
            Objects.equals(errorDate, that.errorDate) &&
            Objects.equals(pagadoDate, that.pagadoDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(reference, that.reference) &&
            Objects.equals(amount, that.amount) &&
            Objects.equals(type, that.type) &&
            Objects.equals(from, that.from) &&
            Objects.equals(accountFrom, that.accountFrom) &&
            Objects.equals(referenceFrom, that.referenceFrom) &&
            Objects.equals(messageFrom, that.messageFrom) &&
            Objects.equals(paymentString, that.paymentString) &&
            Objects.equals(editBy, that.editBy) &&
            Objects.equals(editDate, that.editDate) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(createsDate, that.createsDate) &&
            Objects.equals(accountuserId, that.accountuserId) &&
            Objects.equals(bankId, that.bankId) &&
            Objects.equals(clientconnectId, that.clientconnectId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            inicialDate,
            creadoDate,
            enviadoDate,
            errorDate,
            pagadoDate,
            status,
            reference,
            amount,
            type,
            from,
            accountFrom,
            referenceFrom,
            messageFrom,
            paymentString,
            editBy,
            editDate,
            createdBy,
            createsDate,
            accountuserId,
            bankId,
            clientconnectId,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (inicialDate != null ? "inicialDate=" + inicialDate + ", " : "") +
            (creadoDate != null ? "creadoDate=" + creadoDate + ", " : "") +
            (enviadoDate != null ? "enviadoDate=" + enviadoDate + ", " : "") +
            (errorDate != null ? "errorDate=" + errorDate + ", " : "") +
            (pagadoDate != null ? "pagadoDate=" + pagadoDate + ", " : "") +
            (status != null ? "status=" + status + ", " : "") +
            (reference != null ? "reference=" + reference + ", " : "") +
            (amount != null ? "amount=" + amount + ", " : "") +
            (type != null ? "type=" + type + ", " : "") +
            (from != null ? "from=" + from + ", " : "") +
            (accountFrom != null ? "accountFrom=" + accountFrom + ", " : "") +
            (referenceFrom != null ? "referenceFrom=" + referenceFrom + ", " : "") +
            (messageFrom != null ? "messageFrom=" + messageFrom + ", " : "") +
            (paymentString != null ? "paymentString=" + paymentString + ", " : "") +
            (editBy != null ? "editBy=" + editBy + ", " : "") +
            (editDate != null ? "editDate=" + editDate + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (createsDate != null ? "createsDate=" + createsDate + ", " : "") +
            (accountuserId != null ? "accountuserId=" + accountuserId + ", " : "") +
            (bankId != null ? "bankId=" + bankId + ", " : "") +
            (clientconnectId != null ? "clientconnectId=" + clientconnectId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
