package br.com.schumaker.network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author hudson schumaker
 */
public class KeepMe implements Runnable {

    private boolean run;

    private void response() {
        try {
            DatagramSocket dsocket = new DatagramSocket(HsCommons.KPORT);
            byte[] buffer = new byte[HsCommons.BUFFER];
            System.out.println("KeepMe system started...");
            System.out.println("KeepMe system listening on port: " + HsCommons.KPORT);
            while (isRun()) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                dsocket.receive(packet);
                String msg = new String(buffer, 0, packet.getLength());
                if (msg.equalsIgnoreCase(HsCommons.ALIVE)) {
                    byte[] response = HsCommons.OK.getBytes();
                    packet = new DatagramPacket(response, response.length, packet.getAddress(), packet.getPort());
                    dsocket.send(packet);
                }
            }
        } catch (Exception e) {
            System.err.println("KeepMe:response:\n" + e);
        }
    }

    @Override
    public void run() {
        response();
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setName("KeepMe");
        thread.setPriority(Thread.MIN_PRIORITY);
        run = true;
        thread.start();
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
