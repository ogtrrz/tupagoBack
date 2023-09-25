package tupago.back.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tupago.back.domain.AccountUser} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AccountUserDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 100)
    private String user;

    @NotNull
    private UUID userAccount;

    @NotNull
    private Instant inscription;

    @NotNull
    @Size(min = 10, max = 20)
    private String userTelephone;

    @NotNull
    @Size(max = 100)
    private String userName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public UUID getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UUID userAccount) {
        this.userAccount = userAccount;
    }

    public Instant getInscription() {
        return inscription;
    }

    public void setInscription(Instant inscription) {
        this.inscription = inscription;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AccountUserDTO)) {
            return false;
        }

        AccountUserDTO accountUserDTO = (AccountUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, accountUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AccountUserDTO{" +
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
