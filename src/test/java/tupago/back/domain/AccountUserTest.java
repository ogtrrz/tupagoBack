package tupago.back.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tupago.back.web.rest.TestUtil;

class AccountUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AccountUser.class);
        AccountUser accountUser1 = new AccountUser();
        accountUser1.setId(1L);
        AccountUser accountUser2 = new AccountUser();
        accountUser2.setId(accountUser1.getId());
        assertThat(accountUser1).isEqualTo(accountUser2);
        accountUser2.setId(2L);
        assertThat(accountUser1).isNotEqualTo(accountUser2);
        accountUser1.setId(null);
        assertThat(accountUser1).isNotEqualTo(accountUser2);
    }
}
