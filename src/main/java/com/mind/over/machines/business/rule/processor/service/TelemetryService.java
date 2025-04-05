package com.mind.over.machines.business.rule.processor.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class TelemetryService {

    private static final Logger log = LoggerFactory.getLogger(TelemetryService.class);

    private final Timer requestTimer;
    private final Random random = new Random();

    public TelemetryService(MeterRegistry meterRegistry) {
        this.requestTimer = meterRegistry.timer("application.request.timer");
    }

    @Scheduled(fixedRate = 15000) // Every 15 seconds
    public void simulateTelemetryData() {
        long startTime = System.currentTimeMillis();

        try {
            int processingTime = random.nextInt(500);
            Thread.sleep(processingTime);
            log.info("Processed request in {} ms", processingTime);
        } catch (InterruptedException e) {
            log.error("Error during request processing: ", e);
        } finally {
            long endTime = System.currentTimeMillis();
            requestTimer.record(endTime - startTime, java.util.concurrent.TimeUnit.MILLISECONDS);
        }
    }
}
