import jdbc_api.ForecastRepository;
import weather_api.ApplicationGlobalState;
import weather_api.Controller;
import weather_api.Forecast;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {

    private final Scanner scanner = new Scanner(System.in);
    private final Controller controller = new Controller();
    private final ForecastRepository forecastRepository = new ForecastRepository();

    public void start() {

        while (true) {

            try {
                System.out.println("\n1 - поиск погоды\n2 - все данные\nвыход (exit) - завершить работу");
                String choice = scanner.nextLine();
                checkIsExit(choice);

                switch (choice) {
                    case "2": {

                        System.out.println(
                            forecastRepository.getAll().stream()
                                .map(Forecast::toString)
                                .collect(Collectors.joining("\n"))
                        );
                        break;
                    }
                    case "1": {

                        findWeather();
                        break;
                    }
                    default:
                        System.out.println("Неизвестный ввод: " + choice);
                }
            }
            catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    public void findWeather() {

        System.out.println("Введите название города латиницей");
        String city = scanner.nextLine();

        setGlobalCity(city);

        System.out.println("1 - получить текущую погоду\n2 - получить погоду на следующие 5 дней");
        String result = scanner.nextLine();

        try {
            validateUserInput(result);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try {
            notifyController(result);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkIsExit(String result) {
        if ("выход".equalsIgnoreCase(result) || "exit".equalsIgnoreCase(result)) {
            System.out.println("Завершаю работу");
            scanner.close();
            System.exit(0);
        }
    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    private void validateUserInput(String userInput) throws IOException {

        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Incorrect user input, write one digit, not " + userInput);
        }

        try {
            Integer.parseInt(userInput);
        }
        catch (NumberFormatException e) {
            throw new IOException("Incorrect user input, one digit expected");
        }
    }

    private void notifyController(String input) throws IOException {
        controller.onUserInput(input);
    }
}
