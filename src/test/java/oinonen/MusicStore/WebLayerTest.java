package oinonen.MusicStore;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {
 	
 	@Autowired
 	private MockMvc mockMvc;
 	
 	@Test
 	public void testDefaultMessage() throws Exception {
 	 	this.mockMvc.perform(get("/"))
 	 	.andExpect(status().isOk())
    .andExpect(view().name("index"));
 	};

 	@Test
 	public void testProductPage() throws Exception {
 	 	this.mockMvc.perform(get("/productdata"))
 	 	.andExpect(status().isOk())
 	 	.andExpect(jsonPath("$", Matchers.hasSize(6)))
 	 	.andExpect(jsonPath("$[0].quantity").value(5))
    .andDo(MockMvcResultHandlers.print())
    .andReturn();
 	};
};

