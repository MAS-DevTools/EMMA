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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import static mas.emma.data.models.BaseService.toObject;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.ORGANISATIONTBL)
public class Organisation implements Serializable {

    public Organisation() {
    }

    public Organisation(String org_id, String organisation_name, String guid, String email, Set<UserMaster> UserMaster, Set<TopicGroup> groups) {
        this.org_id = org_id;
        this.organisation_name = organisation_name;
        this.guid = guid;
        this.email = email;
        this.UserMaster = UserMaster;
        this.Group = groups;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ORGANISATION_ID, length = 250, nullable = false, unique = true)
    private String org_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ORGANISATION_NAME, length = 250, nullable = false, unique = true)
    private String organisation_name;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ORGANISATION_GUID, length = 250, nullable = false, unique = true)
    private String guid;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ORGANISATION_EMAIL, length = 250, nullable = false, unique = true)
    private String email;
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = DatabaseConstants.ORGANISATIONTBL)
    private Set<UserMaster> UserMaster;

    @Getter
    @Setter
    @OneToMany(mappedBy = DatabaseConstants.ORGANISATIONTBL, cascade = CascadeType.ALL)
    private Set<TopicGroup> Group;

    @JsonIgnore
    public Organisation valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<Organisation> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, Organisation[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return org_id != null
                && organisation_name != null
                && guid != null
                && email != null;
    }

    @JsonIgnore
    public void setMasterUser(Set<UserMaster> usermaster) {
        this.UserMaster = usermaster;
    }
}
