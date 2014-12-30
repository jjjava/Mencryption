package br.com.schumaker.network;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author hudson schumaker
 */
public class MessageRX {

    private boolean run;
    public static ServerSocket server;
    public static Socket incoming;

    public MessageRX() {
        try {
            server = new ServerSocket(HsCommons.RXPORT);
            run = true;
        } catch (Exception e) {
            System.err.println("MessageRX:\n" + e);
        }
        while (run) {
            try {
                incoming = server.accept();
                new MessageRxSession(incoming).start();
            } catch (Exception e) {
                System.err.println("MessageRX2:\n" + e);
            }
        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
