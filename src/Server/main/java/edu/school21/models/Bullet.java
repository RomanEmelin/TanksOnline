package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import org.json.JSONObject;

import java.awt.*;

public class Bullet {
    private Side side;
    private Point position;
    private Rectangle area;

    public Bullet(Point position, Side side) {
        this.position = position;
        this.side = side;
    }

    public Rectangle getArea() {
        return area;
    }

    public void move() {
        if (side == Side.DOWN) {
            position.y -= Constants.bulletSpeed;
        }
        else
            position.y += Constants.bulletSpeed;
        area.setRect(position.x, position.y, Constants.bulletWidth, Constants.bulletHeight);
    }

    public Side getSide() {
        return side;
    }

    public JSONObject toJson() {
        JSONObject bulletJson = new JSONObject();
        bulletJson.put("x", position.x);
        if (side == Side.DOWN) {
            bulletJson.put("y", position.y);
        }
        else if (side == Side.UP) {
            bulletJson.put("y", Constants.arenaHeight - position.y);
        }
        return bulletJson;
    }
}
