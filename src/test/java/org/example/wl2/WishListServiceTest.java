package org.example.wl2;

import org.example.wl2.model.WishlistModel;
import org.example.wl2.repository.WishRepo;
import org.example.wl2.service.WishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WishListServiceTest {

    private WishRepo repo;
    private WishService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(WishRepo.class);
        service = new WishService((repo));
    }

    @Test
    void TestGetAll() {
        List<WishlistModel> models = Arrays.asList(
                new WishlistModel("Stol", "brun læder", 750, "https://barns&nobles.com")
        );

        when(repo.findAll()).thenReturn(models);

        List<WishlistModel> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Stol", result.get(0).getName());
        verify(repo, times(1)).findAll();
    }
}
