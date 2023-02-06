package com.feuerwehr.kleiderkammer;

import com.feuerwehr.kleiderkammer.domain.models.Adult;
import com.feuerwehr.kleiderkammer.domain.models.AdultClothes;
import com.feuerwehr.kleiderkammer.domain.models.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Parameter;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.services.StoreGetService;
import com.feuerwehr.kleiderkammer.services.StoreSaveService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class KleiderkammerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KleiderkammerApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(StoreSaveService storeSaveService, StoreGetService storeGetService) {
        return args -> {

            storeSaveService.saveAdult(Adult.builder()
                .adultInfo(
                    AdultInfo.builder()
                        .name("Maksym")
                        .surname("Shvedchenko")
                        .age(14L)
                        .build()
                )
                .adultClothes(
                    AdultClothes.builder()
                        .helmet(Stuff.builder()
                            .model("The best")
                            .batchCode(777777)
                            .size(3000)
                            .additionalInfo("The best")
                            .type(StuffType.Helm)
                            .parameter(new Parameter(null, Boolean.class.getSimpleName(), "X", "false"))
                            .build())
                        .combatJacket(Stuff.builder()
                            .model("The best 2")
                            .batchCode(666666)
                            .size(3000)
                            .additionalInfo("The best 2")
                            .type(StuffType.Einsatzjacke)
                            .build())
                        .build()
                )
                .build()
            );
            storeSaveService.saveAdult(Adult.builder()
                .adultInfo(
                    AdultInfo.builder()
                        .name("Chris")
                        .surname("Wegener")
                        .age(24L)
                        .build()
                )
                .build()
            );
//            storeSaveService.saveStuff(new Helmet(null, "Schuhbert", "fdfd", "fdfd", 213L, "fd"));
//            storeSaveService.saveStuff(new Jacket(null, "Schuhbert", "fdfd", "fdfd", 213L, "fd"));
//            storeSaveService.saveStuff(new PATrousers(null, "Schuhbert", "fdfd", "fdfd", 213L, "fd"));
//            storeSaveService.saveAdult(new Adult(storeSaveService.saveAdultInfo(new AdultInfo("Maksym", "Shvedchenko"
//                    , 14L))));
//
//            storeSaveService.addStuffToAdult(StuffKind.Helmet, 213L, "Maksym", "Shvedchenko");
//            storeSaveService.addStuffToAdult(StuffKind.Jacket, 213L, "Maksym", "Shvedchenko");
//            storeSaveService.addStuffToAdult(StuffKind.PATrousers, 213L, "Maksym", "Shvedchenko");
        };
    }


}
