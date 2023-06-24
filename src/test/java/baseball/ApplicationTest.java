package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static baseball.Application.*;
import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest extends NsTest {
    @DisplayName("컴퓨터가 선정한 랜덤 값이 중복인지 확인")
    @Test
    void checkDuplicateRandomNumber() {
        List<Integer> list = saveRandomNumber();

        HashSet<Integer> hs = new HashSet<>(list);

        assertThat(hs.size()).isEqualTo(list.size());
    }

    @DisplayName("컴퓨터가 선정한 랜덤 값이 범위 내인지 확인")
    @Test
    void checkRandomNumberInRange() {
        List<Integer> list = saveRandomNumber();

        assertThat(list.stream().allMatch(v -> v >= 1 && v <= 9)).isTrue();
    }

    @DisplayName("사용자 값이 제대로 저장이 됐는지 확인")
    @Test
    void checkItIsSavedProperly() {
        List<Integer> list = new ArrayList<>();

        savePlayerNumber(list, "123");

        assertThat(list).isEqualTo(new ArrayList<>(List.of(1, 2, 3)));
    }

    @DisplayName("사용자 값이 제대로 입력되지 않으면 에러가 발생하는지 확인")
    @Test
    void checkIfNotRightValue() {
        List<Integer> list = new ArrayList<>();
        List<String> exceptionString = new ArrayList<>(List.of("1234", "011", "h11", "11", "111", "112"));
        for (String testString : exceptionString) {
            assertThrows(IllegalArgumentException.class,
                    () -> savePlayerNumber(list, testString));
        }
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
