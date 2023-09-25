package tupago.back.service.mapper;

import org.mapstruct.*;
import tupago.back.domain.AccountUser;
import tupago.back.domain.Bank;
import tupago.back.domain.ClientConnect;
import tupago.back.domain.Transaction;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.dto.BankDTO;
import tupago.back.service.dto.ClientConnectDTO;
import tupago.back.service.dto.TransactionDTO;

/**
 * Mapper for the entity {@link Transaction} and its DTO {@link TransactionDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransactionMapper extends EntityMapper<TransactionDTO, Transaction> {
    @Mapping(target = "accountuser", source = "accountuser", qualifiedByName = "accountUserId")
    @Mapping(target = "bank", source = "bank", qualifiedByName = "bankId")
    @Mapping(target = "clientconnect", source = "clientconnect", qualifiedByName = "clientConnectId")
    TransactionDTO toDto(Transaction s);

    @Named("accountUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUserDTO toDtoAccountUserId(AccountUser accountUser);

    @Named("bankId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    BankDTO toDtoBankId(Bank bank);

    @Named("clientConnectId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientConnectDTO toDtoClientConnectId(ClientConnect clientConnect);
}
