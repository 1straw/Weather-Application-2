package rn.weatherapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/fetch-users")
    public String fetchUsers() {
        apiService.getUsers();
        return "Users fetched!";
    }
}
