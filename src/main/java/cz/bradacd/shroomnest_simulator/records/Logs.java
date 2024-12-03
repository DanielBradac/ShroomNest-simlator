package cz.bradacd.shroomnest_simulator.records;

import java.util.List;

public record Logs(
        List<Log> info,
        List<Log> warning,
        List<Log> error
) {}
