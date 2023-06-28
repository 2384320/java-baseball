package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"1234", "011", "h11", "11", "111", "112"})
    @DisplayName("사용자 값이 제대로 입력되지 않으면 에러가 발생하는지 확인")
    void checkIfNotRightValue(String testNumber) {
        List<Integer> playerNumberList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class,
                () -> savePlayerNumber(playerNumberList, testNumber));
    }

    @DisplayName("비교하는 두 값이 같을 경우 스트라이크 수가 3이 나오는 지 확인")
    @Test
    void isCorrectStrikeCount() {
        List<Integer> computerNumberList = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> playerNumberList = new ArrayList<>(List.of(1, 2, 3));
        assertThat(getStrikeCount(computerNumberList, playerNumberList)).isEqualTo(3);
    }

    @DisplayName("숫자 구성이 같은 두 값을 비교할 때, 포함되어있는 수가 3이 나오는지 확인")
    @Test
    void isCorrectContainCount() {
        List<Integer> computerNumberList = new ArrayList<>(List.of(3, 2, 1));
        List<Integer> playerNumberList = new ArrayList<>(List.of(1, 2, 3));
        assertThat(getContainCount(computerNumberList, playerNumberList)).isEqualTo(3);
    }

    @DisplayName("스트라이크 수와 볼 수가 주어질 때 알맞은 결과 문자열이 반환되는지 확인")
    @Test
    void isRightReturnResultString() {
        assertAll(
                () -> assertEquals(getPrintResult(3, 0), "3스트라이크"),
                () -> assertEquals(getPrintResult(2, 0), "2스트라이크"),
                () -> assertEquals(getPrintResult(1, 0), "1스트라이크"),
                () -> assertEquals(getPrintResult(0, 1), "1볼"),
                () -> assertEquals(getPrintResult(0, 2), "2볼"),
                () -> assertEquals(getPrintResult(0, 3), "3볼"),
                () -> assertEquals(getPrintResult(1, 1), "1볼 1스트라이크"),
                () -> assertEquals(getPrintResult(1, 2), "2볼 1스트라이크"),
                () -> assertEquals(getPrintResult(0, 0), "낫싱")
        );
    }

    @DisplayName("게임 중단 명령에 1과 2를 제외한 값을 입력하면 false가 반환되는지 확인")
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
