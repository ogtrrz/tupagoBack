package tupago.back.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AccountUser.
 */
@Entity
@Table(name = "account_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AccountUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "jhi_user", length = 100, nullable = false)
    private String user;

    @NotNull
    @Column(name = "user_account", nullable = false)
    private UUID userAccount;

    @NotNull
    @Column(name = "inscription", nullable = false)
    private Instant inscription;

    @NotNull
    @Size(min = 10, max = 20)
    @Column(name = "user_telephone", length = 20, nullable = false)
    private String userTelephone;

    @NotNull
    @Size(max = 100)
    @Column(name = "user_name", length = 100, nullable = false)
    private String userName;

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

    @OneToMany(mappedBy = "accountuser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "transactions", "accountuser" }, allowSetters = true)
    private Set<Bank> banks = new HashSet<>();

    @OneToMany(mappedBy = "accountuser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "transactions", "accountuser" }, allowSetters = true)
    private Set<ClientConnect> clientconnects = new HashSet<>();

    @OneToMany(mappedBy = "accountuser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "accountuser", "bank", "clientconnect" }, allowSetters = true)
    private Set<Transaction> transactions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AccountUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return this.user;
    }

    public AccountUser user(String user) {
        this.setUser(user);
        return this;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UUID getUserAccount() {
        return this.userAccount;
    }

    public AccountUser userAccount(UUID userAccount) {
        this.setUserAccount(userAccount);
        return this;
    }

    public void setUserAccount(UUID userAccount) {
        this.userAccount = userAccount;
    }

    public Instant getInscription() {
        return this.inscription;
    }

    public AccountUser inscription(Instant inscription) {
        this.setInscription(inscription);
        return this;
    }

    public void setInscription(Instant inscription) {
        this.inscription = inscription;
    }

    public String getUserTelephone() {
        return this.userTelephone;
    }

    public AccountUser userTelephone(String userTelephone) {
        this.setUserTelephone(userTelephone);
        return this;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserName() {
        return this.userName;
    }

    public AccountUser userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEditBy() {
        return this.editBy;
    }

    public AccountUser editBy(String editBy) {
        this.setEditBy(editBy);
        return this;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public Instant getEditDate() {
        return this.editDate;
    }

    public AccountUser editDate(Instant editDate) {
        this.setEditDate(editDate);
        return this;
    }

    public void setEditDate(Instant editDate) {
        this.editDate = editDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AccountUser createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatesDate() {
        return this.createsDate;
    }

    public AccountUser createsDate(Instant createsDate) {
        this.setCreatesDate(createsDate);
        return this;
    }

    public void setCreatesDate(Instant createsDate) {
        this.createsDate = createsDate;
    }

    public Set<Bank> getBanks() {
        return this.banks;
    }

    public void setBanks(Set<Bank> banks) {
        if (this.banks != null) {
            this.banks.forEach(i -> i.setAccountuser(null));
        }
        if (banks != null) {
            banks.forEach(i -> i.setAccountuser(this));
        }
        this.banks = banks;
    }

    public AccountUser banks(Set<Bank> banks) {
        this.setBanks(banks);
        return this;
    }

    public AccountUser addBank(Bank bank) {
        this.banks.add(bank);
        bank.setAccountuser(this);
        return this;
    }

    public AccountUser removeBank(Bank bank) {
        this.banks.remove(bank);
        bank.setAccountuser(null);
        return this;
    }

    public Set<ClientConnect> getClientconnects() {
        return this.clientconnects;
    }

    public void setClientconnects(Set<ClientConnect> clientConnects) {
        if (this.clientconnects != null) {
            this.clientconnects.forEach(i -> i.setAccountuser(null));
        }
        if (clientConnects != null) {
            clientConnects.forEach(i -> i.setAccountuser(this));
        }
        this.clientconnects = clientConnects;
    }

    public AccountUser clientconnects(Set<ClientConnect> clientConnects) {
        this.setClientconnects(clientConnects);
        return this;
    }

    public AccountUser addClientconnect(ClientConnect clientConnect) {
        this.clientconnects.add(clientConnect);
        clientConnect.setAccountuser(this);
        return this;
    }

    public AccountUser removeClientconnect(ClientConnect clientConnect) {
        this.clientconnects.remove(clientConnect);
        clientConnect.setAccountuser(null);
        return this;
    }

    public Set<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        if (this.transactions != null) {
            this.transactions.forEach(i -> i.setAccountuser(null));
        }
        if (transactions != null) {
            transactions.forEach(i -> i.setAccountuser(this));
        }
        this.transactions = transactions;
    }

    public AccountUser transactions(Set<Transaction> transactions) {
        this.setTransactions(transactions);
        return this;
    }

    public AccountUser addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        transaction.setAccountuser(this);
        return this;
    }

    public AccountUser removeTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
        transaction.setAccountuser(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountUser)) {
            return false;
        }
        return id != null && id.equals(((AccountUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountUser{" +
            "id=" + getId() +
            ", user='" + getUser() + "'" +
            ", userAccount='" + getUserAccount() + "'" +
            ", inscription='" + getInscription() + "'" +
            ", userTelephone='" + getUserTelephone() + "'" +
            ", userName='" + getUserName() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            "}";
    }
}
