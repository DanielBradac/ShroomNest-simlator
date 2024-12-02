package cz.bradacd.shroomnest_simulator;

import cz.bradacd.shroomnest_simulator.server.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ShroomnestSimulatorApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ShroomnestSimulatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner runServerOnBackground(Server server) {
		return args -> {
			server.runServer(); // Start the background service
		};
	}
}
