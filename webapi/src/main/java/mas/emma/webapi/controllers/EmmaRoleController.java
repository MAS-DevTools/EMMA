/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.controllers;

import mas.emma.data.models.EmmaRole;
import mas.emma.data.models.UserMaster;
import mas.emma.data.statictypes.constants.ControllerConstants;
import static mas.emma.webapi.controllers.BaseController.runWithErrorHandeling;
import mas.emma.webapi.services.EmmaRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nlmsc
 */
@RestController
@RequestMapping(ControllerConstants.ROLEPATH)
public class EmmaRoleController {

    @Autowired
    private EmmaRoleService service;

    //<editor-fold defaultstate="collapsed" desc="CREATE">
    @PostMapping(ControllerConstants.CREATE)
    public <S extends EmmaRole> ResponseEntity<Object> create(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) S s) {
        return runWithErrorHandeling(() -> {
            return service.create(s);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="READ">
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity<UserMaster> getById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(name = ControllerConstants.ID, required = true) long id) {
        return runWithErrorHandeling(() -> {
            return service.getById(id);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="UPDATE">
    @PutMapping(ControllerConstants.UPDATE)
    public <S extends EmmaRole> ResponseEntity update(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) Iterable<S> itrbl) {
        return runWithErrorHandeling(() -> {
            return service.update(itrbl);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DELETE">
    @DeleteMapping(ControllerConstants.DELETE_BY_ID)
    public ResponseEntity deleteById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(name = ControllerConstants.ID, required = true) long id) {
        return runWithErrorHandeling(() -> {
            return service.deleteById(id);
        }, headers);
    }

    @DeleteMapping(ControllerConstants.DELETE_BY_IDS)
    public ResponseEntity deleteById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) Iterable<Long> itrbl) {
        return runWithErrorHandeling(() -> {
            return service.deleteByIds(itrbl);
        }, headers);
    }

    //</editor-fold>
}
