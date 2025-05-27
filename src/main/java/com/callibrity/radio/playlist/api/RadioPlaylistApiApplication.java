package com.callibrity.radio.playlist.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class RadioPlaylistApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RadioPlaylistApiApplication.class, args);
        Environment environment = context.getEnvironment();

        String radioStationName = environment.getProperty("app.radio.station.name");
        String port = environment.getProperty("server.port");
        System.out.println(radioStationName + " application started. Documentation can be found at  : http://localhost:" + port +"/swagger-ui/index.html" );
    }

}
