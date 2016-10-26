package demo.vonex;

import demo.vonex.handler.ApiResponseErrorHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
	public static void main(String [] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
		System.setProperty("https.cipherSuites", "TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new ApiResponseErrorHandler());
		return restTemplate	;
	}
}
