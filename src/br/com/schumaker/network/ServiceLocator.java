package br.com.schumaker.network;

import br.com.schumaker.core.Engine;
import br.com.schumaker.io.WelcomeSmsCreator;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author hudson schumaker
 */
public class ServiceLocator implements Runnable {
    
    public ServiceLocator() {
    }
    
    public void start() {
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
    
    @Override
    public void run() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            byte[] ip = localhost.getAddress();
            for (int k = 1; k < 255; k++) {
                ip[3] = (byte) k;
                byte[] message = WelcomeSmsCreator.getMyMessageID().getBytes();
                InetAddress address = InetAddress.getByAddress(ip);
                DatagramPacket packet = new DatagramPacket(message, message.length, address, HsCommons.LOCATORPORT);
                DatagramSocket dsocket = new DatagramSocket();
                dsocket.send(packet);
                
                byte[] response = new byte[HsCommons.BUFFER];
                DatagramPacket back = new DatagramPacket(response, response.length);
                dsocket.receive(back);
                byte[] received = back.getData();
                String server = new String(received);
                server = server.trim();
                String aux[] = server.split(";");
                Engine.getInstance().setServer(aux[1]);
                System.err.println(server.trim());
                
                dsocket.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
