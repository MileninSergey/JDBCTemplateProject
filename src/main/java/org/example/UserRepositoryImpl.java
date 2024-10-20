package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public User create(User user) {
        String sql = "INSERT INTO users (email,password,userName) VALUES (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
           PreparedStatement preparedStatement =  connection.prepareStatement(sql,new String[]{"id"});
           preparedStatement.setString(1,user.getEmail());
           preparedStatement.setString(2,user.getPassword());
           preparedStatement.setString(3,user.getUserName());
           return preparedStatement;
        },keyHolder);

        User user1 = new User(keyHolder.getKey().longValue(), user.getEmail(), user.getPassword(), user.getUserName());
        return user1;
    }

    @Override
    public void update(User user) {
        String sql = "update users set email = ? , username = ? ,password = ? where id = ?";
        jdbcTemplate.update(sql,user.getEmail(),user.getPassword(),user.getUserName(),user.getId());
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
