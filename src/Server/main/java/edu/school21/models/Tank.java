package edu.school21.models;

import edu.school21.constants.Constants;
import edu.school21.constants.Side;
import edu.school21.session.game.GameSession;

import org.json.JSONObject;

import java.awt.*;

public class Tank {
    private int hp;
    private Rectangle area;
    private Point position;
    private Side side;

    public Tank(Point position, Side side) {
        this.position = position;
        this.side = side;
        hp = 100;
        area = new Rectangle(position.x, position.y, Constants.arenaWidth, Constants.arenaHeight);
    }

    public int getHp() {
        return hp;
    }

    public Rectangle getArea() {
        return area;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isGetDamage(Rectangle bullet) {
        if (area.intersects(bullet)) {
            hp -= 5;
            return true;
        }
        return false;
    }

    public Side getSide() {
        return side;
    }

    public void moveLeft() {
        position.x -= Constants.tankSpeed;
    }

    public void moveRight() {
        position.x += Constants.tankSpeed;
    }

    public JSONObject toJson() {
        JSONObject tankJson = new JSONObject();
        if (side == Side.DOWN) {
            tankJson.put("hp", hp).put("x", position.x).put("y", position.y);
        }
        else if (side == Side.UP) {
            tankJson.put("hp", hp).put("x", position.x).put("y", Constants.arenaHeight - position.y);
        }
        return tankJson;
    }
}
