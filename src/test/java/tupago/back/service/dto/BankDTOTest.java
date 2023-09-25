package tupago.back.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tupago.back.web.rest.TestUtil;

class BankDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankDTO.class);
        BankDTO bankDTO1 = new BankDTO();
        bankDTO1.setId(1L);
        BankDTO bankDTO2 = new BankDTO();
        assertThat(bankDTO1).isNotEqualTo(bankDTO2);
        bankDTO2.setId(bankDTO1.getId());
        assertThat(bankDTO1).isEqualTo(bankDTO2);
        bankDTO2.setId(2L);
        assertThat(bankDTO1).isNotEqualTo(bankDTO2);
        bankDTO1.setId(null);
        assertThat(bankDTO1).isNotEqualTo(bankDTO2);
    }
}
