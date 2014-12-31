package br.com.schumaker.util;

import java.util.Calendar;

/**
 *
 * @author hudson schumaker
 */
public class HsDateTime {

    public static synchronized String getHHMMSS(){
        Calendar dateTime = Calendar.getInstance();
        return String.format("%tT", dateTime);
    }
}
