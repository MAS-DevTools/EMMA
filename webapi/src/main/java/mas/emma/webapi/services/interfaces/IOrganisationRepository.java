/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import mas.emma.data.models.Organisation;
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
public interface IOrganisationRepository extends JpaRepository<Organisation, String> {

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.ORGANISATIONTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.ORGANISATION_NAME + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.ORGANISATION_NAME + SQLDbConstants.ENDCHARACTER)
    UserMaster findOrganisationByName(@Param(DatabaseConstants.ORGANISATION_NAME) String organisation_name);

    @Query(SQLDbConstants.SELECTALLFROMTABLE + DatabaseConstants.ORGANISATIONTBL + SQLDbConstants.JAVAT
            + SQLDbConstants.WHERE + SQLDbConstants.JAVAT + SQLDbConstants.DOT
            + DatabaseConstants.ORGANISATION_EMAIL + SQLDbConstants.EQUAL
            + SQLDbConstants.BEGINCHARACTER + SQLDbConstants.JAVAPARAM
            + DatabaseConstants.ORGANISATION_EMAIL + SQLDbConstants.ENDCHARACTER)
    UserMaster findOrganisationByEmail(@Param(DatabaseConstants.ORGANISATION_EMAIL) String email);
}
