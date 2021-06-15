package edu.school21.message;

import edu.school21.models.Bullet;
import edu.school21.models.Tank;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Response {

    public static void sendResponse(BufferedWriter out, Tank player, Tank enemy, LinkedList<Bullet> bullets) {
        JSONObject response = new JSONObject();
        response.put("player", player.toJson());
        response.put("enemy", enemy.toJson());
        JSONObject bulletsJson = new JSONObject();
        for (Bullet bullet: bullets) {
            JSONObject bulletJson = new JSONObject();
            bulletJson.put("bullet", bullet.toJson());
            bulletsJson.put(String.valueOf(bullets.indexOf(bullet)), bullet.toJson());
        }
        response.put("bullets", bulletsJson);
        try {
            out.write(response.toString());
            out.newLine();
            out.flush();
        } catch (IOException e) {
            System.err.println("Can't send answer to client");
        }
    }
}
