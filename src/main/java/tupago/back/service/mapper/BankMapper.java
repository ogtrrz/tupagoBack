package tupago.back.service.mapper;

import org.mapstruct.*;
import tupago.back.domain.AccountUser;
import tupago.back.domain.Bank;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.dto.BankDTO;

/**
 * Mapper for the entity {@link Bank} and its DTO {@link BankDTO}.
 */
@Mapper(componentModel = "spring")
public interface BankMapper extends EntityMapper<BankDTO, Bank> {
    @Mapping(target = "accountuser", source = "accountuser", qualifiedByName = "accountUserId")
    BankDTO toDto(Bank s);

    @Named("accountUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUserDTO toDtoAccountUserId(AccountUser accountUser);
}
