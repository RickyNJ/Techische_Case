//import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

//import static org.junit.jupiter.api.Assertions.assertEquals;

class APIHandlerTest {
    String userId1 = "12345";
    String macAddress1 = "AA:BB:CC:DD:EE:FF";
    String expected1 = "Status: 201 Created";
    String userId2 = "12345";
    String macAddress2 = "AA:BB:CC:DD:EE:AA";
    String expected2 = "Status: 404 Not Found";
    String userId3 = "11111";
    String macAddress3 = "AA:BB:CC:DD:EE:FF";
    String expected3 = "Status: 409 Conflict";
//
//    @Test
//    void apiTest() throws IOException, InterruptedException {
//        HttpResponse<String> actual = APIHandler.makeRequest(userId1, macAddress1);
//        assertEquals(APIHandler.getResponseBody(actual), expected1);
//    }
//    @Test
//    void apiTest2() throws IOException, InterruptedException {
//        HttpResponse<String> actual = APIHandler.makeRequest(userId2, macAddress2);
//        assertEquals(APIHandler.getResponseBody(actual), expected2);
//    }
//    @Test
//    void apiTest3() throws IOException, InterruptedException {
//        HttpResponse<String> actual = APIHandler.makeRequest(userId3, macAddress3);
//        assertEquals(APIHandler.getResponseBody(actual), expected3);
//    }
}