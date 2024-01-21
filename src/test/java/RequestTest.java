import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class APIHandlerTest {
    @Test
    void apiTest1() throws IOException, InterruptedException {
        Request req1 = new Request("12345", "AA:BB:CC:DD:EE:FF");
        String expected1 = "Status: 201 Created";

        req1.makeRequest();
        String actual = req1.getResponseBody();
        Assertions.assertEquals(actual, expected1);
    }

    @Test
    void apiTest2() throws IOException, InterruptedException {
        Request req2 = new Request("12345", "AA:BB:CC:DD:EE:AA");
        String expected2 = "Status: 404 Not Found";

        req2.makeRequest();

        String actual = req2.getResponseBody();
        Assertions.assertEquals(actual, expected2);
    }

    @Test
    void apiTest3() throws IOException, InterruptedException {
        Request req3 = new Request("11111", "AA:BB:CC:DD:EE:FF");
        String expected3 = "Status: 409 Conflict";

        req3.makeRequest();

        String actual = req3.getResponseBody();
        Assertions.assertEquals(actual, expected3);
    }
}