/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import mas.emma.data.statictypes.constants.ApplicationConstants;

/**
 *
 * @author nlmsc
 */
public class BrowserHelper {

    public static void open(String httpPath) {
        try {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(httpPath));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                 Runtime.getRuntime().exec(ApplicationConstants.RUNDLL32 + "http://localhost:8080");
            }
        } catch (IOException ex) {

        }

    }
}
