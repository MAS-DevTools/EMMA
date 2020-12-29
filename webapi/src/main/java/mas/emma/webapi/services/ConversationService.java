/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.Conversation;
import mas.emma.webapi.services.interfaces.IConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author nlmsc
 */
@Service
public class ConversationService extends BaseService {

    @Autowired
    private IConversationRepository repository;

    public ResponseEntity getById(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends Conversation> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends Conversation> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                Conversation conversation = (Conversation) repository.getOne(s.getId());
                conversation.setReceivedMessages(s.getReceivedMessages());
                conversation.setSendMessages(s.getSendMessages());
                conversation.setSend_to(s.getSend_to());
                conversation.setUser_id(s.getUser_id());

                return new ResponseEntity(repository.save(conversation), HttpStatus.OK);
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
        Iterable<Conversation> messages = repository.findAllById(itrbl);
        repository.deleteAll(messages);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
