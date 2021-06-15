package edu.school21.application;

import edu.school21.server.Server;

import java.io.IOException;

public class Main {

    private static final int FAILURE = -1;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Invalid number of arguments");
            System.exit(FAILURE);
        }
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        if (!args[0].equals("--port=" + port)) {
            System.err.println("Invalid argument");
            System.exit(FAILURE);
        }
        Server server = new Server(port);
        server.start();
    }
}
