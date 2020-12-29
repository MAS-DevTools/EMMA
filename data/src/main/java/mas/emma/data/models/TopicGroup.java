/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import java.sql.Date;
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
@Table(name = DatabaseConstants.TOPICGROUPTBL)
public class TopicGroup extends BaseService implements Serializable {

    public TopicGroup() {
    }

    public TopicGroup(Long id, String group_id, String created_by, Date created_on, String organisation_id, String group_name, boolean is_external_public, boolean is_public, Organisation organisation, Set<GroupMember> groupMembers) {
        this.id = id;
        this.group_id = group_id;
        this.created_by = created_by;
        this.created_on = created_on;
        this.organisation_id = organisation_id;
        this.group_name = group_name;
        this.is_external_accessible = is_external_public;
        this.is_public = is_public;
        this.Organisation = organisation;
        this.GroupMember = groupMembers;
    }

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.GROUP_ID, length = 250, nullable = false, unique = false)
    private Long id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_GROUP_ID, length = 250, nullable = false, unique = false)
    private String group_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_CREATED_BY, length = 250, nullable = false, unique = false)
    private String created_by;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_CREATED_ON, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date created_on;
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_ORGANISATION_ID, length = 250, nullable = false, unique = false)
    private String organisation_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_GROUP_NAME, length = 250, nullable = false, unique = false)
    private String group_name;

    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_EXTERNAL_ACCESSIBLE, nullable = false, unique = false)
    private boolean is_external_accessible;

    @Getter
    @Setter
    @Column(name = DatabaseConstants.GROUP_PUBLIC, nullable = false, unique = false)
    private boolean is_public;
    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = DatabaseConstants.GROUP_ORGANISATION_ID, referencedColumnName = DatabaseConstants.ORGANISATION_ID, nullable = true, insertable = false, updatable = false)
    private Organisation Organisation;

    @Getter
    @Setter
    @OneToMany(targetEntity = GroupMember.class, mappedBy = DatabaseConstants.TOPICGROUPTBL, cascade = CascadeType.ALL)
    private Set<GroupMember> GroupMember;

    @JsonIgnore
    public TopicGroup valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && group_id != null
                && organisation_id != null
                && group_name != null
                && created_by != null
                && created_on != null;
    }

    @JsonIgnore
    public void setOrganisation(Organisation organisation) {
        this.Organisation = organisation;
    }

}
