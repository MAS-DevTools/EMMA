/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.AppAuth;
import mas.emma.webapi.services.interfaces.IAppAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class AppAuthService extends BaseService {

    @Autowired
    private IAppAuthRepository repository;

    public ResponseEntity getByName(String application_name) {
        return new ResponseEntity(repository.findAuthByName(application_name), HttpStatus.OK);
    }

    public ResponseEntity getById(String id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity getAll() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }

    public <S extends AppAuth> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends AppAuth> ResponseEntity save(S s) {
        if (s.isValid()) {
            try {
                AppAuth appAuth = (AppAuth) repository.getOne(s.getId());
                appAuth.setEmail(s.getEmail());
                appAuth.setGuid(s.getGuid());
                appAuth.setApplication_name(s.getApplication_name());
                return new ResponseEntity(repository.save(appAuth), HttpStatus.OK);
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
        Iterable<AppAuth> appAuths = repository.findAllById(itrbl);
        repository.deleteAll(appAuths);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
