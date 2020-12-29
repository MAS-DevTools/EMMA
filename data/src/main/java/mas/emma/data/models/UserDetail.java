/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import mas.emma.data.statictypes.enums.ProfileStatus;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.DateSerializer;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author nlmsc
 */
@Entity
@Table(name = DatabaseConstants.USERDETAILSTBL)
public class UserDetail extends BaseService implements Serializable {

    public UserDetail() {
    }

    public UserDetail(String id, Date birthday, String street_name, String house_number, String zip, String city, String phonenumber, UserMaster UserMaster, ProfileStatus profile_status, Date last_seen) {
        this.id = id;
        this.birthday = birthday;
        this.street_name = street_name;
        this.house_number = house_number;
        this.zip = zip;
        this.city = city;
        this.phonenumber = phonenumber;
        this.UserMaster = UserMaster;
        this.profile_status = profile_status;
        this.last_seen = last_seen;
    }

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",
            strategy = "uuid")
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_ID, length = 250, nullable = false, unique = true)
    private String id;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_BIRTHDAY, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date birthday;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_STREET_NAME, length = 250, nullable = false, unique = false)
    private String street_name;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_HOUSE_NUMBER, length = 250, nullable = false, unique = false)
    private String house_number;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_ZIP, length = 250, nullable = false, unique = false)
    private String zip;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_CITY, length = 250, nullable = false, unique = false)
    private String city;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_PHONENUMBER, length = 250, nullable = false, unique = false)
    private String phonenumber;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_PROFILE_STATUS, length = 250, nullable = false, unique = false)
    @Enumerated(EnumType.STRING)
    private ProfileStatus profile_status;
    @Getter
    @Setter
    @Column(name = DatabaseConstants.USERDETAILS_LAST_SEEN, length = 250, nullable = false, unique = false)
    @JsonSerialize(using = DateSerializer.class)
    private Date last_seen;
    @Getter
    @OneToOne
    @MapsId
    @JoinColumn(name = DatabaseConstants.USER_ID)
    private UserMaster UserMaster;

    @JsonIgnore
    public UserDetail valueOf(String JObject) {
        return toObject(JObject, this.getClass());
    }

    @JsonIgnore
    public boolean isValid() {
        return id != null
                && birthday != null
                && street_name != null
                && house_number != null
                && zip != null && city != null
                && phonenumber != null
                && profile_status != null;
    }

    @JsonIgnore
    public void setMasterUser(UserMaster UserMaster) {
        this.UserMaster = UserMaster;
    }
}
