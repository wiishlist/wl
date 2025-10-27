package org.example.wl2;

import org.example.wl2.controller.WishController;
import org.example.wl2.model.WishlistModel;
import org.example.wl2.service.WishService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.util.Arrays;
import java.util.List;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WishController.class)
 class WishListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishService service;

    @MockBean
    private View view;


    @Test
    void testGetWishes() throws Exception {
        List<WishlistModel> wishlistModels = Arrays.asList(
                new WishlistModel( "taske", "meget fin", 1500, "https://google.com")
        );
        when(service.getAll()).thenReturn(wishlistModels);

        mockMvc.perform(get("/wishes"))
                .andExpect(status().isOk())
                .andExpect(view().name("wishList"))
                .andExpect(model().attributeExists("wish"))
                .andExpect(model().attribute("wish", wishlistModels));

        verify(service, times(1)).getAll();
    }
}
