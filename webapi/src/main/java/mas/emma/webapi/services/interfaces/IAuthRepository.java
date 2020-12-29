/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.webapi.services.interfaces;

import mas.emma.data.models.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author nlmsc
 */
@Repository
public interface IAuthRepository extends JpaRepository<Auth, String> {

}
