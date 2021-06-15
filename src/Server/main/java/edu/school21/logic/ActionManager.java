package edu.school21.logic;

import edu.school21.constants.Action;
import edu.school21.constants.Constants;
import edu.school21.models.Bullet;
import edu.school21.models.Tank;

import java.util.LinkedList;

public class ActionManager {

    public static void moveBullets(LinkedList<Bullet> bullets, Tank tank1, Tank tank2) {
        for (Bullet bullet: bullets) {
            bullet.move();
            if ((tank1.getSide() != bullet.getSide() && tank1.isGetDamage(bullet.getArea()))
                    || (tank2.getSide() != bullet.getSide() && tank2.isGetDamage(bullet.getArea())) ||
                    bullet.getArea().y > Constants.arenaHeight || bullet.getArea().y < 0) {
                bullets.remove(bullet);
            }
        }
    }

    public static boolean moveTanks(LinkedList<Bullet> bullets, Tank tank, Action action) {
        switch (action) {
            case LEFT: {
                tank.moveLeft();
                System.out.println("Player move left");
                return true;
            }
            case RIGHT: {
                tank.moveRight();
                System.out.println("Player move right");
                return true;
            }
            case SHOOT: {
                bullets.addLast(new Bullet(tank.getPosition(), tank.getSide()));
                System.out.println("Player make shoot");
                return true;
            }
            case OFFLINE: {
                System.out.println("Player is disconnected");
                return false;
            }
            case NONE:
                System.out.println("Player do nothing");
                return true;
        }
        return true;
    }
}
