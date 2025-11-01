package org.example.wl2.repository;

import org.example.wl2.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class WishRepo {
    private final JdbcTemplate jdbcTemplate;

    public WishRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Wish> wishRowMapper = (rs, rowNum) ->
            new Wish (
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getString("wishName"),
        rs.getString("descriptions"),
        rs.getDouble("prices"),
        rs.getString("link")
        );


    public int save(Wish wish) {
        String sql = "insert into wish (user_id, wishName, descriptions, prices, link) values (?,?,?,?,?)";
        return jdbcTemplate.update(sql,
                wish.getUserId(),
                wish.getName(),
                wish.getDescription(),
                wish.getPrice(),
                wish.getLink()
        );
    }


    public List<Wish> findALlByUserId(int userId) {
        String sql = "select * from wish where user_id = ?";
        RowMapper<Wish> rowMapper = (rs, rowNum) -> {
            Wish w = new Wish();
            w.setId(rs.getInt("id"));
            w.setUserId(rs.getInt("user_id"));
            w.setName(rs.getString("wishName"));
            w.setDescription(rs.getString("descriptions"));
            w.setPrice(rs.getDouble("prices"));
            w.setLink(rs.getString("link"));
            return w;
        };
        return jdbcTemplate.query(sql, rowMapper, userId);
    }

    public Wish findWishById(int id) {
        String sql = "select * from wish where id = ?";
        return jdbcTemplate.queryForObject(sql, wishRowMapper, id);
    }


    public List<Wish> findAll() {
        String sql = "select * from wish";
        return jdbcTemplate.query(sql, wishRowMapper);
    }


    public int update(int id, Wish updated) {
        String sql = "update wish set wishName=?, descriptions=?, prices=?, link=? where id=?";
        return jdbcTemplate.update(sql,
                updated.getName(),
                updated.getDescription(),
                updated.getPrice(),
                updated.getLink(),
                id
        );
    }

    public int delete(int id) {
        String sql = "delete from wish where id=?";
        return jdbcTemplate.update(sql, id);
    }
}
