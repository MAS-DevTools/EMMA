/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author nlmsc
 */
public class TimeHelper {

    public static String currentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static Date getDateWithOffset(int minute) {
        return new Date(new Date().getTime() + (1000 * 60 * minute));
    }
}
