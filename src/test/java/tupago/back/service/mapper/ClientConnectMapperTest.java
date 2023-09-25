package tupago.back.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientConnectMapperTest {

    private ClientConnectMapper clientConnectMapper;

    @BeforeEach
    public void setUp() {
        clientConnectMapper = new ClientConnectMapperImpl();
    }
}
