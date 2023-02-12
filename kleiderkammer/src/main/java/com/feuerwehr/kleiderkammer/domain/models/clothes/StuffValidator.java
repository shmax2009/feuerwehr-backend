package com.feuerwehr.kleiderkammer.domain.models.clothes;

import com.feuerwehr.kleiderkammer.domain.repository.clothes.StuffRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@Slf4j
@AllArgsConstructor
public class StuffValidator {
    private final StuffRepository stuffRepository;


    public String validateStuffForSave(Stuff stuff) {

        if (stuff == null)
            return "Can not save stuff, this stuff is null";

        if (stuff.getStuffType() == null)
            return "Can not save stuff, stuff type is null";

        if (stuff.getBatchCode() == null)
            return "Can not save stuff, stuff's batchCode is null";

        if (stuffRepository.findByBatchCode(stuff.getBatchCode()).isPresent())
            return "Can not save stuff, stuff's batchCode already in use";


        if (stuff.getId() != null)
            return "Can not save stuff,you can not set ids";

        if (stuff.getClothesId() != null)
            return "Can not save stuff,you can not set clothes id";

        if (stuff.getPersonType() != null)
            return "Can not save stuff,you can not set clothes person type";

        return validateStuff(stuff);
    }


    public String validateStuffForFetch(Stuff stuff) {

        if (stuff == null)
            return "Can not fetch stuff, this stuff is null";

        if (stuff.getStuffType() == null)
            return "Can not fetch stuff, stuff type is null";

        if (stuff.getBatchCode() == null)
            return "Can not fetch stuff, stuff's batchCode is null";

        if (stuff.getId() == null)
            return "Can not fetch stuff, can not find stuff(id is null)";


        var currentStuffOpt = stuffRepository.findById(stuff.getId());

        if (currentStuffOpt.isEmpty())
            return "Can not fetch stuff, can not find stuff(not exist id)";

        var curentStuff = currentStuffOpt.get();

        if (curentStuff.getStuffType() != stuff.getStuffType())
            return "Can not fetch stuff, you can not change stuff type";

        if (!Objects.equals(null, stuff.getClothesId()))
            return "Can not fetch stuff, you can not change person to whom belongs this stuff";

        if (!Objects.equals(null, stuff.getPersonType()))
            return "Can not fetch stuff, you can not change person to whom belongs this stuff";

        stuff.setPersonType(curentStuff.getPersonType());
        stuff.setClothesId(curentStuff.getClothesId());

        if (!Objects.equals(curentStuff.getBatchCode(), stuff.getBatchCode())) {
            if (stuffRepository.findByBatchCode(stuff.getBatchCode()).isPresent())
                return "Can not fetch stuff,you can not use already exist batchCode from another stuff";
        }

        return validateStuff(stuff);
    }


    private static String validateStuff(Stuff stuff) {
        switch (stuff.getStuffType()) {

            case Helm -> {
                return helmValidator(stuff);
            }

            case Einsatzjacke -> {
                return combatJacketValidator(stuff);
            }

            case Gurt -> {
                return beltValidator(stuff);
            }

            default -> {
                return null;
            }
        }
    }


    static String helmValidator(Stuff stuff) {
        String visierResult = validateParameter(stuff, "Visier", Boolean.class);
        String helmlampeResult = validateParameter(stuff, "Helmlampe", Boolean.class);

        if (visierResult != null) {
            return "Can not save Helm, " + visierResult;
        }
        if (helmlampeResult != null) {
            return "Can not save Helm, " + helmlampeResult;
        }
        return null;
    }

    static String combatJacketValidator(Stuff stuff) {
        String kollerResult = validateParameter(stuff, "Koller", Boolean.class);

        if (kollerResult != null) {
            return "can not save Einsatzjacke, " + kollerResult;
        }

        return null;
    }

    static String beltValidator(Stuff stuff) {
        String beil_tascheResult = validateParameter(stuff, "Beil+Tasche", Boolean.class);

        if (beil_tascheResult != null) {
            return "can not save Gurt, " + beil_tascheResult;
        }

        return null;
    }


    static <T> String validateParameter(Stuff stuff, String name, Class<T> type) {
        var parameter = stuff.getParameter(name);
        if (parameter == null)
            return "non exist parameter " + name;

        if (parameter.getType() == null)
            return "non exist type";

        if (parameter.getValue() == null)
            return "non exist value";

        if (!Objects.equals(parameter.getType(), type.getSimpleName()))
            return "type of parameter " + name + " is " + parameter.getType() + " instead of " + type.getSimpleName();

        return null;
    }


}
