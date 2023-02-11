package com.feuerwehr.kleiderkammer;

import com.feuerwehr.kleiderkammer.domain.enums.Achivement;
import com.feuerwehr.kleiderkammer.domain.enums.StuffType;
import com.feuerwehr.kleiderkammer.domain.models.adult.Adult;
import com.feuerwehr.kleiderkammer.domain.models.adult.AdultInfo;
import com.feuerwehr.kleiderkammer.domain.models.clothes.Stuff;
import com.feuerwehr.kleiderkammer.domain.models.kid.Kid;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidClothes;
import com.feuerwehr.kleiderkammer.domain.models.kid.KidInfo;
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
            /*
            storeSaveService.saveKid(Kid.builder()
                .info(KidInfo.builder()
                    .name("Maksym")
                    .surname("Shvedchenko")
                    .age(14L)
                    .build())

                .clothes(KidClothes.builder()
                    .pullover(Stuff.builder()
                        .model("Guuchi")
                        .size(777)
                        .type(StuffType.Pullover)
                        .build())
                    .build())
                .build());

            /*
            try {
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
                                .parameter(new Parameter(Boolean.class.getSimpleName(), "X", "false"))
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
                .info(
                    AdultInfo.builder()
                        .name("Chris")
                        .surname("Wegener")
                        .age(24L)
                        .achivement(Achivement.Atemschutz)
                        .build()
                )
                .build()
            );
            */
        };
    }


}
