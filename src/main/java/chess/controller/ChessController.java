package chess.controller;

import chess.domain.ChessGame;
import chess.domain.board.Commands;
import chess.domain.board.Command;
import chess.domain.dto.BoardDto;
import chess.domain.utils.BoardInitializer;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    public void run() {
        ChessGame chessGame = new ChessGame();
        OutputView.printCommandInfo();
        while (chessGame.isRunning()) {
            final Commands inputCmd = InputView.inputCommand2();
            Command command = Command.of(inputCmd.mainCommand());
            command.apply(chessGame, inputCmd);
        }
    }

    public static void start(ChessGame chessGame, Commands command) {
        chessGame.initBoard(BoardInitializer.init());
        OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
    }

    public static void move(ChessGame chessGame, Commands command) {
        if (chessGame.isReady() || chessGame.isEnd()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았거나, 종료되지 않았습니다.");
        }
        chessGame.move(command);
        OutputView.printBoard(new BoardDto(chessGame.board(), chessGame.turn()));
        confirmKingDead(chessGame);
    }

    public static void end(ChessGame chessGame, Commands command) {
        chessGame.endGame();
    }

    public static void status(ChessGame chessGame, Commands command) {
        if (chessGame.isReady()) {
            throw new IllegalArgumentException("[ERROR] 게임이 초기화되지 않았습니다.");
        }
        OutputView.printStatus(chessGame.calculatePoint());
    }

    private static void confirmKingDead(ChessGame chessGame) {
        if (chessGame.isEnd()) {
            OutputView.printWinner(chessGame.winner());
        }
    }
}
