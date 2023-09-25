package tupago.back.component;

import com.netflix.graphql.dgs.DgsComponent;
//import tupago.back.domain.Bank;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import tech.jhipster.web.util.ResponseUtil;
import tupago.back.repository.BankRepository;
import tupago.back.service.BankService;
import tupago.back.service.dto.BankDTO;
import tupago.back.web.rest.BankResource;

@DgsComponent
public class BankResolver {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private static final String ENTITY_NAME = "bank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankService bankService;

    private final BankRepository bankRepository;

    public BankResolver(BankService bankService, BankRepository bankRepository) {
        this.bankService = bankService;
        this.bankRepository = bankRepository;
    }

    @DgsQuery
    public List<BankDTO> allBanks() {
        log.debug("REST request to get all Banks");
        return bankService.findAll();
    }

    //    @DgsData(parentType = "Query", field )
    @DgsQuery
    public BankDTO oneBank(Long id) {
        log.debug("REST request to get Bank : {}", id);
        Optional<BankDTO> bankDTO = bankService.findOne(id);
        if (bankDTO.isPresent()) {
            return bankDTO.get();
        }
        return null;
    }
}
