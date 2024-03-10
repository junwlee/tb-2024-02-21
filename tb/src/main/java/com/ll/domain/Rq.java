package com.ll.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;
    private String queryString = "";
    private Map<String, String> params;
    public Rq(String cmd) {
        this.cmd = cmd;
        this.params = new HashMap<>();

        String[] cmdBits = this.cmd.split("\\?", 2);
        action = cmdBits[0].trim();

        if (cmdBits.length > 1) {
            queryString = cmdBits[1].trim(); // 쿼리 스트링이 존재하는 경우에만 값을 할당

            String[] queryStrBits = queryString.split("=", 2);
            if (queryStrBits.length > 1) {
                String paramsNames = queryStrBits[0];
                String paramsValues = queryStrBits[1];
                params.put(paramsNames, paramsValues);
            }
        }
    }

    @Getter
    private String action;

    public int getParamAsInt(String paraName, int defaultValue) {
        String value = params.get(paraName);

        if (value != null) try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
        return defaultValue;
    }
}