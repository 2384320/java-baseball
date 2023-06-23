package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private final static Scanner scanner = new Scanner(System.in);
    private final static int gameStart = 1;
    public static void main(String[] args) {
        List<Integer> computerNumberList = new ArrayList<>();
        List<Integer> playerNumberList = new ArrayList<>();

        do {
            setGame(computerNumberList);
            inputPlayerValue(playerNumberList);



        } while (false);

    }

    private static String getOutputString(int command) {
        String outputString = "";
        if (command == gameStart) outputString = "숫자 야구 게임을 시작합니다.";

        return outputString;
    }

    private static void printOutputString(int command) {
        System.out.println(getOutputString(command));
    }

    private static void savePlayerNumber(
            List<Integer> playerNumberList,
            String playerNumber
    ) {
        for (String number : playerNumber.split("")) {
            playerNumberList.add(Integer.parseInt(number));
        }
    }

    private static void notRightInputForm() {
        throw new IllegalArgumentException();
    }

    private static boolean isRightInputLength(String playerNumber) {
        return playerNumber.length() == 3;
    }

    public static void inputPlayerValue(List<Integer> playerNumberList) {
        String playerNumber = scanner.nextLine();

        if (!isRightInputLength(playerNumber) ||
                !playerNumber.matches("[1-9]")) notRightInputForm();

        savePlayerNumber(playerNumberList, playerNumber);
    }

    private static boolean isDuplicate(
            List<Integer> computerNumberList,
            int randomNumber
    ) {
        return computerNumberList.contains(randomNumber);
    }

    private static int getRandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
    }

    private static void saveRandomNumber(
            List<Integer> computerNumberList
    ) {
        while (computerNumberList.size() < 3) {
            int randomNumber = getRandomNumber();
            if (isDuplicate(computerNumberList, randomNumber)) continue;
            computerNumberList.add(randomNumber);
        }
    }

    public static void setGame(
            List<Integer> computerNumberList
    ) {
        printOutputString(gameStart);
        saveRandomNumber(computerNumberList);
    }
}
