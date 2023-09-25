package tupago.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClientConnect.
 */
@Entity
@Table(name = "client_connect")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientConnect implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "type", length = 100, nullable = false)
    private String type;

    @NotNull
    @Size(max = 100)
    @Column(name = "identifier", length = 100, nullable = false)
    private String identifier;

    @Size(max = 100)
    @Column(name = "located", length = 100)
    private String located;

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

    @OneToMany(mappedBy = "clientconnect")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "accountuser", "bank", "clientconnect" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "banks", "clientconnects", "transactions" }, allowSetters = true)
    private AccountUser accountuser;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientConnect id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public ClientConnect name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public ClientConnect type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ClientConnect identifier(String identifier) {
        this.setIdentifier(identifier);
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLocated() {
        return this.located;
    }

    public ClientConnect located(String located) {
        this.setLocated(located);
        return this;
    }

    public void setLocated(String located) {
        this.located = located;
    }

    public String getEditBy() {
        return this.editBy;
    }

    public ClientConnect editBy(String editBy) {
        this.setEditBy(editBy);
        return this;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Instant getEditDate() {
        return this.editDate;
    }

    public ClientConnect editDate(Instant editDate) {
        this.setEditDate(editDate);
        return this;
    }

    public void setEditDate(Instant editDate) {
        this.editDate = editDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ClientConnect createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatesDate() {
        return this.createsDate;
    }

    public ClientConnect createsDate(Instant createsDate) {
        this.setCreatesDate(createsDate);
        return this;
    }

    public void setCreatesDate(Instant createsDate) {
        this.createsDate = createsDate;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setClientconnect(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setClientconnect(this));
        }
        this.transactions = transactions;
    }

    public ClientConnect transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public ClientConnect addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setClientconnect(this);
        return this;
    }

    public ClientConnect removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setClientconnect(null);
        return this;
    }

    public AccountUser getAccountuser() {
        return this.accountuser;
    }

    public void setAccountuser(AccountUser accountUser) {
        this.accountuser = accountUser;
    }

    public ClientConnect accountuser(AccountUser accountUser) {
        this.setAccountuser(accountUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientConnect)) {
            return false;
        }
        return id != null && id.equals(((ClientConnect) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientConnect{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", located='" + getLocated() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            "}";
    }
}
