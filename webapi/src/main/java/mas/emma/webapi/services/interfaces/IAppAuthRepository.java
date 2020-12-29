/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import mas.emma.data.models.AppAuth;
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
public interface IAppAuthRepository extends JpaRepository<AppAuth, String> {

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.APPAUTHTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.APP_AUTH_NAME + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.APP_AUTH_NAME + SQLDbConstants.ENDCHARACTER)
    AppAuth findAuthByName(@Param(DatabaseConstants.APP_AUTH_NAME) String application_name);
}
