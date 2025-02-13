package rn.weatherapi.backend;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    private final WebClient webClient;
    private static final String API_KEY = "API_KEY";

    public WeatherService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getWeatherStockholm(String lat, String lon) {


        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("appid", API_KEY)
                        .queryParam("units", "metric") // Lägg till enheter
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API request failed")))
                .bodyToMono(String.class);
    }
}

