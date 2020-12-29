/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.httpendpoints;

import lombok.experimental.ExtensionMethod;
import mas.emma.data.models.AppAuth;
import mas.emma.data.statictypes.constants.ControllerConstants;
import mas.emma.services.Application;
import mas.emma.services.BaseService;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.http.HttpMethod;

/**
 *
 * @author nlmsc
 */
@ExtensionMethod({java.util.Arrays.class, BaseService.class})
public class HttpAppAuth {

    private String base = Application.getSettings().getServerUrl() + ControllerConstants.APPAUTHPATH;

    //<editor-fold defaultstate="collapsed" desc="CREATE">
    public <S extends AppAuth> AppAuth create(String user_id, S s) {
        String uri = base + ControllerConstants.CREATE;
        return new AppAuth().valueOf(Application.clientHandlerInstance().getEntity(uri, user_id, HttpMethod.POST, new StringEntity(s.toJson(), ContentType.APPLICATION_JSON))
        );
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="READ">
    public AppAuth getById(String user_id, String id) {
        String uri = base + ControllerConstants.GET_BY_ID2 + id;
        return new AppAuth().valueOf(Application.clientHandlerInstance().getEntity(uri, user_id, HttpMethod.GET, null));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="UPDATE">
    public <S extends AppAuth> AppAuth update(String user_id, S s) {
        String uri = base + ControllerConstants.UPDATE;
        return new AppAuth().valueOf(Application.clientHandlerInstance().getEntity(uri, user_id, HttpMethod.PUT, new StringEntity(s.toJson(), ContentType.APPLICATION_JSON)));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="DELETE">
    public boolean deleteById(String user_id, String id) {
        String uri = base + ControllerConstants.DELETE_BY_ID2 + id;
        return Boolean.valueOf(Application.clientHandlerInstance().getEntity(uri, user_id, HttpMethod.DELETE, null));
    }

    public boolean deleteByIds(String user_id, Iterable<String> itrbl) {
        String uri = base + ControllerConstants.DELETE_BY_IDS;

        return Boolean.valueOf(Application.clientHandlerInstance().getEntity(uri, user_id, HttpMethod.DELETE, new StringEntity(itrbl.toJson(), ContentType.APPLICATION_JSON)));
    }

    //</editor-fold>
}
