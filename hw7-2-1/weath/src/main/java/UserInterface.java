import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private final Controller controller = new Controller();

    public void runApplication() {
        Scanner scanner = new Scanner(System.in);
        try {

            while (true) {
                System.out.println("Введите название города латиницей");
                String city = scanner.nextLine();

                setGlobalCity(city);

                System.out.println("Введите цифру: 1 - получить текущую погоду, " +
                        "2 - Получить погоду на следующие 5 дней, " +
                        "выход (exit) - завершить работу");
                String result = scanner.nextLine();

                checkIsExit(result);

                try {
                    validateUserInput(result);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                try {
                    notifyController(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } finally {
            scanner.close();
        }
    }

    private void checkIsExit(String result) {
        if (result.toLowerCase().equals("выход") || result.toLowerCase().equals("exit")) {
            System.out.println("Завершаю работу");
            System.exit(0);
        }
    }

    private void setGlobalCity(String city) {
        ApplicationGlobalState.getInstance().setSelectedCity(city);
    }

    int answer = 0;

    private void validateUserInput(String userInput) throws IOException {

        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Incorrect user input, write one digit, not " + userInput);
        }

        try {
            answer = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IOException("Incorrect user input, one digit expected");
        }
    }

    private void notifyController(String input) throws IOException {
        controller.onUserInput(input);
    }
}
