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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import mas.emma.data.statictypes.enums.ProfileType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.USERSTBL)
public class UserMaster extends BaseService implements Serializable {

    public UserMaster() {
    }

    public UserMaster(String id, String first_name, String last_name, String email, String organisation_id, ProfileType profile_type, UserDetail UserDetails, Auth Auth, Set<EmmaRole> roles, Set<GroupMember> topics) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.profile_type = profile_type;
        this.UserDetails = UserDetails;
        this.Auth = Auth;
        this.roles = roles;
        this.topics = topics;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USER_ID, length = 250, nullable = false, unique = true)
    private String id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USER_FIRST_NAME, length = 250, nullable = false, unique = false)
    private String first_name;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USER_LAST_NAME, length = 250, nullable = false, unique = false)
    private String last_name;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USER_EMAIL, length = 250, nullable = false, unique = false)
    private String email;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = DatabaseConstants.USER_PROFILE_TYPE, nullable = false)
    private ProfileType profile_type;
    @Getter
    @Setter
    @OneToOne(mappedBy = DatabaseConstants.USERSTBL, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private UserDetail UserDetails;
    @Getter
    @Setter
    @OneToOne(mappedBy = DatabaseConstants.USERSTBL, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Auth Auth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "User_Master_Organisation", joinColumns = {
        @JoinColumn(name = "uid")},
            inverseJoinColumns = {
                @JoinColumn(name = "org_id")})
    private Set<Organisation> Organisation;
    @Getter
    @Setter
    @OneToMany(mappedBy = DatabaseConstants.USERSTBL, cascade = CascadeType.ALL)
    private Set<EmmaRole> roles;
    @Getter
    @Setter
    @OneToMany(targetEntity = GroupMember.class, mappedBy = DatabaseConstants.USERSTBL, cascade = CascadeType.ALL)
    private Set<GroupMember> topics;
    @Getter
    @Setter
    @OneToMany(targetEntity = Conversation.class, mappedBy = DatabaseConstants.USERSTBL, cascade = CascadeType.ALL)
    private Set<Conversation> conversations;

    @JsonIgnore
    public UserMaster valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<UserMaster> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, UserMaster[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && first_name != null
                && last_name != null
                && email != null
                && profile_type != null;
    }
}
