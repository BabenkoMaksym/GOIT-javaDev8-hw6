package ua.goit.java8.dev.hw6;


import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;

public class InitDB {

    private final static String DB_URL ="jdbc:postgresql://localhost:5432/goit_java8_hw6";
    private final static String DB_USER = "maksbbn";
    private final static String DB_PASSWORD = "qwerty";

    private static final Logger LOG = Logger.getLogger(InitDB.class);
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource(DB_URL, DB_USER, DB_PASSWORD).load();
        flyway.migrate();
    }
}