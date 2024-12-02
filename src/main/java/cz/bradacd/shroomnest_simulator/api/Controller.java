package cz.bradacd.shroomnest_simulator.api;

import cz.bradacd.shroomnest_simulator.api.entities.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {


    @GetMapping(Paths.GET_STATUS)
    public Status testEndpoint() {
        return new Status(0.1, 0.2);
    }
}