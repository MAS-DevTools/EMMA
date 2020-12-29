/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.Organisation;
import mas.emma.webapi.services.interfaces.IOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class OrganisationService extends BaseService {

    @Autowired
    private IOrganisationRepository repository;

    public ResponseEntity getById(String id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity getByName(String organisation_name) {
        return new ResponseEntity(repository.findOrganisationByName(organisation_name), HttpStatus.OK);
    }

    public ResponseEntity getByEmail(String email) {
        return new ResponseEntity(repository.findOrganisationByEmail(email), HttpStatus.OK);
    }

    public <S extends Organisation> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends Organisation> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                Organisation organisation = (Organisation) repository.getOne(s.getOrg_id());
                organisation.setEmail(s.getEmail());
                organisation.setGuid(s.getGuid());
                organisation.setOrganisation_name(s.getOrganisation_name());
                organisation.setGroup(s.getGroup());
                organisation.setMasterUser(s.getUserMaster());

                return new ResponseEntity(repository.save(organisation), HttpStatus.OK);
            } catch (Exception e) {
                log(e.getMessage());
                return new ResponseEntity(s, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity deleteById(String id) {
        repository.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    public ResponseEntity deleteByIds(Iterable<String> itrbl) {
        Iterable<Organisation> appAuths = repository.findAllById(itrbl);
        repository.deleteAll(appAuths);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
