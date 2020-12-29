/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.Message;
import mas.emma.webapi.services.interfaces.IChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class ChatMessageService extends BaseService {

    @Autowired
    private IChatMessageRepository repository;

    public ResponseEntity getById(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends Message> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return null;
        }
    }

    public <S extends Message> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                Message message = (Message) repository.getOne(s.getId());
                message.setRead_on(s.getRead_on());
                message.setBody(s.getBody());
                message.setSend_date(s.getSend_date());
                message.setSend_to(s.getSend_to());
                message.setSender(s.getSender());
                message.SetConversation(s.getConversation());
                message.SetConversation2(s.getConversation2());

                return new ResponseEntity(repository.save(message), HttpStatus.OK);
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
        Iterable<Message> messages = repository.findAllById(itrbl);
        repository.deleteAll(messages);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
