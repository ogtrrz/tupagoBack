package tupago.back.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tupago.back.domain.Bank} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BankDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 100)
    private String bankName;

    @NotNull
    @Size(max = 50)
    private String bankAccount;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BankDTO)) {
            return false;
        }

        BankDTO bankDTO = (BankDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bankDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BankDTO{" +
            "id=" + getId() +
            ", bankName='" + getBankName() + "'" +
            ", bankAccount='" + getBankAccount() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            ", accountuser=" + getAccountuser() +
            "}";
    }
}
