package com.mind.over.machines.business.rule.processor.dto;

import java.util.List;

public class BusinessRuleResponse {
    private String customerId;
    private List<String> results;

    public BusinessRuleResponse(String customerId, List<String> results) {
        this.customerId = customerId;
        this.results = results;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<String> getResults() {
        return results;
    }
}
