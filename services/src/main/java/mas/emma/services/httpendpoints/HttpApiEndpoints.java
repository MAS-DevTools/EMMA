/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.httpendpoints;

/**
 *
 * @author nlmsc
 */
public abstract class HttpApiEndpoints {

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
}
