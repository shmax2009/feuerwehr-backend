package com.feuerwehr.kleiderkammer.domain.models.clothes;

public class Parser {


    public static <T> T parse(Class<T> tClass, String value) {


        return switch (tClass.getSimpleName()) {
            case "Integer" -> (T) parseInt(value);
            case "String" -> (T) parseString(value);
            case "Boolean" -> (T) parseBoolean(value);
            default -> throw new     IllegalStateException("Unexpected value: " + tClass.getSimpleName());
        };
    }


    public static Integer parseInt(String value) {
        return Integer.parseInt(value);
    }

    public static String parseString(String value) {
        return value;
    }


    public static Boolean parseBoolean(String value) {
        return Boolean.parseBoolean(value);
    }
}


/*
 *   Integer x = helmet.getHeader("header_name").parse(Integer.class);
 * */