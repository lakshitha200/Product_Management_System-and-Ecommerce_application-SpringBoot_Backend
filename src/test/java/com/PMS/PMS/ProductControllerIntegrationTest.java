package com.PMS.PMS;


import com.PMS.PMS.Model.Products.PColors;
import com.PMS.PMS.Model.Products.Product;
import com.PMS.PMS.Model.Products.ProductImages;
import com.PMS.PMS.Model.Products.ProductSpecifications;
import com.PMS.PMS.Repository.PColorsRepository;
import com.PMS.PMS.Repository.ProductImagesRepository;
import com.PMS.PMS.Repository.ProductRepository;
import com.PMS.PMS.Repository.ProductSpecificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImagesRepository productImagesRepository;
    @Autowired
    private ProductSpecificationRepository productSpecificationRepository;
    @Autowired
    private PColorsRepository pColorsRepository;


    @Autowired
    private ObjectMapper objectMapper;

    Product product1;
    Product product2;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeAll
    static void setUp() {
        mySQLContainer.start();
    }


    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }

    @BeforeEach
    public void setup(){

        // Save related entities
        ProductImages image1 = new ProductImages(1L, "samsung1.jpg");
        ProductImages image2 = new ProductImages(2L, "samsung2.jpg");
        productImagesRepository.saveAll(Arrays.asList(image1, image2));


        ProductSpecifications s = ProductSpecifications.builder()
                .id(1L)
                .display("6 inch Super Display")
                .battery("6000 mA")
                .ram("4GB")
                .chipset("super chipset")
                .storage("64GB")
                .camera("48MP")
                .build();
        productSpecificationRepository.save(s);

        PColors color1 = PColors.builder().id(1L).name("Black").build();
        PColors color2 = PColors.builder().id(2L).name("Blue").build();
        pColorsRepository.saveAll(Arrays.asList(color1, color2));

        List<ProductImages> pImages1 = Arrays.asList(image1);
        List<PColors> colors1 = Arrays.asList(color1);

        List<ProductImages> pImages2 = Arrays.asList(image2);
        List<PColors> colors2 = Arrays.asList(color2);

        product1 = Product.builder()
                .id(1L)
                .number(1000)
                .model("Galaxy M21")
                .brand("Samsung")
                .type("Phone")
                .price(40000)
                .discount(10)
                .discountAvailable(true)
                .stock(20)
                .images(pImages1)
                .specifications(s)
                .colors(colors1)
                .build();

        product2 = Product.builder()
                .id(2L)
                .number(1001)
                .model("Blase A54")
                .brand("Zte")
                .type("Phone")
                .price(35000)
                .discount(0)
                .discountAvailable(false)
                .stock(10)
                .images(pImages2)
//                .specifications(null)
                .colors(colors2)
                .build();

    }

    @Test
    @Order(1)
    @WithMockUser(username = "adminManager", roles = {"ADMIN"})
    public void testSaveProduct() throws Exception {

        mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product1)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.model", is(product1.getModel())))
                .andExpect(jsonPath("$.number", is(product1.getNumber())))
                .andDo(print());
    }

    @Test
    @Order(2)
    public void testGetAllProduct() throws Exception {

        productRepository.saveAll(Arrays.asList(product1, product2));

        // Action and Verify
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(2)));
    }

    @Test
    @Order(3)
    public void testGetByIdProduct() throws Exception {
        // Action and Verify
        mockMvc.perform(get("/api/v1/products/" + product1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(product1.getName())))
                .andExpect(jsonPath("$.number", is(product1.getNumber())))
                .andDo(print());
    }

    @Test
    @Order(4)
    @WithMockUser(username = "adminManager", roles = {"ADMIN"})
    public void testUpdateProduct() throws Exception{
        product1.setPrice(50000);
        product1.setDiscount(20);

        //Action
        mockMvc.perform(put("/api/v1/products/" + product1.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                //verify
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.price", is(product1.getPrice())))
                .andExpect(jsonPath("$.discount", is(product1.getDiscount())));
    }

    @Test
    @Order(5)
    @WithMockUser(username = "adminManager", roles = {"ADMIN"})
    public void testDeleteProduct() throws Exception {

        mockMvc.perform(delete("/api/v1/products/"+product1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
