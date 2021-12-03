package com.vesa.testprojmain.repository;

import com.vesa.testprojmain.domain.Plant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Properties;

@Slf4j
@Repository
public class PlantRepository {

    @Value("${application.postgres.username:appuser}")
    private String dbUsername;
    @Value("${application.postgres.password:appuser}")
    private String dbPassword;
    @Value("${application.postgres.host:localhost}")
    private String dbHost;
    @Value("${application.postgres.port:5432}")
    private String dbPort;
    @Value("${application.postgres.db.name:app_pg}")
    private String dbName;

    public void registerPlant(final Plant plant) {
        try {
            final String url = String.format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName);
            final Properties props = new Properties();
            props.setProperty("user", dbUsername);
            props.setProperty("password", dbPassword);
            final Connection conn = DriverManager.getConnection(url, props);

            final Statement st = conn.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM plant_table");
            while (rs.next()) {
                log.info("Column 1 returned ");
                log.warn(rs.getString(1));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            log.warn("Not able to establish connection to DB");
        }
    }

}
