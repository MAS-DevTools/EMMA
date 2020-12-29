/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.UserDetail;
import mas.emma.webapi.services.interfaces.IUserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class UserDetailService extends BaseService {

    @Autowired
    private IUserDetailRepository repository;

    public ResponseEntity getById(String id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends UserDetail> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends UserDetail> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                UserDetail detail = repository.getOne(s.getId());
                detail.setStreet_name(s.getStreet_name());
                detail.setBirthday(s.getBirthday());
                detail.setCity(s.getCity());
                detail.setHouse_number(s.getPhonenumber());
                detail.setPhonenumber(s.getPhonenumber());
                detail.setZip(s.getZip());
                detail.setLast_seen(s.getLast_seen());
                detail.setProfile_status(s.getProfile_status());
                detail.setMasterUser(s.getUserMaster());

                return new ResponseEntity(repository.save(detail), HttpStatus.OK);
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
        Iterable<UserDetail> details = repository.findAllById(itrbl);
        repository.deleteAll(details);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
