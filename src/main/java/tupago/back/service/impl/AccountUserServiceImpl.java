package tupago.back.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tupago.back.domain.AccountUser;
import tupago.back.repository.AccountUserRepository;
import tupago.back.service.AccountUserService;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.mapper.AccountUserMapper;

/**
 * Service Implementation for managing {@link AccountUser}.
 */
@Service
@Transactional
public class AccountUserServiceImpl implements AccountUserService {

    private final Logger log = LoggerFactory.getLogger(AccountUserServiceImpl.class);

    private final AccountUserRepository accountUserRepository;

    private final AccountUserMapper accountUserMapper;

    public AccountUserServiceImpl(AccountUserRepository accountUserRepository, AccountUserMapper accountUserMapper) {
        this.accountUserRepository = accountUserRepository;
        this.accountUserMapper = accountUserMapper;
    }

    @Override
    public AccountUserDTO save(AccountUserDTO accountUserDTO) {
        log.debug("Request to save AccountUser : {}", accountUserDTO);
        AccountUser accountUser = accountUserMapper.toEntity(accountUserDTO);
        accountUser = accountUserRepository.save(accountUser);
        return accountUserMapper.toDto(accountUser);
    }

    @Override
    public AccountUserDTO update(AccountUserDTO accountUserDTO) {
        log.debug("Request to update AccountUser : {}", accountUserDTO);
        AccountUser accountUser = accountUserMapper.toEntity(accountUserDTO);
        accountUser = accountUserRepository.save(accountUser);
        return accountUserMapper.toDto(accountUser);
    }

    @Override
    public Optional<AccountUserDTO> partialUpdate(AccountUserDTO accountUserDTO) {
        log.debug("Request to partially update AccountUser : {}", accountUserDTO);

        return accountUserRepository
            .findById(accountUserDTO.getId())
            .map(existingAccountUser -> {
                accountUserMapper.partialUpdate(existingAccountUser, accountUserDTO);

                return existingAccountUser;
            })
            .map(accountUserRepository::save)
            .map(accountUserMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountUserDTO> findAll() {
        log.debug("Request to get all AccountUsers");
        return accountUserRepository.findAll().stream().map(accountUserMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountUserDTO> findOne(Long id) {
        log.debug("Request to get AccountUser : {}", id);
        return accountUserRepository.findById(id).map(accountUserMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AccountUser : {}", id);
        accountUserRepository.deleteById(id);
    }
}
