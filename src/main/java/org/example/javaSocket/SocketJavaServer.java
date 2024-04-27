package org.example.javaSocket;

import org.example.javaSocket.server.UDPServer;

/**
 *
 * @author randyt
 */
public class SocketJavaServer {

    public static void main(String[] args) {
        UDPServer server;
        server = new UDPServer();

        server.receive();
    }
}
