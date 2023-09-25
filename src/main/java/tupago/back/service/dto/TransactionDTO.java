package tupago.back.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;
import tupago.back.domain.enumeration.Status;

/**
 * A DTO for the {@link tupago.back.domain.Transaction} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransactionDTO implements Serializable {

    @NotNull
    private Long id;

    private Instant inicialDate;

    private Instant creadoDate;

    private Instant enviadoDate;

    private Instant errorDate;

    private Instant pagadoDate;

    @NotNull
    private Status status;

    @NotNull
    private String reference;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Boolean type;

    @NotNull
    private String from;

    @NotNull
    private String accountFrom;

    private String referenceFrom;

    private String messageFrom;

    private String paymentString;

    @NotNull
    @Size(max = 100)
    private String editBy;

    @NotNull
    private Instant editDate;

    @NotNull
    @Size(max = 100)
    private String createdBy;

    @NotNull
    private Instant createsDate;

    private AccountUserDTO accountuser;

    private BankDTO bank;

    private ClientConnectDTO clientconnect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInicialDate() {
        return inicialDate;
    }

    public void setInicialDate(Instant inicialDate) {
        this.inicialDate = inicialDate;
    }

    public Instant getCreadoDate() {
        return creadoDate;
    }

    public void setCreadoDate(Instant creadoDate) {
        this.creadoDate = creadoDate;
    }

    public Instant getEnviadoDate() {
        return enviadoDate;
    }

    public void setEnviadoDate(Instant enviadoDate) {
        this.enviadoDate = enviadoDate;
    }

    public Instant getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Instant errorDate) {
        this.errorDate = errorDate;
    }

    public Instant getPagadoDate() {
        return pagadoDate;
    }

    public void setPagadoDate(Instant pagadoDate) {
        this.pagadoDate = pagadoDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getReferenceFrom() {
        return referenceFrom;
    }

    public void setReferenceFrom(String referenceFrom) {
        this.referenceFrom = referenceFrom;
    }

    public String getMessageFrom() {
        return messageFrom;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getPaymentString() {
        return paymentString;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Instant getEditDate() {
        return editDate;
    }

    public void setEditDate(Instant editDate) {
        this.editDate = editDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatesDate() {
        return createsDate;
    }

    public void setCreatesDate(Instant createsDate) {
        this.createsDate = createsDate;
    }

    public AccountUserDTO getAccountuser() {
        return accountuser;
    }

    public void setAccountuser(AccountUserDTO accountuser) {
        this.accountuser = accountuser;
    }

    public BankDTO getBank() {
        return bank;
    }

    public void setBank(BankDTO bank) {
        this.bank = bank;
    }

    public ClientConnectDTO getClientconnect() {
        return clientconnect;
    }

    public void setClientconnect(ClientConnectDTO clientconnect) {
        this.clientconnect = clientconnect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionDTO)) {
            return false;
        }

        TransactionDTO transactionDTO = (TransactionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, transactionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransactionDTO{" +
            "id=" + getId() +
            ", inicialDate='" + getInicialDate() + "'" +
            ", creadoDate='" + getCreadoDate() + "'" +
            ", enviadoDate='" + getEnviadoDate() + "'" +
            ", errorDate='" + getErrorDate() + "'" +
            ", pagadoDate='" + getPagadoDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", reference='" + getReference() + "'" +
            ", amount=" + getAmount() +
            ", type='" + getType() + "'" +
            ", from='" + getFrom() + "'" +
            ", accountFrom='" + getAccountFrom() + "'" +
            ", referenceFrom='" + getReferenceFrom() + "'" +
            ", messageFrom='" + getMessageFrom() + "'" +
            ", paymentString='" + getPaymentString() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            ", accountuser=" + getAccountuser() +
            ", bank=" + getBank() +
            ", clientconnect=" + getClientconnect() +
            "}";
    }
}
