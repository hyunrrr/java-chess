package chess.dto;

import chess.domain.ChessBoard;
import chess.domain.Team;

public class WebChessStatusDto {
    private double blackScore;
    private double whiteScore;
    private Team winner;

    private WebChessStatusDto(double blackScore, double whiteScore, Team winner) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
        this.winner = winner;
    }

    public static WebChessStatusDto of(ChessBoard chessBoard) {
        return new WebChessStatusDto(chessBoard.calculateScore(Team.BLACK), chessBoard.calculateScore(Team.WHITE), chessBoard.judgeWinner());
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public Team getWinner() {
        return winner;
    }
}