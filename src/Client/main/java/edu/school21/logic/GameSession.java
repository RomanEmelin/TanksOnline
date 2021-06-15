package edu.school21.logic;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import edu.school21.models.Bullet;
import edu.school21.models.Tank;
import org.json.JSONObject;

public class GameSession extends Thread {
    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;
    private int delay = 0;
    private final String server;
    private final int port;
    private final long delayForShoot = 1000L;

    private void initSession() {
        try {
            socket = new Socket(server, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println("Can't connect to server");;
        }
        System.out.println("Connection established");
    }

    public GameSession(String server, int port) {
        this.server = server;
        this.port = port;
        initSession();
    }

    @Override
    public void run() {
        long lastShoot = 0;
        while (true) {
            String answer = "";
            try {
                answer = in.readLine();
            } catch (IOException e) {
                System.err.println("Can't take response from server");
            }
            String response = "";
            switch (action) {
                case 1: {
                    response += "LEFT";
                    break;
                }
                case 2: {
                    response += "RIGHT";
                    break;
                }
                case 3: {
                    if (System.currentTimeMillis() - lastShoot < delayForShoot) {
                        System.out.println("Reload gun!");
                        break;
                    }
                    lastShoot = System.currentTimeMillis();
                    response += "SHOOT";
                }
                default: {
                    response += "NONE";
                }
            }
            try {
                out.write(response);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                System.err.println("Can't send answer to server");
            }
            JSONObject answerJson = new JSONObject(answer);
            JSONObject myJson = new JSONObject(answerJson.get("player").toString());
            Tank myTank = new Tank(new Point(myJson.getInt("x"), myJson.getInt("y")));
            JSONObject enemyJson = new JSONObject(answerJson.get("enemy").toString());
            Tank enemyTank = new Tank(new Point(enemyJson.getInt("x"), enemyJson.getInt("y")));
            JSONObject bulletsJson = new JSONObject(answerJson.get("bullets").toString());
            LinkedList<Bullet> bullets = new LinkedList<>();
            for (int i = 0; i < bulletsJson.size(); i++) {
                JSONObject bulletJson = new JSONObject(bulletsJson.get(String.valueOf(i)));
                bullets.add(new Bullet(new Point(bulletJson.getInt("x"), bulletJson.getInt("y"))));
            }
        }
    }

}
