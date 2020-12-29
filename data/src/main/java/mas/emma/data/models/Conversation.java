/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.CONVERSATIONTBL)
public class Conversation extends BaseService implements Serializable {

    public Conversation() {
    }

    public Conversation(Long id, String user_id, String send_to, Set<Message> messages, UserMaster userMaster) {
        this.id = id;
        this.user_id = user_id;
        this.send_to = send_to;
        this.sendMessages = messages;
        this.UserMaster = userMaster;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = DatabaseConstants.CONVERSATION_ID, length = 250, nullable = false, unique = false)
    private Long id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.CONVERSATION_USER_ID, length = 250, nullable = false, unique = false)
    private String user_id;

    @Getter
    @Setter
    @Column(name = DatabaseConstants.CONVERSATION_SEND_TO, length = 250, nullable = false, unique = false)
    private String send_to;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = DatabaseConstants.CONVERSATION_USER_ID, nullable = true, referencedColumnName = DatabaseConstants.USER_ID, insertable = false, updatable = false)
    private UserMaster UserMaster;
    @Getter
    @Setter
    @OneToMany(targetEntity = Message.class, fetch = FetchType.LAZY, mappedBy = DatabaseConstants.CONVERSATIONTBL, cascade = CascadeType.ALL)
    private Set<Message> sendMessages;
    @Getter
    @Setter
    @OneToMany(targetEntity = Message.class, fetch = FetchType.LAZY, mappedBy = DatabaseConstants.CONVERSATIONTBL + "2", cascade = CascadeType.ALL)
    private Set<Message> receivedMessages;

    @JsonIgnore
    public Conversation valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<Conversation> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, Conversation[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && user_id != null
                && send_to != null;
    }
}
