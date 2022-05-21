package Package.Controllers;

import Package.Entities.Laptop;
import io.swagger.models.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Check that the create a laptop method works")
    @Test
    void createLaptop() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                
                {
                    "brand": "Asus",
                    "model": "TUF creado en TEST",
                    "price": 1000000,
                    "warranty": true
                }
                
                """;

        HttpEntity<String> request = new HttpEntity<>(json,headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,
                request, Laptop.class);

        Laptop laptopResponse = response.getBody();

        assertEquals(1L, laptopResponse.getId());
        assertEquals("TUF creado en TEST",laptopResponse.getModel());

    }

    @DisplayName("Check that the find all laptops method works")
    @Test
    void findAllLaptops() {

        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops",Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());

        List<Laptop> laptops = Arrays.asList(response.getBody());

        System.out.println(laptops.size());

    }

    @DisplayName("Check that the find laptop by ID method works")
    @Test
    void findLaptopById() {
        ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}