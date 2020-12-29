/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.EmmaRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mas.emma.webapi.services.interfaces.IEmmaRoleRepository;

/**
 *
 * @author nlmsc
 */
@Service
public class EmmaRoleService extends BaseService {

    @Autowired
    private IEmmaRoleRepository repository;

    public ResponseEntity getById(long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends EmmaRole> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends EmmaRole> ResponseEntity update(Iterable<S> itrbl) {
        try {
            for (EmmaRole role : itrbl) {
                if (role.isValid()) {
                    EmmaRole emmaRole = repository.getOne(role.getRole_id());
                    emmaRole.setMasterUser(role.getUserMaster());
                    emmaRole.setOrganisation_id(role.getOrganisation_id());
                    emmaRole.setRow_id(role.getRow_id());
                    emmaRole.setRole_name(role.getRole_name());
                    emmaRole.setUser_id(role.getUser_id());

                    repository.save(emmaRole);
                } else {
                    return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
                }
            }
            return new ResponseEntity(itrbl, HttpStatus.OK);
        } catch (Exception e) {
            log(e.getMessage());
            return new ResponseEntity(null, HttpStatus.EXPECTATION_FAILED);
        }

    }

    public ResponseEntity deleteById(Long id) {
        repository.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    public ResponseEntity deleteByIds(Iterable<Long> itrbl) {
        Iterable<EmmaRole> auth = repository.findAllById(itrbl);
        repository.deleteAll(auth);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
