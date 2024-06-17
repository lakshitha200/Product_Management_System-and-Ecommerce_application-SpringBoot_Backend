package com.PMS.PMS;

import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
class AdminTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ObjectMapper objectMapper;

    Admin admin;


    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        mySQLContainer.start();

        System.out.println(mySQLContainer.getDatabaseName());
        System.out.println(mySQLContainer.getJdbcUrl());
        System.out.println(mySQLContainer.getUsername());
        System.out.println(mySQLContainer.getPassword());
    }

    @AfterAll
    static void afterAll() {
        mySQLContainer.stop();
    }


    @BeforeEach
    public void setup(){
        admin = Admin.builder()
                .id(1L)
                .number(1000)
                .name("Lakshitha Fernando")
                .username("lakiya")
                .email("lakshitha@gmail.com")
                .password("lakshitha")
                .img("lakshitha.jpg")
                .phone(777070068)
                .roles("ROLE_ADMIN_MANAGER")
                .build();

    }


    //Save Employee Test
    @Test
    @Order(1)
    public void saveEmployeeTest() throws Exception {
        // precondition

		/* ** Precondition is in the above setup() method.
		 Other test methods can access this employee object instead
		 create new employee for each test methods */

        // Action and Verify
        mockMvc.perform(post("/api/v1/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin)))

                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Lakshitha Fernando"))
                .andExpect(jsonPath("$.username").value("lakiya"))
                .andExpect(jsonPath("$.email").value("lakshitha@gmail.com"));
    }


    //Get All employees Test
    @Test
    @Order(2)
    public void getAllEmployee() throws Exception {
        // precondition
        List<Admin> adminList = new ArrayList<>();
        adminList.add(admin);
        adminList.add(Admin.builder()
                .id(1L)
                .number(1000)
                .name("Sithum Fernando")
                .username("sithuma")
                .email("sithum@gmail.com")
                .password("sithum")
                .img("sithum.jpg")
                .phone(77090072)
                .roles("ROLE_ADMIN")
                .build()
        );
        adminRepository.saveAll(adminList);


        // Action and Verify
        mockMvc.perform(get("/api/v1/admins"))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(adminList.size())));

    }

    //Get employeeById Test
    @Test
    @Order(3)
    public void getEmployeeById() throws Exception{

        // Action and Verify
        mockMvc.perform(get("/api/v1/admins/{id}", admin.getId()))

                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(admin.getName())))
                .andExpect(jsonPath("$.username", is(admin.getUsername())))
                .andExpect(jsonPath("$.email", is(admin.getEmail())));

    }

    //Update employees Test
    @Test
    @Order(4)
    public void updateEmployee() throws Exception {

        //Action
        admin.setEmail("lakshitha12345@gmail.com");
        admin.setPhone(710817839);
        mockMvc.perform(put("/api/v1/admins/{id}", admin.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin)))
                //verify
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(admin.getName())))
                .andExpect(jsonPath("$.email", is(admin.getEmail())));
    }

    //Delete employees Test
    @Test
    @Order(5)
    public void deleteEmployee() throws Exception{

        // Action and Verify
        mockMvc.perform(delete("/api/v1/admins/{id}", admin.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
