package org.example.wl2.repository;

import org.example.wl2.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepo {
    private final JdbcTemplate jdbcTemplate;
    public UserRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<User> userRowMapper = (rs, rowNum) ->
         new User(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("passwords")
        );


    public User findByUserName(String user){
        String sql = "select * from users where username = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper,user);
        return users.isEmpty() ? null : users.get(0);
    }

    public User findByEmail(String email){
        String sql = "select * from users where email = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper,email);
        return users.isEmpty() ? null : users.get(0);
    }


    public boolean avaliableUserName (String username){
        String sql = "select count(*) from users where username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;

    }
    public boolean avaliableEmail(String email){
        String sql = "select count(*) from users where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count!= null && count > 0;
    }
    public User findById(int id) {
        String sql = "Select * from users where id = ?";
        List<User> users = jdbcTemplate.query(sql, userRowMapper,id);
        return users.isEmpty() ? null : users.get(0);
    }

    public User save(User user) {
        String sql = "insert into users (username, email, passwords) values(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

         jdbcTemplate.update(con -> {
             PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
             ps.setString(1, user.getUser());
             ps.setString(2, user.getEmail());
             ps.setString(3, user.getPassword());
             return ps;
         }, keyHolder);

         int generateID = keyHolder.getKey().intValue();
         user.setId(generateID);

         return user;
    }
}