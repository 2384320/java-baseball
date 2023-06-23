package baseball;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

public class Application {
    private final static int gameStart = 1;
    public static void main(String[] args) {
        List<Integer> computerNumber = new ArrayList<>();

        do {
            setGame(computerNumber);




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

    private static boolean isDuplicate(List<Integer> computerNumber, int randomNumber) {
        return computerNumber.contains(randomNumber);
    }

    private static int getRandomNumber() {
        return Randoms.pickNumberInRange(1, 9);
    }

    public static void setGame(List<Integer> computerNumber) {
        printOutputString(gameStart);

        while (computerNumber.size() < 3) {
            int randomNumber = getRandomNumber();
            if (isDuplicate(computerNumber, randomNumber)) continue;
            computerNumber.add(randomNumber);
        }
    }
}
