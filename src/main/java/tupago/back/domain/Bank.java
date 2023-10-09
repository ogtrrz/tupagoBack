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
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

/**
 * A Bank.
 */
@Entity
@Table(name = "bank")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Bank implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "bank_name", length = 100, nullable = false)
    private String bankName;

    @NotNull
    @Size(max = 50)
    @Column(name = "bank_account", length = 50, nullable = false)
    private String bankAccount;

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
//    @CreatedBy
    private String createdBy;

    @NotNull
    @Column(name = "creates_date", nullable = false)
//    @CreationTimestamp
    private Instant createsDate;

    @OneToMany(mappedBy = "bank")
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

    public Bank id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return this.bankName;
    }

    public Bank bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return this.bankAccount;
    }

    public Bank bankAccount(String bankAccount) {
        this.setBankAccount(bankAccount);
        return this;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getEditBy() {
        return this.editBy;
    }

    public Bank editBy(String editBy) {
        this.setEditBy(editBy);
        return this;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Instant getEditDate() {
        return this.editDate;
    }

    public Bank editDate(Instant editDate) {
        this.setEditDate(editDate);
        return this;
    }

    public void setEditDate(Instant editDate) {
        this.editDate = editDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Bank createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatesDate() {
        return this.createsDate;
    }

    public Bank createsDate(Instant createsDate) {
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
            this.transactions.forEach(i -> i.setBank(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setBank(this));
        }
        this.transactions = transactions;
    }

    public Bank transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public Bank addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setBank(this);
        return this;
    }

    public Bank removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setBank(null);
        return this;
    }

    public AccountUser getAccountuser() {
        return this.accountuser;
    }

    public void setAccountuser(AccountUser accountUser) {
        this.accountuser = accountUser;
    }

    public Bank accountuser(AccountUser accountUser) {
        this.setAccountuser(accountUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        return id != null && id.equals(((Bank) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bank{" +
            "id=" + getId() +
            ", bankName='" + getBankName() + "'" +
            ", bankAccount='" + getBankAccount() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            "}";
    }
}
