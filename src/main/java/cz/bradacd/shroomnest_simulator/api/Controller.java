package cz.bradacd.shroomnest_simulator.api;

import cz.bradacd.shroomnest_simulator.exceptions.BadRequestException;
import cz.bradacd.shroomnest_simulator.exceptions.ValidationException;
import cz.bradacd.shroomnest_simulator.records.*;
import cz.bradacd.shroomnest_simulator.server.managers.HumidityManagerSingleton;
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

    @GetMapping(Paths.GET_LOGS)
    public Logs getLogs() {
        return LogManagerSingleton.getInstance().getRecord();
    }

    @GetMapping(Paths.GET_HUMIDITY_SETTINGS)
    public HumiditySettings getHumiditySettings() {
        return HumidityManagerSingleton.getInstance().getRecord();
    }

    @PostMapping(Paths.UPDATE_HUMIDITY_SETTINGS)
    public HumiditySettings updateHumiditySettings(@RequestBody HumiditySettingsInput newSettings) {
        HumidityManagerSingleton manager = HumidityManagerSingleton.getInstance();
        try {
            manager.updateValues(newSettings);
        } catch (ValidationException e) {
            throw new BadRequestException(e.getMessage());
        }

        return manager.getRecord();
    }

    @PostMapping(Paths.UPDATE_IP_SETTINGS)
    public IPSettings updateIPSettings(@RequestBody IPSettings newSettings) {
        IPManagerSingleton manager = IPManagerSingleton.getInstance();
        manager.updateValues(newSettings);
        return manager.getRecord();
    }

    @PostMapping(Paths.PURGE_LOGS)
    public Logs purgeLogs() {
        LogManagerSingleton manager = LogManagerSingleton.getInstance();
        manager.purgeLogs();
        return manager.getRecord();
    }
}