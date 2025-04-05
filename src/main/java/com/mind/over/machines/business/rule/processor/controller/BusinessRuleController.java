package com.mind.over.machines.business.rule.processor.controller;


import com.mind.over.machines.business.rule.processor.core.BusinessRule;
import com.mind.over.machines.business.rule.processor.dto.BusinessRuleRequest;
import com.mind.over.machines.business.rule.processor.dto.BusinessRuleResponse;
import com.mind.over.machines.business.rule.processor.exception.BusinessRuleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
public class BusinessRuleController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessRuleController.class);

    private final Map<String, BusinessRule> businessRuleMap;

    public BusinessRuleController(List<BusinessRule> businessRules) {
        this.businessRuleMap = businessRules.stream()
                .collect(Collectors.toMap(BusinessRule::getRuleName, rule -> rule));
    }

    @PostMapping("/execute")
    public BusinessRuleResponse executeRules(@RequestBody BusinessRuleRequest request) {
        String customerId = request.getCustomerId();
        List<String> ruleNames = request.getRuleNames();
        List<String> results = new ArrayList<>();

        logger.info("🔄 Received request to execute business rules for customer ID: {}", customerId);

        for (String ruleName : ruleNames) {
            BusinessRule rule = businessRuleMap.get(ruleName);
            if (rule != null) {
                try {
                    rule.execute(customerId);
                    results.add("✅ Rule [" + ruleName + "] passed for customer ID: " + customerId);
                } catch (BusinessRuleException e) {
                    results.add("❌ Rule [" + ruleName + "] failed: " + e.getMessage());
                }
            } else {
                results.add("⚠️ Rule [" + ruleName + "] not found.");
            }
        }

        return new BusinessRuleResponse(customerId, results);
    }
}
