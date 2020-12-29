/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import java.util.UUID;
import mas.emma.data.models.Auth;
import mas.emma.services.helpers.AES;
import mas.emma.webapi.services.interfaces.IAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class AuthService extends BaseService {

    @Autowired
    private IAuthRepository repository;

    public ResponseEntity getById(String id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends Auth> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends Auth> ResponseEntity update(S s) {
        if (s.isValid()) {
            try ( AES Client_AES = new AES(s.getAuth_key())) {// Set key provided by client
                Auth auth = repository.getOne(s.getId()); //find the correct Auth to modify
                String key = UUID.randomUUID().toString();
                try ( AES Aes = new AES(key)) { // set new GUIDKey
                    String new_Auth_token = Aes.encrypt(Client_AES.decrypt(s.getAuth()));
                    auth.setAuth(new_Auth_token);
                    auth.setAuth_key(key);
                    return new ResponseEntity(repository.save(auth), HttpStatus.OK);
                }
            } catch (Exception e) {
                log(e.getMessage());
                return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
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
        Iterable<Auth> auth = repository.findAllById(itrbl);
        repository.deleteAll(auth);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
