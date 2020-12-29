/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import mas.emma.data.statictypes.enums.EmmaRoleType;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Immutable;

/**
 *
 * @author nlmsc
 */
@Entity
@Immutable
@Table(name = DatabaseConstants.ROLETBL,
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {DatabaseConstants.ROLE_ROLE_ID, DatabaseConstants.ROLE_USER_ID})
        })
public class EmmaRole extends BaseService implements Serializable {

    public EmmaRole() {
    }

    public EmmaRole(Long row_id, Long role_id, String user_id, String organisation_id, EmmaRoleType role_name, UserMaster UserMaster) {
        this.row_id = row_id;
        this.role_id = role_id;
        this.user_id = user_id;
        this.organisation_id = organisation_id;
        this.role_name = role_name;
        this.UserMaster = UserMaster;
    }

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = DatabaseConstants.ROLE_ID, length = 250, nullable = false, unique = true)
    private Long row_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ROLE_ROLE_ID, length = 250, nullable = false, unique = false)
    private Long role_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ROLE_USER_ID, length = 250, nullable = false, unique = false)
    private String user_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ROLE_ORGANISATION_ID, length = 250, nullable = false, unique = false)
    private String organisation_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.ROLE_NAME, length = 250, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private EmmaRoleType role_name;
    @Getter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = DatabaseConstants.ROLE_USER_ID, nullable = false, referencedColumnName = DatabaseConstants.USER_ID, insertable = false, updatable = false)
    private UserMaster UserMaster;

    @JsonIgnore
    public EmmaRole valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<EmmaRole> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, EmmaRole[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return row_id != null
                && organisation_id != null
                && role_id != null
                && user_id != null
                && role_name != null;
    }

    @JsonIgnore
    public void setMasterUser(UserMaster UserMaster) {
        this.UserMaster = UserMaster;
    }
}
