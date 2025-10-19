package rn.weatherapi.backend;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;

    @Value("${OPENWEATHER_API_KEY}")
    private String API_KEY;

    @Autowired
    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    @PostConstruct
    public void init() {
        System.out.println("✅ Loaded OpenWeather API key: " + (API_KEY != null ? "OK" : "MISSING"));
    }

    public Mono<String> getWeather(String lat, String lon) {
        if (API_KEY == null || API_KEY.isEmpty()) {
            return Mono.error(new RuntimeException("No OpenWeather API key configured!"));
        }
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("appid", API_KEY)
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API request failed")))
                .bodyToMono(String.class);
    }
}

