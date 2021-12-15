package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entity.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

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

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());

        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void findOneById() {

        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/laptops/1", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json =
                """
                        {           
                            "price": 18499.0,
                            "offers": true,
                            "modelo": "IdeaPad Gaming 3i 15",
                            "marca": "Lenovo"
                        }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.POST,request,Laptop.class);

        Laptop result = response.getBody();
        assertEquals(1L, result.getId());
        assertEquals("Lenovo", result.getMarca());

    }

    @Test
    void update() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));


        String json =
                """
                    {
                        "id": 2,
                        "price": 18499.0,
                        "offers": true,
                        "modelo": "IdeaPad Gaming 3i 20",
                        "marca": "Lenovo2"
                    }
                
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);

        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops",HttpMethod.PUT,request,Laptop.class);

        Laptop result = response.getBody();

    }

    @Test
    void delete() {

        int idTest = 2;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = null;

        HttpEntity<String> request = new HttpEntity<String>(json, headers);
        ResponseEntity<Laptop> response1 = testRestTemplate.exchange("/api/laptops/"+idTest, HttpMethod.DELETE,request, Laptop.class );
        Laptop result3 = response1.getBody();
        assertEquals(null,result3);

    }

    @Test
    void deleteAll() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = null;

        HttpEntity<String> request = new HttpEntity<String>(json,headers);

    }
}