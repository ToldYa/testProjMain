package com.vesa.testprojmain.service;

import com.vesa.testprojmain.domain.Plant;
import com.vesa.testprojmain.repository.PlantRepository;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.internal.SemaphoreBulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.decorators.Decorators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Service
public class PlantService {

    private final PlantRepository repository;
    private SemaphoreBulkhead bulkhead;
    private CircuitBreaker circuitBreaker;

    @Autowired
    public PlantService(final PlantRepository repository) {
        this.repository = repository;
        initBulkhead();
        initCircuitBreaker();
    }

    private void initBulkhead() {
        final BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(5)
                .build();
        bulkhead = new SemaphoreBulkhead("PlantServiceBulkhead", config);
    }

    private void initCircuitBreaker() {
        final CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .minimumNumberOfCalls(4)
                .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
                .build();
        circuitBreaker = CircuitBreaker.of("PlantServiceCircuitBreaker", config);
    }

    public void registerPlant(final Plant plant) {
        Decorators.ofRunnable(() -> repository.registerPlant(plant))
                .withBulkhead(bulkhead)
                .withCircuitBreaker(circuitBreaker)
                .decorate()
                .run();
    }

    public void removePlant() {
        throw new NotImplementedException();
    }

    public boolean isPlantRegistered() {
        throw new NotImplementedException();
    }

}
