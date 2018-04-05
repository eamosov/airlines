package com.softpro.airlines.tests;

import com.softpro.airlines.AirlinesService;
import com.softpro.airlines.Application;
import com.softpro.airlines.controllers.AirlinesController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = {Application.class, AirlinesService.class, AirlinesController.class})
public class AirlinesControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAirplains() throws Exception {

        mockMvc.perform(get("/airplains"))
               .andExpect(status().isOk());

        mockMvc.perform(get("/airplains/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testAirports() throws Exception {

        mockMvc.perform(get("/airports"))
               .andExpect(status().isOk());

        mockMvc.perform(get("/airports/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testRoutes() throws Exception {

        mockMvc.perform(get("/routes"))
               .andExpect(status().isOk());

        mockMvc.perform(get("/routes/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)));

    }

    @Test
    public void testFlights() throws Exception {

        mockMvc.perform(get("/flights"))
               .andExpect(status().isOk());

        mockMvc.perform(get("/flights/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)));

        mockMvc.perform(get("/lands/1/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", is(14)));

        mockMvc.perform(get("/flights/add/2018-04-09/1/1"))
               .andExpect(status().isOk());

        mockMvc.perform(get("/lands/1/2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", is(15)));

        mockMvc.perform(get("/lands/1/2/2018-04-02/2018-04-08"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", is(14)));

    }

}
