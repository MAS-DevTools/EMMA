/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import mas.emma.data.models.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nlmsc
 */
@Repository
public interface IGroupMemberRepository extends JpaRepository<GroupMember, Long> {
    // List<GroupChat> findByGrouChatKey(GroupChatKey key);
}
