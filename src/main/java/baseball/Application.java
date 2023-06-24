package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private final static int gameStartCommand = 1;
    private final static int inputPlayerValueCommand = 2;

    public static void main(String[] args) {
        List<Integer> computerNumberList = new ArrayList<>();
        List<Integer> playerNumberList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        do {
            setGame(computerNumberList);

            printOutputString(inputPlayerValueCommand);
            String playerNumber = scanner.nextLine();
            inputPlayerValue(playerNumberList, playerNumber);

            compareValue(computerNumberList, playerNumberList);



        } while (false);

    }

    private static String getOutputString(int command) {
        String outputString = "";
        if (command == gameStartCommand) outputString = "숫자 야구 게임을 시작합니다.\n";
        else if (command == inputPlayerValueCommand) outputString = "숫자를 입력해주세요 : ";

        return outputString;
    }

    private static void printOutputString(int command) {
        System.out.print(getOutputString(command));
    }

    private static boolean isEqualsValue(
            int computerNumber,
            int playerNumber
    ) {
        return computerNumber == playerNumber;
    }

    private static int getStrike(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int strikeCount = 0;
        for (int i = 0; i < 3; i++) {
            if (isEqualsValue(
                    computerNumberList.get(i),
                    playerNumberList.get(i)
            )) strikeCount++;
        }

        return strikeCount;
    }

    public static void compareValue(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int strikeCount = getStrike(computerNumberList, playerNumberList);
        if (strikeCount == 3) return;
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

    public static void inputPlayerValue(
            List<Integer> playerNumberList,
            String playerNumber
    ) {
        if (!isRightInputLength(playerNumber) ||
                !playerNumber.matches("[1-9]*")) notRightInputForm();

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
        printOutputString(gameStartCommand);
        saveRandomNumber(computerNumberList);
    }
}
