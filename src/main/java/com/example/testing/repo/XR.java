package com.example.testing.repo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class XR {
    private JdbcTemplate jdbcTemplate;

    public int save(String sql){
        return jdbcTemplate.update(sql);
    }
}
