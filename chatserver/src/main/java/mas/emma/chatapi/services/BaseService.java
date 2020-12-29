/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.chatapi.services;

import mas.emma.services.httpendpoints.HttpAuth;
import mas.emma.services.httpendpoints.HttpUserMaster;
import mas.emma.services.interfaces.Action;

/**
 *
 * @author nlmsc
 */
public class BaseService extends Thread {

    private HttpAuth authService;
    private HttpUserMaster userMasterService;

    protected HttpUserMaster userMasterServiceInstance() {
        if (userMasterService == null) {
            userMasterService = new HttpUserMaster();
        }
        return userMasterService;
    }

    protected HttpAuth authServiceInstance() {
        if (authService == null) {
            authService = new HttpAuth();
        }
        return authService;
    }

    protected void RunWithErrorHandeling(Action action) {
        try {
            action.invoke();
        } catch (InterruptedException exception) {
            System.err.print(exception);
        } catch (Exception exception) {
            System.err.print(exception);
        }
    }

    protected void WriteLine(String message) {
        System.out.println(message);
    }
}
