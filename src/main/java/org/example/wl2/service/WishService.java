package org.example.wl2.service;

import org.example.wl2.model.WishlistModel;
import org.example.wl2.repository.WishRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WishService {
    private final WishRepo repository;

    public WishService(WishRepo repository){
        this.repository = repository;
    }

    public List<WishlistModel> getAll(){
        return repository.findAll();
    }

    public WishlistModel getById(int id){
        return repository.findById(id);
    }

    public void addWish(WishlistModel model){
        repository.add(model);
    }

    public void updateWish(int id, WishlistModel updated){
        repository.update(id, updated);
    }

    public void delete(int id){
        repository.delete(id);
    }
}
