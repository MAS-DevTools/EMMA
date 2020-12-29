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
@Table(name = DatabaseConstants.APPAUTHTBL)
public class AppAuth extends BaseService implements Serializable {

    public AppAuth() {
    }

    public AppAuth(String id, String application_name, String guid, String email) {
        this.id = id;
        this.application_name = application_name;
        this.guid = guid;
        this.email = email;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.APP_AUTH_ID, length = 250, nullable = false, unique = true)
    private String id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.APP_AUTH_NAME, length = 250, nullable = false, unique = true)
    private String application_name;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.APP_AUTH_GUID, length = 250, nullable = false, unique = true)
    private String guid;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.APP_AUTH_EMAIL, length = 250, nullable = false, unique = true)
    private String email;

    @JsonIgnore
    public AppAuth valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && application_name != null
                && guid != null && email != null
                && email != null;
    }
}
