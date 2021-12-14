package ladder.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PointTest {
    @Test
    @DisplayName("Point 생성자 테스트")
    void constructor() {
        assertThat(new Point(false, false)).isEqualTo(new Point(false, false));
    }

    @Test
    @DisplayName("첫번째 Point 생성")
    void firstPoint() {
        assertThat(Point.firstPoint(true)).isEqualTo(Point.firstPoint(true));
    }

    @Test
    @DisplayName("마지막 Point 생성")
    void lastPoint() {
        assertThat(Point.lastPoint(true)).isEqualTo(Point.lastPoint(true));
    }

    @Test
    @DisplayName("Point에 따라 라인을 생성한다.")
    void getResultPoint() {
        assertThat(new Point(false, false).getResultPoint()).isEqualTo("     ");
        assertThat(new Point(false, true).getResultPoint()).isEqualTo("-----");
    }
}