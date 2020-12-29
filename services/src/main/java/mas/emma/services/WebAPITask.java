/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services;

import mas.emma.services.interfaces.TaskScheduler;

/**
 *
 * @author nlmsc
 */
public class WebAPITask extends TaskScheduler {

    public WebAPITask() {
        super.startScheduler();
    }

    @Override
    public void executeTask() {
        Application.cacheInstance().removeOutDatedUSer();
    }

}
