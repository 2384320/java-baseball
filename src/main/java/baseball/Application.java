package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    private static final String REPLAY_GAME = "1";
    private static final String FINISH_GAME = "2";
    public static void main(String[] args) {
        System.out.println("숫자 야구 게임을 시작합니다.");
        playGame();
    }

    public static boolean isRightReplayCommandForm(String replayCommand) {
        return replayCommand.equals(REPLAY_GAME) || replayCommand.equals(FINISH_GAME);
    }

    public static boolean setGameOver() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String replayCommand = readLine();
        if (!isRightReplayCommandForm(replayCommand)) throw new IllegalArgumentException();

        return replayCommand.equals(REPLAY_GAME);
    }

    public static String getPrintResult(
            int strikeCount,
            int ballCount
    ) {
        String result;
        if (strikeCount == 3) result = "3스트라이크";
        else if (strikeCount == 0 && ballCount == 0) result = "낫싱";
        else if (strikeCount == 0) result = ballCount + "볼";
        else if (ballCount == 0) result = strikeCount + "스트라이크";
        else result = ballCount + "볼 " + strikeCount + "스트라이크";
        return result;
    }

    public static int getContainCount(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int containCount = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumberList.contains(playerNumberList.get(i))) containCount++;
        }
        return containCount;
    }

    public static int getStrike(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int strikeCount = 0;
        for (int i = 0; i < 3; i++) {
            if (computerNumberList.get(i).equals(playerNumberList.get(i))) strikeCount++;
        }
        return strikeCount;
    }

    public static boolean isThreeStrike(
            List<Integer> computerNumberList,
            List<Integer> playerNumberList
    ) {
        int strikeCount = getStrike(computerNumberList, playerNumberList);
        int containCount = getContainCount(computerNumberList, playerNumberList);
        int ballCount = containCount - strikeCount;
        System.out.println(getPrintResult(strikeCount, ballCount));
        return strikeCount == 3;
    }

    public static void savePlayerNumber(
            List<Integer> playerNumberList,
            String playerNumber
    ) {
        if (!isRightInputForm(playerNumber)) throw new IllegalArgumentException();

        for (String oneNumber : playerNumber.split("")) {
            int number = Integer.parseInt(oneNumber);
            if (isDuplicate(playerNumberList, number)) throw new IllegalArgumentException();
            playerNumberList.add(number);
        }
    }

    public static boolean isRightInputForm(String playerNumber) {
        return playerNumber.length() == 3 &&
                playerNumber.matches("[1-9]*");
    }

    public static List<Integer> inputPlayerValue() {
        List<Integer> playerNumberList = new ArrayList<>();
        System.out.print("숫자를 입력해주세요 : ");
        String playerNumber = readLine();

        savePlayerNumber(playerNumberList, playerNumber);

        return playerNumberList;
    }

    public static void inputAndCompareValue(
            List<Integer> computerNumberList
    ) {
        List<Integer> playerNumberList;
        do {
            playerNumberList = inputPlayerValue();
        } while (!isThreeStrike(computerNumberList, playerNumberList));
    }

    public static boolean isDuplicate(
            List<Integer> computerNumberList,
            int randomNumber
    ) {
        return computerNumberList.contains(randomNumber);
    }

    public static int getRandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
    }

    public static List<Integer> saveRandomNumber() {
        List<Integer> computerNumberList = new ArrayList<>();
        while (computerNumberList.size() < 3) {
            int randomNumber = getRandomNumber();
            if (isDuplicate(computerNumberList, randomNumber)) continue;
            computerNumberList.add(randomNumber);
        }

        return computerNumberList;
    }

    public static void playGame() {
        do {
            List<Integer> computerNumberList = saveRandomNumber();
            inputAndCompareValue(computerNumberList);
        } while (setGameOver());
    }
}
