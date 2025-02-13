package rn.weatherapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class ApiService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public void getUsers() {
        // API URL
        String url = "https://jsonplaceholder.typicode.com/users";

        // Bygg WebClient-instansen
        WebClient webClient = webClientBuilder.baseUrl(url).build();

        // Anropa API:et och hämta flera objekt (som en lista av användare)
        Flux<String> response = webClient.get()  // Använd .get() på WebClient, inte på Builder
                .retrieve()
                .bodyToFlux(String.class);

        // Här kan du hantera resultatet
        response.subscribe(
                result -> System.out.println("API Response: " + result),
                error -> System.err.println("Error: " + error.getMessage())
        );
    }
}
