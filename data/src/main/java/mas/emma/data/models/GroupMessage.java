/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import static mas.emma.data.models.BaseService.toObject;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.GROUPMESSAGETBL)
public class GroupMessage extends BaseService implements Serializable {

    public GroupMessage() {
    }

    public GroupMessage(Long id, String sender, String send_to, boolean message_read, Date send_date, Date read_on, String body, GroupMember GroupMember) {
        this.id = id;
        this.sender = sender;
        this.send_to = send_to;
        this.send_date = send_date;
        this.read_on = read_on;
        this.body = body;
        this.GroupMember = GroupMember;
    }

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.GROUP_MESSAGE_ID, length = 250, nullable = false, unique = true)
    private Long id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_MESSAGE_SENDER, length = 250, nullable = false, unique = false)
    private String sender;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_MESSAGE_SEND_TO, length = 250, nullable = false, unique = false)
    private String send_to;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_MESSAGE_SEND_DATE, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date send_date;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_MESSAGE_READ_ON, length = 250, nullable = true, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date read_on;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_MESSAGE_BODY, length = 250, nullable = false, unique = false)
    private String body;
    @Getter
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumns(
            {
                @JoinColumn(name = DatabaseConstants.GROUP_MESSAGE_SENDER, referencedColumnName = DatabaseConstants.GROUPMEMBER_USER_ID, nullable = true, insertable = false, updatable = false)//, @JoinColumn(name = DatabaseConstants.MESSAGE_SEND_TO, referencedColumnName = DatabaseConstants.GROUPMEMBER_GROUP_ID, nullable = true, insertable = false, updatable = false)
            }
    )
    private GroupMember GroupMember;

    @JsonIgnore
    public GroupMessage valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<GroupMessage> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, GroupMessage[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && sender != null
                && send_to != null
                && read_on != null
                && send_date != null
                && body != null;
    }

    @JsonIgnore
    public void SetGroupMember(GroupMember GroupMember) {
        this.GroupMember = GroupMember;
    }
}
