/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import mas.emma.data.models.UserMaster;
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
public interface IUserMasterRepository extends JpaRepository<UserMaster, String> {

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.USERSTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.USER_EMAIL + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.USER_EMAIL + SQLDbConstants.ENDCHARACTER)
    UserMaster findUserByEmail(@Param(DatabaseConstants.USER_EMAIL) String email);

}
