package org.example.javaSocket.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    private DatagramSocket socket;
    private byte[] rxBuf;
    private byte[] txBuf;

    public UDPClient() {
        try {
            this.socket = new DatagramSocket();
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

    /* UDPClient.send() */
    public void send(String message) {
        try {
            DatagramPacket outPacket;
            byte[] encodedMessage;
            InetAddress ip = InetAddress.getByName("localhost");

            encodedMessage = message.getBytes();

            this.setTxBuf(encodedMessage);

            outPacket = new DatagramPacket(encodedMessage, encodedMessage.length, ip, 8081);

            this.socket.send(outPacket);

            this.receive();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (this.socket != null) {
                this.socket.close();
            }
        }

    }

    /* UDPClient.receive() */
    public void receive() {
        try {
            DatagramPacket inPacket;
            String resp;

            this.setRxBuf(new byte[1024]);

            inPacket = new DatagramPacket(this.rxBuf, this.rxBuf.length);

            this.socket.receive(inPacket);

            resp = new String(inPacket.getData(), 0, inPacket.getLength());

            System.out.println("Server Response: " + resp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
