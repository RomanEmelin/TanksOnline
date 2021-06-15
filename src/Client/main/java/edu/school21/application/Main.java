package edu.school21.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.Socket;

public class Main {

    private static final int FAILURE = -1;
    private Socket playerSocket;
    BufferedReader in;
    BufferedWriter out;



    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Invalid number of arguments");
            System.exit(FAILURE);
        }
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        if (!args[0].equals("--server-port=" + port)) {
            System.err.println("Invalid argument");
            System.exit(FAILURE);
        }

    }
}
