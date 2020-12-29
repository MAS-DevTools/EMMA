/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.GroupMessage;
import mas.emma.webapi.services.interfaces.IGroupMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class GroupMessageService extends BaseService {

    @Autowired
    private IGroupMessageRepository repository;

    public ResponseEntity getById(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends GroupMessage> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends GroupMessage> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                GroupMessage groupMessage = (GroupMessage) repository.getOne(s.getId());
                groupMessage.setBody(s.getBody());
                groupMessage.setRead_on(s.getRead_on());
                groupMessage.setSend_date(s.getSend_date());
                groupMessage.setSender(s.getSender());
                groupMessage.SetGroupMember(s.getGroupMember());
                groupMessage.setSend_to(s.getSend_to());

                return new ResponseEntity(repository.save(groupMessage), HttpStatus.OK);
            } catch (Exception e) {
                log(e.getMessage());
                return new ResponseEntity(s, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public ResponseEntity deleteById(Long id) {
        repository.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    public ResponseEntity deleteByIds(Iterable<Long> itrbl) {
        Iterable<GroupMessage> messages = repository.findAllById(itrbl);
        repository.deleteAll(messages);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
