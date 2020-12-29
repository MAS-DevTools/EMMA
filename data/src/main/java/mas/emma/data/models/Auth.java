/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name = DatabaseConstants.AUTHTBL)
public class Auth implements Serializable {

    public Auth() {
    }

    public Auth(String id, String auth, String auth_key, UserMaster userMaster) {
        this.id = id;
        this.auth = auth;
        this.auth_key = auth_key;
        this.UserMaster = userMaster;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.AUTH_ID, length = 250, nullable = false, unique = true)
    private String id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.AUTH_AUTH, length = 250, nullable = false, unique = false)
    private String auth;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.AUTH_KEY, length = 250, nullable = false, unique = false)
    private String auth_key;
    @OneToOne
    @MapsId
    @JoinColumn(name = DatabaseConstants.USER_ID)
    private UserMaster UserMaster;

    @JsonIgnore
    public Auth valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && auth != null
                && auth_key != null;
    }

    @JsonIgnore
    public void SetMasterUser(UserMaster userMaster) {
        this.UserMaster = userMaster;
    }
}
