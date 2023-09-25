package tupago.back.service.mapper;

import org.mapstruct.*;
import tupago.back.domain.AccountUser;
import tupago.back.domain.ClientConnect;
import tupago.back.service.dto.AccountUserDTO;
import tupago.back.service.dto.ClientConnectDTO;

/**
 * Mapper for the entity {@link ClientConnect} and its DTO {@link ClientConnectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientConnectMapper extends EntityMapper<ClientConnectDTO, ClientConnect> {
    @Mapping(target = "accountuser", source = "accountuser", qualifiedByName = "accountUserId")
    ClientConnectDTO toDto(ClientConnect s);

    @Named("accountUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AccountUserDTO toDtoAccountUserId(AccountUser accountUser);
}
