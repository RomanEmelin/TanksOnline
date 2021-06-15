package edu.school21.server;

import edu.school21.session.game.GameSession;
import edu.school21.session.room.SessionRoom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Thread session;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        SessionRoom.getRoom();
    }

    public void start() {
        System.out.println("Server started!");
        try {
            while (true) {
                clientSocket = serverSocket.accept();
                if (SessionRoom.isStarted()) {
                        session.join();
                        System.err.println("Can't join session thread");
                }
                if (!SessionRoom.isEmpty() && !SessionRoom.isStarted()) {
                    System.out.println("Let's fight begin!");
                    SessionRoom.setGameIsStarted(true);
                    session = new GameSession();
                    session.start();
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
