/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services;

import mas.emma.data.models.TopicGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import mas.emma.webapi.services.interfaces.ITopicGroupRepository;

/**
 *
 * @author nlmsc
 */
@Service
public class TopicGroupService extends BaseService {

    @Autowired
    private ITopicGroupRepository repository;

    public ResponseEntity getById(Long id) {
        return new ResponseEntity(repository.findById(id), HttpStatus.OK);
    }

    public <S extends TopicGroup> ResponseEntity create(S s) {
        if (s.isValid()) {
            repository.save(s);
            return new ResponseEntity(s, HttpStatus.OK);
        } else {
            return new ResponseEntity(s, HttpStatus.EXPECTATION_FAILED);
        }
    }

    public <S extends TopicGroup> ResponseEntity update(S s) {
        if (s.isValid()) {
            try {
                TopicGroup group = (TopicGroup) repository.getOne(s.getId());
                group.setCreated_by(s.getCreated_by());
                group.setCreated_on(s.getCreated_on());
                group.setGroupMember(s.getGroupMember());
                group.setGroup_id(s.getGroup_id());
                group.setGroup_name(s.getGroup_name());
                group.setOrganisation_id(s.getOrganisation_id());
                group.set_external_accessible(s.is_external_accessible());
                group.set_public(s.is_public());
                group.setOrganisation(s.getOrganisation());

                return new ResponseEntity(repository.save(group), HttpStatus.OK);
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
        Iterable<TopicGroup> groups = repository.findAllById(itrbl);
        repository.deleteAll(groups);
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
