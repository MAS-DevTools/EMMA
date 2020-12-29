/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.GroupMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mas.emma.webapi.services.interfaces.IGroupMemberRepository;

/**
 *
 * @author nlmsc
 */
@Service
public class GroupMemberService extends BaseService {

    @Autowired
    private IGroupMemberRepository repository;

    public ResponseEntity getById(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends GroupMember> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);

        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends GroupMember> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                GroupMember member = (GroupMember) repository.getOne(s.getId());
                member.setGroup(s.getTopicGroup());
                member.setGroupMessages(s.getGroupMessages());
                member.setGroup_id(s.getGroup_id());
                member.setJoined_on(s.getJoined_on());
                member.setLast_seen(s.getLast_seen());
                member.setMasterUser(s.getUserMaster());
                member.setUser_id(s.getUser_id());
                member.set_administrator(s.is_administrator());

                return new ResponseEntity(repository.save(member), HttpStatus.OK);
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
        Iterable<GroupMember> messages = repository.findAllById(itrbl);
        repository.deleteAll(messages);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
