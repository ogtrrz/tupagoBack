package tupago.back.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tupago.back.web.rest.TestUtil;

class AccountUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountUserDTO.class);
        AccountUserDTO accountUserDTO1 = new AccountUserDTO();
        accountUserDTO1.setId(1L);
        AccountUserDTO accountUserDTO2 = new AccountUserDTO();
        assertThat(accountUserDTO1).isNotEqualTo(accountUserDTO2);
        accountUserDTO2.setId(accountUserDTO1.getId());
        assertThat(accountUserDTO1).isEqualTo(accountUserDTO2);
        accountUserDTO2.setId(2L);
        assertThat(accountUserDTO1).isNotEqualTo(accountUserDTO2);
        accountUserDTO1.setId(null);
        assertThat(accountUserDTO1).isNotEqualTo(accountUserDTO2);
    }
}
