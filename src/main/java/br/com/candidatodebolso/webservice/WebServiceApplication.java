package br.com.candidatodebolso.webservice;

import br.com.candidatodebolso.webservice.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController("/")
public class WebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return args -> storageService.init();
    }

    @GetMapping
    public ResponseEntity<?> welcome() {
        return ResponseEntity.ok("Serviço funcionando!");
    }
}
