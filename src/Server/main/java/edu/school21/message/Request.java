package edu.school21.message;

import edu.school21.constants.Action;

import java.io.BufferedReader;
import java.io.IOException;

public class Request {

    private static final long TIME_TO_REQUEST = 10000l;
    public static Action getAction(BufferedReader in) {
        try {
            long begin = System.currentTimeMillis();
            while (begin - System.currentTimeMillis() < TIME_TO_REQUEST) {
                if (in.ready()) {
                    String userAction = in.readLine();
                    for (Action action: Action.values()) {
                        if (action.toString().equals(userAction)) {
                            return action;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Can't read request from player");
        }
        return Action.OFFLINE;
    }
}
