package br.com.schumaker.core;

import br.com.schumaker.gfx.FrMain;
import br.com.schumaker.network.HsCommons;
import br.com.schumaker.network.KeepMe;
import br.com.schumaker.network.MessageRX;
import br.com.schumaker.network.ServiceLocator;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author hudson schumaker Client
 */
public class Engine implements Runnable {

    private static final Engine INSTANCE = new Engine();
    private String server;
    private FrMain frame;

    private Engine() {
        frame = new FrMain();
        frame.setVisible(true);
    }

    public static Engine getInstance() {
        return INSTANCE;
    }

    public void addUser(String s) {
        frame.addToList(s);
    }

    @Override
    public void run() {
        new ServiceLocator().start();
        new KeepMe().start();
        new MessageRX();
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    public void sendMessage(String esms) {
        try {
            Socket conexao = new Socket(getServer(), HsCommons.TXPORT);
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(esms);
            conexao.close();
        } catch (Exception exc) {
            System.out.println(exc.toString());
        }
    }

    public void scanServer() {
        new ServiceLocator().start();
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        frame.setTitle("Server: " + server);
        this.server = server;
    }

    public void setMessage(String esms) {
        frame.setMessage(Encryption.decrypt(esms));
    }
}
