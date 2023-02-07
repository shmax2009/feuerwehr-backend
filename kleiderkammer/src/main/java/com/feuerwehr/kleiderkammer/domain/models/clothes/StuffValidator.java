package com.feuerwehr.kleiderkammer.domain.models.clothes;

import java.util.Objects;

public class StuffValidator {

    public static String validateStuff(Stuff stuff) {

        if (stuff == null)
            return "Can not save stuff, this stuff is null";

        if (stuff.getStuffType() == null)
            return "Can not save stuff, stuff type is null";

        if (stuff.getBatchCode() == null)
            return "Can not save stuff, stuff's batchCode is null";


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
