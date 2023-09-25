package tupago.back.service.mapper;

import org.mapstruct.*;
import tupago.back.domain.AccountUser;
import tupago.back.service.dto.AccountUserDTO;

/**
 * Mapper for the entity {@link AccountUser} and its DTO {@link AccountUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface AccountUserMapper extends EntityMapper<AccountUserDTO, AccountUser> {}
