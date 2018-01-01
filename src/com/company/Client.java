package com.company;

import java.io.IOException;
import java.net.*;

public class Client {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf = new byte[256];

    public Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) {
        try {
            buf = msg.getBytes();
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length, address, 4445);
            socket.send(packet);

            byte[] notBuf = new byte[256];
            packet = new DatagramPacket(notBuf, notBuf.length);
            socket.receive(packet);
            String received = new String(
                    packet.getData(), 0, packet.getLength());

            return received;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void close() {
        socket.close();
    }
}
