/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.util.Arrays;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import static mas.emma.data.models.BaseService.toObject;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.USERMASTERORGANISATIONTBL)
public class UserMasterOrganisation {

    public UserMasterOrganisation() {
    }

    public UserMasterOrganisation(String uid, String org_id) {
        this.row_id = row_id;
        this.uid = uid;
        this.org_id = org_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERMASTERORG_ROW_ID, length = 250, nullable = false, unique = true)
    private long row_id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERMASTERORG_UID, length = 250, nullable = false, unique = false)
    private String uid;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERMASTERORG_ORG_ID, length = 250, nullable = false, unique = false)
    private String org_id;

    @JsonIgnore
    public UserMasterOrganisation valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public List<UserMasterOrganisation> valueOfList(String JObject) {
        return Arrays.asList(toObject(JObject, UserMasterOrganisation[].class));
    }

    @JsonIgnore
    public boolean isValid() {
        return row_id != 0
                && uid != null
                && org_id != null;
    }

}
