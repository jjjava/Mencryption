package br.com.schumaker.core;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 *
 * @author hudson schumaker
 */
public class Encryption {

    public static String encrypt(String sms) {
        String authEncBytes = Base64.encode(sms.getBytes());
        return authEncBytes;
    }

    public static String decrypt(String esms) {
        byte[] authEncBytes = Base64.decode(esms);
        String sms = new String(authEncBytes);
        return sms;
    }
}
