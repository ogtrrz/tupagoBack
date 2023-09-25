package tupago.back.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link tupago.back.domain.ClientConnect} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientConnectDTO implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 100)
    private String type;

    @NotNull
    @Size(max = 100)
    private String identifier;

    @Size(max = 100)
    private String located;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLocated() {
        return located;
    }

    public void setLocated(String located) {
        this.located = located;
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
        if (!(o instanceof ClientConnectDTO)) {
            return false;
        }

        ClientConnectDTO clientConnectDTO = (ClientConnectDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientConnectDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientConnectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", located='" + getLocated() + "'" +
            ", editBy='" + getEditBy() + "'" +
            ", editDate='" + getEditDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createsDate='" + getCreatesDate() + "'" +
            ", accountuser=" + getAccountuser() +
            "}";
    }
}
