package tupago.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tupago.back.domain.enumeration.Status;

/**
 * A Transaction.
 */
@Entity
@Table(name = "transaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Transaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "inicial_date")
    private Instant inicialDate;

    @Column(name = "creado_date")
    private Instant creadoDate;

    @Column(name = "enviado_date")
    private Instant enviadoDate;

    @Column(name = "error_date")
    private Instant errorDate;

    @Column(name = "pagado_date")
    private Instant pagadoDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "reference", nullable = false)
    private String reference;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull
    @Column(name = "type", nullable = false)
    private Boolean type;

    @NotNull
    @Column(name = "jhi_from", nullable = false)
    private String from;

    @NotNull
    @Column(name = "account_from", nullable = false)
    private String accountFrom;

    @Column(name = "reference_from")
    private String referenceFrom;

    @Column(name = "message_from")
    private String messageFrom;

    @Column(name = "payment_string")
    private String paymentString;

    @NotNull
    @Size(max = 100)
    @Column(name = "edit_by", length = 100, nullable = false)
    private String editBy;

    @NotNull
    @Column(name = "edit_date", nullable = false)
    private Instant editDate;

    @NotNull
    @Size(max = 100)
    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "creates_date", nullable = false)
    private Instant createsDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "banks", "clientconnects", "transactions" }, allowSetters = true)
    private AccountUser accountuser;

    @ManyToOne
    @JsonIgnoreProperties(value = { "transactions", "accountuser" }, allowSetters = true)
    private Bank bank;

    @ManyToOne
    @JsonIgnoreProperties(value = { "transactions", "accountuser" }, allowSetters = true)
    private ClientConnect clientconnect;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Transaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInicialDate() {
        return this.inicialDate;
    }

    public Transaction inicialDate(Instant inicialDate) {
        this.setInicialDate(inicialDate);
        return this;
    }

    public void setInicialDate(Instant inicialDate) {
        this.inicialDate = inicialDate;
    }

    public Instant getCreadoDate() {
        return this.creadoDate;
    }

    public Transaction creadoDate(Instant creadoDate) {
        this.setCreadoDate(creadoDate);
        return this;
    }

    public void setCreadoDate(Instant creadoDate) {
        this.creadoDate = creadoDate;
    }

    public Instant getEnviadoDate() {
        return this.enviadoDate;
    }

    public Transaction enviadoDate(Instant enviadoDate) {
        this.setEnviadoDate(enviadoDate);
        return this;
    }

    public void setEnviadoDate(Instant enviadoDate) {
        this.enviadoDate = enviadoDate;
    }

    public Instant getErrorDate() {
        return this.errorDate;
    }

    public Transaction errorDate(Instant errorDate) {
        this.setErrorDate(errorDate);
        return this;
    }

    public void setErrorDate(Instant errorDate) {
        this.errorDate = errorDate;
    }

    public Instant getPagadoDate() {
        return this.pagadoDate;
    }

    public Transaction pagadoDate(Instant pagadoDate) {
        this.setPagadoDate(pagadoDate);
        return this;
    }

    public void setPagadoDate(Instant pagadoDate) {
        this.pagadoDate = pagadoDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public Transaction status(Status status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getReference() {
        return this.reference;
    }

    public Transaction reference(String reference) {
        this.setReference(reference);
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Transaction amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getType() {
        return this.type;
    }

    public Transaction type(Boolean type) {
        this.setType(type);
        return this;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getFrom() {
        return this.from;
    }

    public Transaction from(String from) {
        this.setFrom(from);
        return this;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAccountFrom() {
        return this.accountFrom;
    }

    public Transaction accountFrom(String accountFrom) {
        this.setAccountFrom(accountFrom);
        return this;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getReferenceFrom() {
        return this.referenceFrom;
    }

    public Transaction referenceFrom(String referenceFrom) {
        this.setReferenceFrom(referenceFrom);
        return this;
    }

    public void setReferenceFrom(String referenceFrom) {
        this.referenceFrom = referenceFrom;
    }

    public String getMessageFrom() {
        return this.messageFrom;
    }

    public Transaction messageFrom(String messageFrom) {
        this.setMessageFrom(messageFrom);
        return this;
    }

    public void setMessageFrom(String messageFrom) {
        this.messageFrom = messageFrom;
    }

    public String getPaymentString() {
        return this.paymentString;
    }

    public Transaction paymentString(String paymentString) {
        this.setPaymentString(paymentString);
        return this;
    }

    public void setPaymentString(String paymentString) {
        this.paymentString = paymentString;
    }

    public String getEditBy() {
        return this.editBy;
    }

    public Transaction editBy(String editBy) {
        this.setEditBy(editBy);
        return this;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Instant getEditDate() {
        return this.editDate;
    }

    public Transaction editDate(Instant editDate) {
        this.setEditDate(editDate);
        return this;
    }

    public void setEditDate(Instant editDate) {
        this.editDate = editDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Transaction createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatesDate() {
        return this.createsDate;
    }

    public Transaction createsDate(Instant createsDate) {
        this.setCreatesDate(createsDate);
        return this;
    }

    public void setCreatesDate(Instant createsDate) {
        this.createsDate = createsDate;
    }

    public AccountUser getAccountuser() {
        return this.accountuser;
    }

    public void setAccountuser(AccountUser accountUser) {
        this.accountuser = accountUser;
    }

    public Transaction accountuser(AccountUser accountUser) {
        this.setAccountuser(accountUser);
        return this;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Transaction bank(Bank bank) {
        this.setBank(bank);
        return this;
    }

    public ClientConnect getClientconnect() {
        return this.clientconnect;
    }

    public void setClientconnect(ClientConnect clientConnect) {
        this.clientconnect = clientConnect;
    }

    public Transaction clientconnect(ClientConnect clientConnect) {
        this.setClientconnect(clientConnect);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Transaction)) {
            return false;
        }
        return id != null && id.equals(((Transaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Transaction{" +
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
            "}";
    }
}
