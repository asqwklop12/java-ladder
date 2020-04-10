package ladder;

import ladder.model.LadderBridge;
import ladder.model.LadderLine;
import ladder.model.LadderPole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("사다리 라인(가로) 테스트")
public class LadderLineTests {

    @DisplayName("라인 생성 테스트")
    @Test
    public void generateLadderLineTests() {
        assertThatCode(() -> LadderLine.newInstance(7)).doesNotThrowAnyException();
    }

    @DisplayName("라인 생성 - 비정상 테스트")
    @Test
    public void generateLadderLineAbnormalTests() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LadderLine.newInstance(-13))
                .withMessageContaining("Ladder Pole count must be greater than zero.");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> LadderLine.newInstance(0))
                .withMessageContaining("Ladder Pole count must be greater than zero.");
    }

    @DisplayName("라인 생성 - 사이즈 테스트")
    @Test
    public void compareLadderLineTests() {
        LadderLine ladderLine = LadderLine.newInstance(7);
        assertThat(ladderLine.poleCount()).isEqualTo(7);
    }

    @DisplayName("사다리 라인 움직임 테스트")
    @Test
    public void nextLadderPolesTests() {
        LadderLine ladderLine = LadderLine.newInstance(LadderBridge.UN_EXIST, LadderBridge.EXIST, LadderBridge.UN_EXIST);

        assertThat(ladderLine.moveLadderPole(LadderPole.of(0))).isEqualTo(LadderPole.of(0));
        assertThat(ladderLine.moveLadderPole(LadderPole.of(1))).isEqualTo(LadderPole.of(2));
        assertThat(ladderLine.moveLadderPole(LadderPole.of(2))).isEqualTo(LadderPole.of(1));
        assertThat(ladderLine.moveLadderPole(LadderPole.of(3))).isEqualTo(LadderPole.of(3));
    }

    @DisplayName(" (가로 라인이 연속으로 나오지 않는지) 테스트")
    @Test
    public void generateLadderConsecutiveBridgeTests() {
        LadderLine ladderLine = LadderLine.newInstance(5);
        List<LadderBridge> bridges = ladderLine.getBridges();

        LadderBridge preLadderBridge = LadderBridge.UN_EXIST;
        for (int i = 0; i < bridges.size(); i++) {
            assertTrue(validateConsecutiveBridge(preLadderBridge, bridges.get(i)));
            preLadderBridge = bridges.get(i);
        }
    }

    private static boolean validateConsecutiveBridge(LadderBridge preBridge, LadderBridge nowBridge) {
        return preBridge != nowBridge || nowBridge == LadderBridge.UN_EXIST;
    }
}