package org.example.wl2.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.wl2.model.Wish;
import org.example.wl2.repository.WishRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishService {
    private final WishRepo repository;

    public WishService(WishRepo repository){
        this.repository = repository;
    }

    public List<Wish> getAll(){
        return repository.findAll();
    }

    public Wish getById(int id){
        return repository.findWishById(id);
    }


    public void addWish(Wish model){
        repository.save(model);
    }

    public void updateWish(int id, Wish updated){
        repository.update(id, updated);
    }

    public void delete(int id){
        repository.delete(id);
    }

    public List<Wish> getAllWishesByUser(int userId) {
        return repository.findALlByUserId(userId);
    }
}
