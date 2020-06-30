package laddergame.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LadderResultMapper {

  private final Map<PlayerName, ResultName> ladderResultMap;

  public LadderResultMapper(Map<PlayerName, ResultName> ladderResultMap) {
    this.ladderResultMap = ladderResultMap;
  }

  public static LadderResultMapper createBy(Ladder ladder, PlayerNames players,
      ResultNames results) {
    return new LadderResultMapper(Stream.iterate(0, positionNum -> positionNum + 1)
        .limit(players.getCountOfNames())
        .collect(Collectors.toMap(
            players::getNameByIndex,
            positionNum -> results.getNameByIndex(ladder.findResultPositionOf(new Position(positionNum))),
            (x, y) -> x,
            LinkedHashMap::new
        )));
  }

  public ResultName getResultOf(PlayerName playerName) {
    ResultName resultName = ladderResultMap.get(playerName);

    if (resultName == null) {
      throw new PlayerNotExistException();
    }

    return resultName;
  }

  public Set<Entry<PlayerName, ResultName>> getResults() {
    return Collections.unmodifiableSet(ladderResultMap.entrySet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LadderResultMapper that = (LadderResultMapper) o;
    return ladderResultMap.equals(that.ladderResultMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ladderResultMap);
  }

  @Override
  public String toString() {
    return "LadderResultMap{" +
        "ladderResultMap=" + ladderResultMap +
        '}';
  }
}