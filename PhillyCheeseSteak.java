import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;




public class PhillyCheeseSteak {
    private static String url = "https://d18ejwnvsjufan.cloudfront.net/SPACE!!!";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Getting the random number from API");
        final int randomNumber = getRandomValueFromAPI();
        System.out.println("Random value is:" + randomNumber);

        // Calculate a random prime number above 10,000
        int prime = calculateLargePrime(randomNumber);

        // Signal readiness
        System.out.println("Calculation complete, ready to start.");
    }

    public static int getRandomValueFromAPI() throws IOException, InterruptedException   {
        System.out.println("Fetching random data:");
        String response = sendGetRequest(url);
        System.out.println("Recieved: " + response);
        response = response.split("/")[0]; 
        System.out.println("Processing: " + response);
        response = response.split(":")[1];
        System.out.println("Processing: " + response);
        response = response.split("\"")[1];
        System.out.println("Processing: " + response);
        char responseChar = response.charAt(0);
        System.out.println("Processing: " + responseChar);
        return Integer.valueOf(responseChar);
    }

    public static String sendGetRequest(String urlString) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response);
        }

        return response.body();
    }

    public static int calculateLargePrime(int randomNumber) {
        System.out.println("Checking if " + randomNumber + " is a prime");
        if(!isPrime(randomNumber)) {
            System.out.println("Not prime!");
        } else {
            System.out.println("Prime!");
        }
        return randomNumber;
    }

    static boolean isPrime(int n) //geeksforgeeks
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
