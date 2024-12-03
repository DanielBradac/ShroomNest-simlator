package cz.bradacd.shroomnest_simulator.api;

import cz.bradacd.shroomnest_simulator.records.IPSettings;
import cz.bradacd.shroomnest_simulator.records.Logs;
import cz.bradacd.shroomnest_simulator.records.Status;
import cz.bradacd.shroomnest_simulator.server.managers.IPManagerSingleton;
import cz.bradacd.shroomnest_simulator.server.managers.LogManagerSingleton;
import cz.bradacd.shroomnest_simulator.server.managers.StatusManagerSingleton;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping(Paths.GET_STATUS)
    public Status getStatus() {
        return StatusManagerSingleton.getInstance().getRecord();
    }

    @PostMapping(Paths.UPDATE_IP_SETTINGS)
    public IPSettings updateIPSettings(@RequestBody IPSettings newSettings) {
        IPManagerSingleton manager = IPManagerSingleton.getInstance();
        manager.updateValues(newSettings);
        return manager.getRecord();
    }

    @GetMapping(Paths.GET_LOGS)
    public Logs getLogs() {
        return LogManagerSingleton.getInstance().getRecord();
    }

    @PostMapping(Paths.PURGE_LOGS)
    public Logs purgeLogs() {
        LogManagerSingleton manager = LogManagerSingleton.getInstance();
        manager.purgeLogs();
        return manager.getRecord();
    }
}