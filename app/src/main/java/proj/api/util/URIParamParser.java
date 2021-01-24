package proj.api.util;

import proj.api.exceptions.WrongURIFormatException;

import java.util.HashMap;
import java.util.Map;

public class URIParamParser {
    public static final String REGEX_1 = "(.*?)\\((.*?)\\)";
    public static final String REGEX_2 = "\\w";

    public static Map<String, Map<String, String>> parse(String sortingParam) {
        String[] sortingParamsArray = sortingParam.split(",");
        Map<String, Map<String, String>> sortingParamsMap = new HashMap<>();
        for (String parameter : sortingParamsArray) {
            if (parameter.matches(REGEX_1)) {
                sortingParamsMap.put(parameter.substring(0, parameter.indexOf('(')),
                        tune(parameter.substring(parameter.indexOf('(') + 1, parameter.length() - 1)));
                continue;
            }
            if (parameter.matches(REGEX_2)) {
                sortingParamsMap.put(parameter, new HashMap<>());
            }
            throw new WrongURIFormatException("Your URI is in wrong format!");
        }
        return sortingParamsMap;
    }

    private static Map<String, String> tune(String tuningParams) {
        Map<String, String> tuningParamsMap = new HashMap<>();
        String[] tuningParamsArray = tuningParams.split(",");
        for (String param : tuningParamsArray) {
            String[] split = param.split("=");
            tuningParamsMap.put(split[0], split[1]);
        }
        return tuningParamsMap;
    }
}
