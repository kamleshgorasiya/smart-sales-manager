package com.smart.sales.manager.test.password_reset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRegistrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void submitRegistrationAccountExists() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "info@logwintech.com")
                                .param("confirmEmail", "info@logwintech.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationPasswordNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@logwintech.com")
                                .param("confirmEmail", "new@logwintech.com")
                                .param("password", "password")
                                .param("confirmPassword", "invalid")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationEmailNotMatching() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@logwintech.com")
                                .param("confirmEmail", "different@logwintech.com")
                                .param("password", "password")
                                .param("confirmPassword", "invalid")
                                .param("terms", "on")
                )
                .andExpect(model().hasErrors())
                .andExpect(model().attributeHasErrors("user"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitRegistrationSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/registration")
                                .with(csrf())
                                .param("firstName", "Memory")
                                .param("lastName", "Not Found")
                                .param("email", "new@logwintech.com")
                                .param("confirmEmail", "new@logwintech.com")
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("terms", "on")
                )
                .andExpect(redirectedUrl("/registration?success"))
                .andExpect(status().is3xxRedirection());
    }

}
