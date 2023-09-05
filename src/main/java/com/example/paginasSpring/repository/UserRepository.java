package com.example.paginasSpring.repository;

import com.example.paginasSpring.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final Logger logger = LogManager.getLogger(UserRepository.class);

    private final JdbcTemplate template;

    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<User> getAll() {
        logger.info("Ejecutando query para usuarios:");
        return template.query("SELECT * FROM users" , this::mapToUser);
    }

    private User mapToUser(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setLastname(resultSet.getString("lastname"));
        user.setEmail(resultSet.getString("email"));
        user.setNickname(resultSet.getString("nickname"));
        logger.info("Se obvtuvieron de la base los usuarios: ");
        return user;
    }
}
