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
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import static mas.emma.data.models.BaseService.toObject;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.GROUPMEMBERTBL)
public class GroupMember extends BaseService implements Serializable {

    public GroupMember() {
    }

    public GroupMember(Long id, String group_id, String organisation_id, String user_id, String group_name, UserMaster usermaster, Organisation organisation, TopicGroup group) {
        this.id = id;
        this.group_id = group_id;
        this.user_id = user_id;
        this.UserMaster = usermaster;
        this.TopicGroup = group;
    }

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.GROUPMEMBER_ID, length = 250, nullable = false, unique = false)
    private Long id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUPMEMBER_GROUP_ID, length = 250, nullable = false, unique = false)
    private String group_id;
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUPMEMBER_USER_ID, length = 250, nullable = false, unique = false)
    private String user_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUPMEMBER_IS_ADMINISTRATOR, length = 250, nullable = false, unique = false)
    private boolean is_administrator;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUPMEMBER_JOINED_ON, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date joined_on;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUPMEMBER_LAST_SEEN, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date last_seen;
    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = DatabaseConstants.GROUPMEMBER_USER_ID, nullable = true, referencedColumnName = DatabaseConstants.USER_ID, insertable = false, updatable = false)
    private UserMaster UserMaster;
    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = DatabaseConstants.GROUPMEMBER_GROUP_ID, nullable = true, referencedColumnName = DatabaseConstants.GROUP_GROUP_ID, insertable = false, updatable = false)
    private TopicGroup TopicGroup;

    @Getter
    @Setter
    @OneToMany(targetEntity = GroupMessage.class, mappedBy = DatabaseConstants.GROUPMEMBERTBL, cascade = CascadeType.ALL)
    private Set<GroupMessage> GroupMessages;

    @JsonIgnore
    public GroupMember valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<GroupMember> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, GroupMember[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && group_id != null
                && user_id != null;
    }

    @JsonIgnore
    public void setMasterUser(UserMaster usermaster) {
        this.UserMaster = usermaster;
    }

    @JsonIgnore
    public void setGroup(TopicGroup group) {
        this.TopicGroup = group;
    }
}
