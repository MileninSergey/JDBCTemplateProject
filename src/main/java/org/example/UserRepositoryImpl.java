package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (email,password,userName) VALUES (?,?,?)";
        jdbcTemplate.update(sql,user.getEMail(),user.getPassword(),user.getUserName());
    }

    @Override
    public void update(User user) {
        String sql = "update users set email = ? , username = ? ,password = ? where id = ?";
        jdbcTemplate.update(sql,user.getEMail(),user.getPassword(),user.getUserName(),user.getId());
    }

    @Override
    public User getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> getListUser() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
