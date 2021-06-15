package edu.school21.session.room;

import java.net.Socket;

public final class SessionRoom {
    private static Socket player1Socket;
    private static Socket player2Socket;
    private static SessionRoom room;
    private SessionRoom() {}
    private static boolean gameIsStarted = false;
    private static boolean player1Connected = false, player2Connected = false;

    enum Player {
        FIRST,
        SECOND
    }

    public static SessionRoom getRoom() {
        if (room == null) {
            room = new SessionRoom();
        }
        return room;
    }

    public static boolean isEmpty() {
        return player1Connected || player2Connected;
    }

    public static boolean isStarted() {
        return gameIsStarted;
    }

    public static void setPlayerSocket(Socket socket, Player player) {
        if (player == Player.FIRST) {
            SessionRoom.player1Socket = socket;
        }
        else if (player == Player.SECOND) {
            SessionRoom.player2Socket = socket;
        }
    }

    public static boolean isConnected(Player player) {
        if (player == Player.FIRST) {
            return player1Connected;
        }
        else if (player == Player.SECOND) {
            return player2Connected;
        }
        return false;
    }

    public static Socket getPlayer1Socket() {
        return player1Socket;
    }

    public static Socket getPlayer2Socket() {
        return player2Socket;
    }

    public static void setGameIsStarted(boolean gameIsStarted) {
        SessionRoom.gameIsStarted = gameIsStarted;
    }
}
