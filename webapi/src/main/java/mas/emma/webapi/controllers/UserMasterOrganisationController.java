/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.controllers;

import mas.emma.data.models.UserMasterOrganisation;
import mas.emma.data.statictypes.constants.ControllerConstants;
import static mas.emma.webapi.controllers.BaseController.runWithErrorHandeling;
import mas.emma.webapi.services.UserMasterOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author nlmsc
 */
@RestController
@RequestMapping(ControllerConstants.USERMASTERORGANISATIONPATNH)
public class UserMasterOrganisationController extends BaseController {

    @Autowired
    private UserMasterOrganisationService service;

    //<editor-fold defaultstate="collapsed" desc="CREATE">
    @PostMapping(path = ControllerConstants.CREATE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity create(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) UserMasterOrganisation newEntry) {

        return runWithErrorHandeling(() -> {
            return service.create(newEntry);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="READ">
    @GetMapping(ControllerConstants.GET_BY_ID)
    public ResponseEntity findOrganisationIdsByUserId(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ID) String id) {
        return runWithErrorHandeling(() -> {
            return service.findOrganisationIdsByUserId(id);
        }, headers);
    }

    @GetMapping(ControllerConstants.GET_BY_ORGANISATION_ID)
    public ResponseEntity findUserIdsByOrganisationId(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ORGANISATION_ID) String organisation_id) {
        return runWithErrorHandeling(() -> {
            return service.findUserIdsByOrganisationId(organisation_id);
        }, headers);
    }

    @GetMapping(ControllerConstants.GET_BY_ID_AND_ORGANISATION_ID)
    public ResponseEntity findRecord(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ID) String id, @PathVariable(ControllerConstants.ORGANISATION_ID) String organisation_id) {
        return runWithErrorHandeling(() -> {
            return service.findRecord(id, organisation_id);
        }, headers);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DELETE">
    @DeleteMapping(ControllerConstants.DELETE_BY_IDS)
    public ResponseEntity deleteByIds(@RequestHeader(required = true) MultiValueMap<String, String> headers, @RequestBody(required = true) Iterable<Long> itrbl) {
        return runWithErrorHandeling(() -> {
            return service.deleteByIds(itrbl);
        }, headers);
    }

    @DeleteMapping(ControllerConstants.DELETE_BY_ID)
    public ResponseEntity deleteById(@RequestHeader(required = true) MultiValueMap<String, String> headers, @PathVariable(ControllerConstants.ID) Long row_id) {
        return runWithErrorHandeling(() -> {
            return service.deleteById(row_id);
        }, headers);
    }

    //</editor-fold>
}
