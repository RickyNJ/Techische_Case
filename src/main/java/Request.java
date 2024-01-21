import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private static final String url = "https://v444k.wiremockapi.cloud/activate";
    private String userId;
    private String macAddress;
    private HttpResponse<String> response;

    public Request(String uid, String mac) {
        userId = uid;
        macAddress = mac;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void makeRequest() throws IOException, InterruptedException {
        String body = String.format("{\"customerId\": \"%s\", \"macAddress\": \"%s\"}", this.getUserId(), this.getMacAddress());

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        this.response = client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public String getResponseBody() {
        return response.body();
    }
}