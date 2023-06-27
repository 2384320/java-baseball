package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static baseball.Application.*;
import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest extends NsTest {
    @DisplayName("조건에 충족하는 사용자 값이 입력됐을 때 리스트 형식으로 저장되는지 확인")
    @Test
    void checkItIsSavedProperly() {
        List<Integer> playerNumberList = new ArrayList<>();

        savePlayerNumber(playerNumberList, "123");

        assertThat(playerNumberList).isEqualTo(new ArrayList<>(List.of(1, 2, 3)));
    }

    @DisplayName("사용자 값이 제대로 입력되지 않으면 에러가 발생하는지 확인")
    @Test
    void checkIfNotRightValue() {
        List<Integer> playerNumberList = new ArrayList<>();
        List<String> exceptionString = new ArrayList<>(List.of("1234", "011", "h11", "11", "111", "112"));
        for (String testString : exceptionString) {
            assertThrows(IllegalArgumentException.class,
                    () -> savePlayerNumber(playerNumberList, testString));
        }
    }

    @DisplayName("스트라이크 수가 올바른지 확인")
    @Test
    void isCorrectStrikeCount() {
        List<Integer> computerNumberList = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> playerNumberList = new ArrayList<>(List.of(1, 2, 3));
        assertThat(getStrike(computerNumberList, playerNumberList)).isEqualTo(3);
    }

    @DisplayName("포함되어있는 숫자 수가 올바른지 확인")
    @Test
    void isCorrectContainCount() {
        List<Integer> computerNumberList = new ArrayList<>(List.of(3, 2, 1));
        List<Integer> playerNumberList = new ArrayList<>(List.of(1, 2, 3));
        assertThat(getContainCount(computerNumberList, playerNumberList)).isEqualTo(3);
    }

    @DisplayName("값 비교 시 알맞은 결과가 출력되는지 확인")
    @Test
    void isRightResult() {
        assertThat(getPrintResult(3, 0)).isEqualTo("3스트라이크");
        assertThat(getPrintResult(1, 0)).isEqualTo("1스트라이크");
        assertThat(getPrintResult(0, 1)).isEqualTo("1볼");
        assertThat(getPrintResult(1, 1)).isEqualTo("1볼 1스트라이크");
        assertThat(getPrintResult(0, 0)).isEqualTo("낫싱");
    }

    @DisplayName("제대로 된 값을 입력하였을 때 true 반환되는지 확인")
    @Test
    void isRightReplayInputForm() {
        assertThat(isRightReplayCommandForm("1")).isTrue();
        assertThat(isRightReplayCommandForm("2")).isTrue();
        assertThat(isRightReplayCommandForm("3")).isFalse();
    }

    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("246", "135", "1", "597", "589", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                1, 3, 5, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatCode(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
