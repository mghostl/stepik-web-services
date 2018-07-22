package com.mghostl.education.stepik.webservices.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ServletUtils {
    private ServletUtils () {}

    public static Map<String, String> getParamsMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> params.put(key, value[0]));
        return params;
    }
}
