package org.hm.demo.mcpreport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoadEndpointTests {

	@Autowired
	private MockMvc mvc;


	@Test
	public void missingDate() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/load").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(400, mvcResult.getResponse().getStatus());
        assertEquals("Required LocalDate parameter 'date' is not present",mvcResult.getResponse().getErrorMessage());
	}

	@Test
	public void invalidDate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20011390").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(equalTo("Bad Request: [Invalid date]")));
	}

	@Test
	public void invalidDate_2() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20010112text").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string(equalTo("Bad Request: [Invalid date]")));
	}

	@Test
	public void validateNonExistentDate() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/load?date=20010112").accept(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(404, mvcResult.getResponse().getStatus());
        assertEquals("Date not found",mvcResult.getResponse().getErrorMessage());
	}

	@Test
	public void validateExistentDate20180131() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20180131").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void validateExistentDate20180201() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20180201").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void validateExistentDate20180202() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20180202").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void validateMetricsReturnSomething() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/load?date=20180202").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		mvc.perform(MockMvcRequestBuilders.get("/metrics"))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"numberMissingFields\":3,\"numberBlankContent\":0,\"numberRowFieldsErrors\":2,\"numberOfCalls\":{\"34\":2},\"averageCallDuration\":{\"34\":120.0},\"wordOccurrence\":{}}"));
	}

	//more tests needed...
}