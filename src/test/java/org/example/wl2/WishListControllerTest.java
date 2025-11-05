package org.example.wl2;

import org.example.wl2.controller.WishController;
import org.example.wl2.service.WishService;
import org.example.wl2.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishController.class)
class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService service;

    @MockBean
    private UserService userService;

    @MockBean
    private View view;

    @Test
    void testRedirectToLoginIfUnauthenticated() throws Exception {
        mockMvc.perform(get("/wishes"))
                .andExpect(status().isFound())            // 302 redirect
                .andExpect(redirectedUrl("/login"));      // redirected to login
    }
}