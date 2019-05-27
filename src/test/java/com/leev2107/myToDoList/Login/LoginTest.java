package com.leev2107.myToDoList.Login;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

	@Autowired
	private MockMvc mockMvc;

	// login
	@Test
	public void loginTest() throws Exception {
		this.mockMvc.perform(formLogin().user("admin").password("123")).andDo(print())
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/"));
	}

	// wrong input
	@Test
	public void badCredentials() throws Exception {
		this.mockMvc.perform(post("/login").param("user", "1235")).andDo(print())
				.andExpect(status().is3xxRedirection());
	}
}
