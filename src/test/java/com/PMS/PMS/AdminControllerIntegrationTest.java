package com.PMS.PMS;

import com.PMS.PMS.Model.Admin;
import com.PMS.PMS.Repository.AdminRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class AdminControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest");

    Admin admin1;
    Admin admin2;

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
    public void setup() throws Exception {

        admin1 = Admin.builder()
                .id(1L)
                .number(1000)
                .name("Lakshitha Fernando")
                .username("lakiya")
                .email("lakshitha@gmail.com")
                .password(this.passwordEncoder.encode("lakshitha"))
                .img("lakshitha.jpg")
                .phone(777070068)
                .roles("ROLE_ADMIN_MANAGER")
                .build();

        admin2 = Admin.builder()
                .id(2L)
                .number(1001)
                .name("Sithum Dhananjaya")
                .username("sithum")
                .email("sithum@gmail.com")
                .password(this.passwordEncoder.encode("sithum"))
                .img("sithum.jpg")
                .phone(758955639)
                .roles("ROLE_ADMIN")
                .build();

    }


    @Test
    @Order(1)
    @WithMockUser(username = "adminManager", roles = {"ADMIN_MANAGER"})
    public void testCreateAdmin() throws Exception {

        // Action and Verify
        mockMvc.perform(post("/api/v1/admins")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(admin1)))

                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(admin1.getName())))
                .andExpect(jsonPath("$.username", is("A_" + admin1.getUsername())))
                .andExpect(jsonPath("$.email", is(admin1.getEmail())))
                .andDo(print());
    }

    @Test
    @Order(2)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAllAdmins() throws Exception {
        // precondition
        List<Admin> admins = Arrays.asList(admin1, admin2);
        adminRepository.saveAll(admins);

        // Action and Verify
        mockMvc.perform(get("/api/v1/admins"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(admins.size())));
    }

    @Test
    @Order(3)
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testGetAdminByID() throws Exception {

        // Action and Verify
        mockMvc.perform(get("/api/v1/admins/" + admin1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(admin1.getName())))
                .andExpect(jsonPath("$.username", is(admin1.getUsername())))
                .andExpect(jsonPath("$.email", is(admin1.getEmail())))
                .andDo(print());
    }


    @Test
    @Order(4)
    @WithMockUser(username = "adminManager", roles = {"ADMIN_MANAGER"})
    public void testUpdateAdmin() throws Exception {
        admin1.setEmail("lakshitha12345@gmail.com");
        admin1.setName("LAKSHITHA GAJENDRA FDO");

        //Action
        mockMvc.perform(put("/api/v1/admins/"+ admin1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin1)))
                //verify
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is(admin1.getName())))
                .andExpect(jsonPath("$.email", is(admin1.getEmail())));
    }

    @Test
    @Order(5)
    @WithMockUser(username = "adminManager", roles = {"ADMIN_MANAGER"})
    public void testDeleteAdmin() throws Exception {
        // Action and Verify
        mockMvc.perform(delete("/api/v1/admins/"+admin1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}
