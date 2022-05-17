package swag.rest.bank_app_delivery.dao.internal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import swag.rest.bank_app_delivery.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


}