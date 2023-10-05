package tupago.back.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import graphql.schema.DataFetchingEnvironment;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import tupago.back.graphql.generated.DgsConstants;
import tupago.back.graphql.generated.types.Bank;
import tupago.back.graphql.generated.types.BankFilter;
import tupago.back.service.BankQueryService;
import tupago.back.service.BankService;
import tupago.back.service.criteria.BankCriteria;
import tupago.back.service.dto.BankDTO;
import tupago.back.web.rest.BankResource;

@DgsComponent
public class BankResolver {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    //    private static final String ENTITY_NAME = "bank";
    //
    //    @Value("${jhipster.clientApp.name}")
    //    private String applicationName;

    private final BankService bankService;

    private final BankQueryService bankQueryService;

    public BankResolver(BankService bankService, BankQueryService bankQueryService) {
        this.bankService = bankService;
        this.bankQueryService = bankQueryService;
    }

    @DgsQuery
    public List<BankDTO> allBanks() {
        log.debug("GRAPHQL request to get all Banks");
        return bankService.findAll();
    }

    //    @DgsData(parentType = "Query", field )
    @DgsQuery
    public BankDTO oneBank(Long id) {
        log.debug("GRAPHQL request to get Bank : {}", id);
        Optional<BankDTO> bankDTO = bankService.findOne(id);
        if (bankDTO.isPresent()) {
            return bankDTO.get();
        }
        return null;
    }

    //    TransactionCriteria{id=LongFilter [greaterThan=3, ], status=StatusFilter [notIn=[INICIAL]], }

    @DgsQuery
    public List<BankDTO> filterBanks() {
        BankCriteria criteria = new BankCriteria();
        //        StringFilter sf = new StringFilter();
        //        sf.setContains("hola");
        criteria.bankName().setDoesNotContain("hola");
        //        criteria.createsDate().setEquals()
        log.debug("GRAPHQL request to get Banks by criteria: {}", criteria);
        return bankQueryService.findByCriteria(criteria);
    }

    @DgsQuery
    public List<BankDTO> bankByAccountUser(Long id) {
        log.debug("GRAPHQL request to get BankByAcoountUser Id : {}", id);
        BankCriteria criteria = new BankCriteria();
        criteria.accountuserId().setEquals(id);
        List<BankDTO> bankListDTO = bankQueryService.findByCriteria(criteria);
        //        if (!bankListDTO.isEmpty()) {
        return bankListDTO;
        //        }
        //        return null;
    }

    @DgsQuery
    public List<BankDTO> banksFilter(@InputArgument(name = "bankFilter", collectionType = BankFilter.class) Optional<BankFilter> filter) {
        if (filter.isEmpty()) {
            return bankService.findAll();
        }
        log.debug("GRAPHQL request to get FILTER filter : {}", filter.get().toString());
        //        BankCriteria criteria = new BankCriteria();
        //        criteria.bankName().se
        //        return bankQueryService.findByCriteria()
        return null;
    }
    //    @DgsComponent
    //    public class FilterBanksDatafetcher {
    //        @DgsData(
    //            parentType = DgsConstants.QUERY_TYPE,
    //            field = DgsConstants.QUERY.FilterBanks
    //        )

    //    @DgsData(parentType = "Bank", field = "bankName")
    //    public List<Bank> getFilterBanks(DataFetchingEnvironment dataFetchingEnvironment) {
    ////            var bankMap = (Map<String, Object>) dataFetchingEnvironment.getArgument("")
    //
    //        DataLoader<String, Director> dataLoader = dataFetchingEnvironment.getDataLoader(DirectorsDataLoader.class);
    //        String id = dfe.getArgument("directorId");
    //
    ////        return dataLoader.load(id);
    //
    //
    //        log.debug("GRAPHQL request to filter Bank : {}", dataFetchingEnvironment.getArguments());
    //        return null;
    //    }
    //    }

}
