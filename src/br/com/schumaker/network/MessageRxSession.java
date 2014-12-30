package br.com.schumaker.network;

import br.com.schumaker.core.Engine;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author hudson schumaker
 */
public class MessageRxSession extends Thread {

    private Socket socket = null;

    public MessageRxSession(Socket socket) {
        this.socket = socket;
    }
    
    private void tramsmit(String line){
       Engine.getInstance().setMessage(line);
    }

    @Override
    public void run() {
        try {
            InputStream inps = socket.getInputStream();
            OutputStream outs = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(inps);
            PrintWriter out = new PrintWriter(outs, true);
            out.println("> Connected to server...");
            boolean done = false;
//            while (!done) {
                String line = dis.readUTF();
                System.out.println(line);
                tramsmit(line); 
//            }
            inps.close();
            outs.close();
            dis.close();
        } catch (Exception e) {
            System.err.println("MessageRxSeesion:Run:\n"+e);
        }
    }
}
