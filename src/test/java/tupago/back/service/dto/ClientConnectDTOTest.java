package tupago.back.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tupago.back.web.rest.TestUtil;

class ClientConnectDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientConnectDTO.class);
        ClientConnectDTO clientConnectDTO1 = new ClientConnectDTO();
        clientConnectDTO1.setId(1L);
        ClientConnectDTO clientConnectDTO2 = new ClientConnectDTO();
        assertThat(clientConnectDTO1).isNotEqualTo(clientConnectDTO2);
        clientConnectDTO2.setId(clientConnectDTO1.getId());
        assertThat(clientConnectDTO1).isEqualTo(clientConnectDTO2);
        clientConnectDTO2.setId(2L);
        assertThat(clientConnectDTO1).isNotEqualTo(clientConnectDTO2);
        clientConnectDTO1.setId(null);
        assertThat(clientConnectDTO1).isNotEqualTo(clientConnectDTO2);
    }
}
