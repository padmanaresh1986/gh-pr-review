package com.mind.over.machines.business.rule.processor.core;


import com.mind.over.machines.business.rule.processor.exception.BusinessRuleException;

import java.util.Random;

public interface BusinessRule {
    void execute(String customerId) throws BusinessRuleException;
    String getRuleName();

    Random getRandomInstance();
}
