package org.example.wl2.repository;

import org.example.wl2.model.WishlistModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class WishRepo {
    private final JdbcTemplate jdbcTemplate;

    public WishRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<WishlistModel> wishlistRowMapper= (rs, rowNum) -> {
        return new WishlistModel(
                rs.getInt("id"),
                rs.getString("wishName"),
                rs.getString("descriptions"),
                rs.getDouble("prices"),
                rs.getString("link")
        );
    };

    public List<WishlistModel> findAll(){
        String sql = "select * from Wish";
        return jdbcTemplate.query(sql,wishlistRowMapper);
    }

    public WishlistModel findById(int id){
        String sql = "select * from Wish where id = ?";
        return jdbcTemplate.queryForObject(sql, wishlistRowMapper, id);
    }

    public int add(WishlistModel model){
        String sql = "insert into wish (wishName, descriptions, prices, link) values (?,?,?,?)";
        return jdbcTemplate.update(sql,
                model.getName(),
                model.getDescription(),
                model.getPrice(),
                model.getLink()
                );
    }

    public int update(int id, WishlistModel updated){
        String sql = "update wish set wishName=?, descriptions=?, prices=?, link=? where id=?";
        return jdbcTemplate.update(sql,
                updated.getName(),
                updated.getDescription(),
                updated.getPrice(),
                updated.getLink(),
                id
        );
    }

    public int delete(int id){
        String sql = "delete from wish where id=?";
        return jdbcTemplate.update(sql,id);
    }




}
