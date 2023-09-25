package tupago.back.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tupago.back.domain.ClientConnect;
import tupago.back.repository.ClientConnectRepository;
import tupago.back.service.ClientConnectService;
import tupago.back.service.dto.ClientConnectDTO;
import tupago.back.service.mapper.ClientConnectMapper;

/**
 * Service Implementation for managing {@link ClientConnect}.
 */
@Service
@Transactional
public class ClientConnectServiceImpl implements ClientConnectService {

    private final Logger log = LoggerFactory.getLogger(ClientConnectServiceImpl.class);

    private final ClientConnectRepository clientConnectRepository;

    private final ClientConnectMapper clientConnectMapper;

    public ClientConnectServiceImpl(ClientConnectRepository clientConnectRepository, ClientConnectMapper clientConnectMapper) {
        this.clientConnectRepository = clientConnectRepository;
        this.clientConnectMapper = clientConnectMapper;
    }

    @Override
    public ClientConnectDTO save(ClientConnectDTO clientConnectDTO) {
        log.debug("Request to save ClientConnect : {}", clientConnectDTO);
        ClientConnect clientConnect = clientConnectMapper.toEntity(clientConnectDTO);
        clientConnect = clientConnectRepository.save(clientConnect);
        return clientConnectMapper.toDto(clientConnect);
    }

    @Override
    public ClientConnectDTO update(ClientConnectDTO clientConnectDTO) {
        log.debug("Request to update ClientConnect : {}", clientConnectDTO);
        ClientConnect clientConnect = clientConnectMapper.toEntity(clientConnectDTO);
        clientConnect = clientConnectRepository.save(clientConnect);
        return clientConnectMapper.toDto(clientConnect);
    }

    @Override
    public Optional<ClientConnectDTO> partialUpdate(ClientConnectDTO clientConnectDTO) {
        log.debug("Request to partially update ClientConnect : {}", clientConnectDTO);

        return clientConnectRepository
            .findById(clientConnectDTO.getId())
            .map(existingClientConnect -> {
                clientConnectMapper.partialUpdate(existingClientConnect, clientConnectDTO);

                return existingClientConnect;
            })
            .map(clientConnectRepository::save)
            .map(clientConnectMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientConnectDTO> findAll() {
        log.debug("Request to get all ClientConnects");
        return clientConnectRepository.findAll().stream().map(clientConnectMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientConnectDTO> findOne(Long id) {
        log.debug("Request to get ClientConnect : {}", id);
        return clientConnectRepository.findById(id).map(clientConnectMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientConnect : {}", id);
        clientConnectRepository.deleteById(id);
    }
}
