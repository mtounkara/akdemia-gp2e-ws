package af.cmr.indyli.akdemia.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import af.cmr.indyli.akdemia.ws.config.AkdemiaWsGp2eConfig;

@SpringBootApplication
@Import(AkdemiaWsGp2eConfig.class)
public class AkdemiaWsGp2eApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkdemiaWsGp2eApplication.class, args);
    }

}
