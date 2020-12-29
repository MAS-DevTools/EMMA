/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.Auth;
import mas.emma.data.models.UserMaster;
import mas.emma.services.helpers.AES;
import mas.emma.webapi.services.interfaces.IUserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class UserMasterService extends BaseService {

    @Autowired
    private IUserMasterRepository repository;

    public ResponseEntity getById(String id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity findUserByEmail(String email) {
        return new ResponseEntity(repository.findUserByEmail(email), HttpStatus.OK);
    }

    public ResponseEntity isAuthenticated(String email, Auth auth) {
        if (email != null && auth.getAuth() != null && auth.getAuth() != null) {
            UserMaster user = repository.findUserByEmail(email);
            if (user != null) {
                try ( AES Client_AES = new AES(auth.getAuth_key())) {// Set key provided by client

                    try ( AES _AES = new AES(user.getAuth().getAuth_key())) {// Set keyt
                        String authentication = _AES.encrypt(Client_AES.decrypt(auth.getAuth()));
                        if (authentication.equals(user.getAuth().getAuth())) {
                            return new ResponseEntity(true, HttpStatus.OK);
                        }
                    } catch (Exception e) {
                        log(e.getMessage());
                        return new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                } catch (Exception e) {
                    log(e.getMessage());
                    return new ResponseEntity(false, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
        return new ResponseEntity(false, HttpStatus.EXPECTATION_FAILED);
    }

    public <S extends UserMaster> ResponseEntity create(S s) {
        if (s.isValid()) {
            s.getAuth().SetMasterUser(s);
            s.getUserDetails().setMasterUser(s);
            s.getRoles().forEach(role -> {
                role.setMasterUser(s);
            });
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends UserMaster> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                UserMaster master = repository.getOne(s.getId());
                master.setAuth(s.getAuth());
                master.setEmail(s.getEmail());
                master.setFirst_name(s.getFirst_name());
                master.setLast_name(s.getLast_name());
                master.setProfile_type(s.getProfile_type());
                master.setRoles(s.getRoles());
                master.setUserDetails(s.getUserDetails());

                return new ResponseEntity(repository.save(master), HttpStatus.OK);
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
        Iterable<UserMaster> details = repository.findAllById(itrbl);
        repository.deleteAll(details);
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
