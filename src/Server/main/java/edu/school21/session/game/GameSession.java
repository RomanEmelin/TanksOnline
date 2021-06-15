package edu.school21.session.game;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.logic.ActionManager;
import edu.school21.message.Request;
import edu.school21.message.Response;
import edu.school21.models.Tank;
import edu.school21.models.Bullet;
import edu.school21.session.room.SessionRoom;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

public class GameSession extends Thread{
    private LinkedList<Bullet> bullets;
    private Tank tank1, tank2;
    private BufferedReader player1In;
    private BufferedReader player2In;
    private BufferedWriter player1Out;
    private BufferedWriter player2Out;
    private Socket player1Socket, player2Socket;

    public GameSession() {
        tank1 = new Tank(new Point(Constants.arenaWidth / 2,30), Side.UP);
        tank2 = new Tank(new Point(Constants.arenaWidth / 2,Constants.arenaHeight - 30), Side.DOWN);
        player1Socket = SessionRoom.getPlayer1Socket();
        player2Socket = SessionRoom.getPlayer2Socket();
        bullets = new LinkedList<>();
    }

    private void initGame() {
        try {
            player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            player1Out = new BufferedWriter(new OutputStreamWriter(player1Socket.getOutputStream()));
            player2Out = new BufferedWriter(new OutputStreamWriter(player2Socket.getOutputStream()));
            System.out.println("Let's fight begin!");
            player1Out.write("Start\n");
            player2Out.write("Start\n");
            player1Out.flush();
            player2Out.flush();
        } catch (IOException e) { e.printStackTrace();}
    }

    @Override
    public void run() {
        initGame();
        while (tank1.getHp() > 0 && tank2.getHp() > 0) {
            Response.sendResponse(player1Out, tank1, tank2, bullets);
            ActionManager.moveTanks(bullets, tank1, Request.getAction(player1In));
            ActionManager.moveBullets(bullets, tank1, tank2);
            Response.sendResponse(player2Out, tank2, tank1, bullets);
            ActionManager.moveTanks(bullets, tank2, Request.getAction(player2In));
            ActionManager.moveBullets(bullets, tank2, tank1);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.err.println("Can't thread sleep");
            }
        }
    }
}
