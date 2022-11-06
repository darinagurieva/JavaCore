package jdbc_api;

import weather_api.Forecast;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ForecastRepository {

    private static final String DB_NAME = "jdbc:sqlite:forecast.db";

    public ForecastRepository() {

        try {
            Class.forName("org.sqlite.JDBC");

            try (
                var connection = DriverManager.getConnection(DB_NAME);
                var stmt = connection.createStatement();
            ) {

                stmt.execute(
                    """
                    CREATE TABLE IF NOT EXISTS "forecast" (
                        id integer primary key autoincrement,
                        city text not null ,
                        localDate text not null ,
                        weatherText text not null ,
                        temperature real not null
                    );
                    """
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(List<Forecast> forecastList) {

        String insertSql = "INSERT INTO \"forecast\" (city, localDate, weatherText, temperature) VALUES (?, ?, ?, ?)";

        try(
            var connection = DriverManager.getConnection(DB_NAME);
            var prepStmt = connection.prepareStatement(insertSql);
        ) {

            for (Forecast forecast : forecastList) {

                prepStmt.setString(1, forecast.city);
                prepStmt.setString(2, forecast.localDate);
                prepStmt.setString(3, forecast.weatherText);
                prepStmt.setDouble(4, forecast.temperature);
                prepStmt.addBatch();
            }

            prepStmt.executeBatch();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Forecast> getAll() {

        List<Forecast> result = new ArrayList<>();

        try(
            var connection = DriverManager.getConnection(DB_NAME);
            var stmt = connection.prepareStatement("SELECT  * FROM \"forecast\"");
            var resSet = stmt.executeQuery()
        ) {

            while (resSet.next()) {

                final Forecast forecast = new Forecast();
                result.add(forecast);
                forecast.city = resSet.getString("city");
                forecast.localDate = resSet.getString("localDate");
                forecast.weatherText = resSet.getString("weatherText");
                forecast.temperature = resSet.getDouble("temperature");
            }

            return result;
        }
        catch (Exception e) {

             throw new IllegalStateException(e);
        }
    }
}
