package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class Main {
    private static Client client;
    private static boolean firstLaunch = true;

    public static void main(String[] args) {
	    try {
	        client = new Client();
        } catch (UnknownHostException ex) {
	        ex.printStackTrace();
        } catch (SocketException ex) {
	        ex.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean stop = false;
            while (!stop) {

                String x = "";
                do {
                    System.out.print("Enter x: ");
                    x = br.readLine();
                } while(!matcher(x));

                String y = "";
                do {
                    System.out.print("Enter y: ");
                    y = br.readLine();
                } while (!matcher(y));

                System.out.println(client.sendEcho(x + " " + y));
            }
        } catch (IOException ex) {
	        ex.printStackTrace();
        }
    }

    private static boolean matcher(String value) {
        boolean result = Pattern.matches("[0-9]+(\\.)?([0-9]+)?", value);
        if (!result) System.out.println("Invalid Value");
        return result;
    }
}
