/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.example.javaSocket;

import org.example.javaSocket.client.UDPClient;
import org.example.javaSocket.server.UDPServer;

;

/**
 *
 * @author randyt
 */
public class SocketJavaClient {

    public static void main(String[] args) {
        UDPServer server;
        UDPClient client;
        String message;

        server = new UDPServer();
        client = new UDPClient();

        message = "Hello from client";

        client.send(message);
    }
}
