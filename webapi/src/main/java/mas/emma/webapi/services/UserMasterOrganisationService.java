/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.UserMasterOrganisation;
import mas.emma.webapi.services.interfaces.IUserMasterOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class UserMasterOrganisationService extends BaseService {

    @Autowired
    private IUserMasterOrganisationRepository repository;

    public <S extends UserMasterOrganisation> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity findUserIdsByOrganisationId(String org_id) {
        return new ResponseEntity(repository.findUserIdsByOrganisationId(org_id), HttpStatus.OK);
    }

    public ResponseEntity findOrganisationIdsByUserId(String uid) {
        return new ResponseEntity(repository.findOrganisationIdsByUserId(uid), HttpStatus.OK);
    }

    public ResponseEntity findRecord(String uid, String org_id) {
        return new ResponseEntity(repository.findRecord(uid, org_id), HttpStatus.OK);
    }

    public ResponseEntity deleteById(Long id) {
        repository.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    public ResponseEntity deleteByIds(Iterable<Long> itrbl) {
        Iterable<UserMasterOrganisation> details = repository.findAllById(itrbl);
        repository.deleteAll(details);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
