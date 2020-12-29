/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.interfaces;

import mas.emma.services.BaseService;

/**
 *
 * @author nlmsc
 */
public abstract class TaskScheduler extends BaseService {

    private static volatile Thread thread;

    protected abstract void executeTask();

    protected void startScheduler() {
        thread = scheduler();
        thread.start();
    }

    protected Thread scheduler() {
        return new Thread(() -> {
            while (true) {
                runWithErrorHandeling(() -> {
                    executeTask();
                    thread.sleep(10L * 1000L);
                });
            }
        });
    }
}
