package com.smart.sales.manager.test.password_reset;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordResetIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessPasswordResetWithoutToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessPasswordResetWithInvalidToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password?token=invalid-token")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void accessPasswordResetWithExpiredToken() throws Exception {
        this.mockMvc
                .perform(
                        get("/reset-password?token=expired-token")
                )
                .andExpect(model().attributeExists("error"))
                .andExpect(status().isOk());
    }

    @Test
    public void submitPasswordResetSuccess() throws Exception {
        this.mockMvc
                .perform(
                        post("/reset-password")
                                .with(csrf())
                                .param("password", "password")
                                .param("confirmPassword", "password")
                                .param("token", "valid-token")
                )
                .andExpect(model().hasNoErrors())
                .andExpect(redirectedUrl("/login?reset-success"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submitPasswordResetPasswordDoNotMatch() throws Exception {
        this.mockMvc
                .perform(
                        post("/reset-password")
                                .with(csrf())
                                .param("password", "password")
                                .param("confirmPassword", "invalid-password")
                                .param("token", "valid-token")
                )
                .andExpect(flash().attributeExists(BindingResult.class.getName() + ".passwordResetForm"))
                .andExpect(redirectedUrl("/reset-password?token=valid-token"))
                .andExpect(status().is3xxRedirection());
    }

}
