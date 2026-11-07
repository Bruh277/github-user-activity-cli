import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Scanner;

public class Fetcher {
    private Scanner scanner;
    private HttpClient client;
    private HttpRequest request;

    public Fetcher(){
        this.scanner = new Scanner(System.in);
        HttpClient client = HttpClient.newBuilder()
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/" + getUsername() + "/events"))
                .GET()
                .timeout(Duration.ofSeconds(45))
                .header("accept", "application/vnd.github+json")
                .header("X-GitHub-Api-Version", "2022-11-28")
                .build();
    }

    public String getUsername(){
        System.out.println("Provide the username of the user whose recent GitHub activity you wish to view:");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    public String serverResponse(){
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    }
}
