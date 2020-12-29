/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import mas.emma.data.models.UserMaster;
import mas.emma.data.statictypes.enums.ApplicationType;

/**
 *
 * @author nlmsc
 */
public class ExceptionHelper {

    public static void logAndShowException(Exception exception, UserMaster user, ApplicationType app) {
        logException(exception.getMessage(), user, app);

        switch (app) {
            case WEBAPI:
                // code block => show
                break;
            case CHATSERVER:
                // code block => show
                break;
            default:
            // code block
        }
    }

    public static void logException(String message, UserMaster user, ApplicationType app) {
        System.out.println(message);
    }
}
