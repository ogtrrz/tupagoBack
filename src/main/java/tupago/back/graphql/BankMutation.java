package tupago.back.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import tupago.back.graphql.generated.DgsConstants;
import tupago.back.graphql.generated.types.BankInput;
import tupago.back.security.SecurityUtils;
import tupago.back.service.BankQueryService;
import tupago.back.service.BankService;
import tupago.back.service.criteria.BankCriteria;
import tupago.back.service.dto.BankDTO;
import tupago.back.web.rest.BankResource;

@DgsComponent
public class BankMutation {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    //    private static final String ENTITY_NAME = "bank";

    //    @Value("${jhipster.clientApp.name}")
    //    private String applicationName;

    private final BankService bankService;

    private final BankQueryService bankQueryService;

    public BankMutation(BankService bankService, BankQueryService bankQueryService) {
        this.bankService = bankService;
        this.bankQueryService = bankQueryService;
    }

    /*
    addBank(bankInput: BankInput!) : Int
    replaceBank(bankInput: BankInput!) : Bank
    deleteBank(id: ID!): Boolean
     */

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.DeleteBank)
    //    @DgsMutation
    public boolean deleteBank(String id) {
        log.debug("GRAPHQL request to DELTE Bank : {}", id);
        BankCriteria criteria = new BankCriteria();
        criteria.bankAccount().setEquals(id);
        List<BankDTO> bankList = bankQueryService.findByCriteria(criteria);
        try {
            bankList.forEach(bank -> {
                bankService.delete(bank.getId());
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.AddBank)
    @DgsMutation
    public Long addBank(
        @InputArgument(name = "bankInput") BankInput bankInput,
        @RequestHeader(name = "X-XSRF-TOKEN", required = false) Optional<String> token
    ) {
        Instant lt = Instant.now();
        String userLogin = SecurityUtils.getCurrentUserLogin().orElse("Anonimo");

        BankDTO bank = new BankDTO();
        //        bank.setAccountuser();
        bank.setBankAccount(bankInput.getBankAccount());
        bank.setBankName(bankInput.getBankName());
        bank.setCreatedBy(userLogin);
        bank.setCreatesDate(lt);
        bank.setEditBy(token.orElse("Sin Valor en Header"));
        bank.setEditDate(lt);
        bankService.save(bank);
        BankCriteria criteria = new BankCriteria();
        criteria.bankName().setNotEquals(">");
        return bankQueryService.countByCriteria(criteria);
    }

    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ReplaceBank)
    //    @DgsMutation
    public BankDTO replaceBank(@InputArgument(name = "bankInput") BankInput bankInput) {
        BankCriteria criteria = new BankCriteria();
        criteria.bankAccount().setEquals(bankInput.getBankAccount());
        List<BankDTO> bankList = bankQueryService.findByCriteria(criteria);
        if (!bankList.isEmpty()) {
            String userLogin = SecurityUtils.getCurrentUserLogin().orElse("Anonimo");
            Instant lt = Instant.now();
            BankDTO bankDTO = bankList.get(0);
            bankDTO.setBankName(bankInput.getBankName());
            //            bankDTO.setBankAccount(bankInput.getBankAccount());
            bankDTO.setEditDate(lt);
            bankDTO.setEditBy(userLogin);
            Optional<BankDTO> res = bankService.partialUpdate(bankDTO);
            if (res.isPresent()) {
                return res.get();
            }
        }
        return null;
    }
}
