/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.controllers;

import mas.emma.data.models.Auth;
import mas.emma.data.models.UserMaster;
import mas.emma.data.statictypes.constants.ControllerConstants;
import mas.emma.webapi.services.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
@RequestMapping(ControllerConstants.USERPATH)
public class UserMasterController extends BaseController {

    @Autowired
    private UserMasterService service;

    //<editor-fold defaultstate="collapsed" desc="CREATE">
    @PostMapping(path = ControllerConstants.CREATE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity create(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) UserMaster newUser) {

        return runWithErrorHandeling(() -> {
            return service.create(newUser);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="READ">
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity getById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ID) String id) {
        return runWithErrorHandeling(() -> {
            return service.getById(id);
        }, headers);
    }

    @GetMapping(ControllerConstants.FIND_BY_EMAIL)
    public ResponseEntity findByEmail(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.EMAIL) String email) {
        return runWithErrorHandeling(() -> {
            return service.findUserByEmail(email);
        }, headers);
    }

    @PostMapping(ControllerConstants.IS_AUTHENTICATED)
    public <S extends Auth> ResponseEntity isAuthenticated(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.EMAIL) String email, @RequestBody(required = true) S s) {
        return runWithErrorHandeling(() -> {
            return service.isAuthenticated(email, s);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="UPDATE">
    @PutMapping(ControllerConstants.UPDATE)
    public <S extends UserMaster> ResponseEntity getAllUsers(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) S s) {
        return runWithErrorHandeling(() -> {
            return service.update(s);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DELETE">
    @DeleteMapping(ControllerConstants.DELETE_BY_IDS)
    public ResponseEntity<Object> deleteByIds(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) Iterable<String> itrbl) {
        return runWithErrorHandeling(() -> {
            return service.deleteByIds(itrbl);
        }, headers);
    }

    @DeleteMapping(ControllerConstants.DELETE_BY_ID)
    public ResponseEntity<Object> deleteById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ID) String id) {
        return runWithErrorHandeling(() -> {
            return service.deleteById(id);
        }, headers);
    }

    //</editor-fold>
}
