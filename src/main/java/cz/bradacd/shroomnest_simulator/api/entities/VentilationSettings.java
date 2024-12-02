package cz.bradacd.shroomnest_simulator.api.entities;

public record VentilationSettings(
        int waitTime,
        int waitPer,
        int runPer,
        String mode,
        int runTime,
        boolean fanOn
) {}



