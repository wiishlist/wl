package org.example.wl2.repository;

import org.example.wl2.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepo {
    private final JdbcTemplate jdbcTemplate;
    public UserRepo(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<UserModel> userModelRowMapper = (rs, rowNum) -> {
        return new UserModel(
                rs.getInt("id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("passwords")
        );
    };

    public UserModel findByUserName(String user){
        String sql = "select * from users where username = ?";
        return jdbcTemplate.queryForObject(sql,userModelRowMapper,user);
    }

    public UserModel findByEmail(String email){
        String sql = "select * from users where email = ?";
        return jdbcTemplate.queryForObject(sql,userModelRowMapper,email);
    }


    public boolean avaliableUserName (String username){
        String sql = "select count(*) from users where username =?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;

    }

    public int save(UserModel user) {
        String sql = "insert into users (username, email, passwords) values(?,?,?)";
        return jdbcTemplate.update(sql,
                user.getUser(),
                user.getEmail(),
                user.getPassword()
        );
    }
}