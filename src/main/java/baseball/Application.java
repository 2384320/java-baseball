package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        playGame();
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

    private static int getBall(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int ballCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) continue;
                if (isEqualsValue(
                        computerNumberList.get(i),
                        playerNumberList.get(j)
                )) ballCount++;
            }
        }

        return ballCount;
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

    public static boolean compareValue(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int strikeCount = getStrike(computerNumberList, playerNumberList);
        if (strikeCount == 3) {
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            return true;
        }
        int ballCount = getBall(computerNumberList, playerNumberList);

        if (strikeCount == 0 && ballCount == 0) System.out.println("낫싱");
        else if (strikeCount == 0) System.out.println(ballCount + "볼");
        else if (ballCount == 0) System.out.println(strikeCount + "스트라이크");
        else System.out.println(ballCount + "볼 " + strikeCount + "스트라이크");
        return false;
    }

    private static void savePlayerNumber(
            List<Integer> playerNumberList,
            String playerNumber
    ) {
        if (!isRightInputForm(playerNumber)) notRightInputForm();

        for (String oneNumber : playerNumber.split("")) {
            int number = Integer.parseInt(oneNumber);
            if (isDuplicate(playerNumberList, number)) notRightInputForm();
            playerNumberList.add(number);
        }
    }

    private static boolean isRightInputForm(String playerNumber) {
        return playerNumber.length() == 3 &&
                playerNumber.matches("[1-9]*");
    }

    public static List<Integer> inputPlayerValue() {
        List<Integer> playerNumberList = new ArrayList<>();
        System.out.print("숫자를 입력해주세요 : ");
        String playerNumber = scanner.nextLine();

        savePlayerNumber(playerNumberList, playerNumber);

        return playerNumberList;
    }

    public static void inputAndCompareValue(
            List<Integer> computerNumberList
    ) {
        List<Integer> playerNumberList;
        do {
            playerNumberList = inputPlayerValue();
        } while (!compareValue(computerNumberList, playerNumberList));
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

    private static List<Integer> saveRandomNumber() {
        List<Integer> computerNumberList = new ArrayList<>();
        while (computerNumberList.size() < 3) {
            int randomNumber = getRandomNumber();
            if (isDuplicate(computerNumberList, randomNumber)) continue;
            computerNumberList.add(randomNumber);
        }

        return computerNumberList;
    }

    public static void playGame() {
        List<Integer> computerNumberList = saveRandomNumber();

        inputAndCompareValue(computerNumberList);
    }
}
