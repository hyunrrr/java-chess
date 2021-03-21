package chess.domain.piece;

import chess.domain.location.Location;
import chess.domain.team.Team;

public class Queen extends Piece {

    private Queen(final Location location, final Team team) {
        super(location, team);
    }

    public static Queen of(Location location, Team team) {
        return new Queen(location, team);
    }

    @Override
    public void validateMovingAbilityToTarget(Location target) {
        if (!(location.canMoveHorizontallyOrVerticallyTo(target) || location.canMoveDigonallyTo(target))) {
            throw new IllegalArgumentException("[ERROR] 퀸은 해당 위치로 이동할 능력이 없습니다.");
        }
    }
}
