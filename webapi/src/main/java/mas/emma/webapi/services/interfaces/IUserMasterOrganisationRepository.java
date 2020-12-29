/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import java.util.List;
import mas.emma.data.models.UserMasterOrganisation;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import mas.emma.data.statictypes.constants.SQLDbConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nlmsc
 */
@Repository
public interface IUserMasterOrganisationRepository extends JpaRepository<UserMasterOrganisation, Long> {

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.USERMASTERORGANISATIONTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.USERMASTERORG_ORG_ID + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.USERMASTERORG_ORG_ID + SQLDbConstants.ENDCHARACTER)
    List<UserMasterOrganisation> findUserIdsByOrganisationId(@Param(DatabaseConstants.USERMASTERORG_ORG_ID) String org_id);

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.USERMASTERORGANISATIONTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.USERMASTERORG_UID + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.USERMASTERORG_UID + SQLDbConstants.ENDCHARACTER)
    List<UserMasterOrganisation> findOrganisationIdsByUserId(@Param(DatabaseConstants.USERMASTERORG_UID) String uid);

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.USERMASTERORGANISATIONTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.USERMASTERORG_ORG_ID + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.USERMASTERORG_ORG_ID + SQLDbConstants.ENDCHARACTER
            + SQLDbConstants.AND + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.USERMASTERORG_UID + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.USERMASTERORG_UID + SQLDbConstants.ENDCHARACTER)
    UserMasterOrganisation findRecord(@Param(DatabaseConstants.USERMASTERORG_UID) String uid, @Param(DatabaseConstants.USERMASTERORG_ORG_ID) String org_id);
}
