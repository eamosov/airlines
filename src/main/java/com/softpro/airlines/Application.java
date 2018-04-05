package com.softpro.airlines;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public DataSource dataSource(@Value("${spring.datasource.url}") String url,
//                                 @Value("${spring.datasource.username}") String user,
//                                 @Value("${spring.datasource.password}") String pass) {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(pass);
//        final HikariConfig conf = new HikariConfig();
//        conf.setDataSource(dataSource);
//        return new HikariDataSource(conf);
//    }

    @Bean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public CommandLineRunner loadInitialData(AirlinesService airlinesService) {
        return (args) -> {
            airlinesService.initAirplanes();
            airlinesService.initAirports();
            airlinesService.initRoutes();
            airlinesService.initFlights();
        };
    }

}