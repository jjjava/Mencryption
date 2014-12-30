package br.com.schumaker.io;

import br.com.schumaker.network.HsCommons;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author hudson schumaker
 */
public class WelcomeSmsCreator {

    //idService;userName;ipAddress
    public static String getMyMessageID() {
        InetAddress ip;
        String message = "?;?;?";
        try {
            ip = InetAddress.getLocalHost();
            message = HsCommons.IDSERVICE + ";" + System.getProperty("user.name") + ";" + ip.getHostAddress();
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        return message;
    }

    public static String me() {
        return System.getProperty("user.name");
    }
}
