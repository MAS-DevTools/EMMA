/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.interfaces;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author nlmsc
 * @param <T>
 */
public interface ActionResponse {

    public ResponseEntity invokeRespone() throws Exception;
}
