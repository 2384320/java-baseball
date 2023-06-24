package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Integer> computerNumberList = new ArrayList<>();
        List<Integer> playerNumberList = new ArrayList<>();

        System.out.println("숫자 야구 게임을 시작합니다.");

        playGame(computerNumberList, playerNumberList);
    }

    private static void notRightInputForm() {
        throw new IllegalArgumentException();
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

    private static boolean isRightInputForm(String playerNumber) {
        return playerNumber.length() == 3 &&
                playerNumber.matches("[1-9]*");
    }

    public static void inputPlayerValue(
            List<Integer> playerNumberList
    ) {
        System.out.print("숫자를 입력해주세요 : ");
        String playerNumber = scanner.nextLine();

        if (!isRightInputForm(playerNumber)) notRightInputForm();
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

    public static void playGame(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        saveRandomNumber(computerNumberList);

        inputPlayerValue(playerNumberList);

        compareValue(computerNumberList, playerNumberList);
    }
}
