package org.example.javaSocket.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private DatagramSocket socket;
    private byte[] rxBuf;
    private byte[] txBuf;

    public UDPServer() {
        try {
            this.socket = new DatagramSocket(8081);
            this.rxBuf = new byte[1024];
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRxBuf(byte[] message) {
        this.rxBuf = message;
    }

    public byte[] getRxBuf() {
        return this.rxBuf;
    }

    public void setTxBuf(byte[] message) {
        this.txBuf = message;
    }

    public byte[] getTxBuf() {
        return this.txBuf;
    }

    /* UDPServer.receive() */
    public void receive() {
        while (true) {
            try {
                DatagramPacket inPacket;
                String resp;

                this.setRxBuf(new byte[1024]);

                inPacket = new DatagramPacket(this.rxBuf, this.rxBuf.length);

                this.socket.receive(inPacket);

                resp = new String(inPacket.getData(), 0, inPacket.getLength());

                System.out.println("Client Request: " + resp);

                this.send(inPacket);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* UDPServer.send() */
    public void send(DatagramPacket inPacket) {
        try {
            DatagramPacket outPacket;
            InetAddress clientAddr;
            int clientPort;
            byte[] encodedMessage;
            String message;

            message = "Hello from server.";

            clientAddr = inPacket.getAddress();
            clientPort = inPacket.getPort();

            encodedMessage = message.getBytes();
            setTxBuf(encodedMessage);

            outPacket = new DatagramPacket(encodedMessage, encodedMessage.length, clientAddr, clientPort);
            this.socket.send(outPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
