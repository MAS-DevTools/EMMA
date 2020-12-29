/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.interfaces;

import java.util.List;

/**
 *
 * @author nlmsc
 */
public interface ActionWithReturn<T> {

    public List<T> invokeT() throws Exception;

}
