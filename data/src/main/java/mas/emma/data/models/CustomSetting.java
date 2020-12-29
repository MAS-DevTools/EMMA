/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import lombok.Getter;
import lombok.Setter;
import mas.emma.data.statictypes.constants.DatabaseConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author nlmsc
 */
@Component
@ConfigurationProperties(prefix = DatabaseConstants.CUSTOMPREFIX)
public class CustomSetting {

    //<editor-fold defaultstate="collapsed" desc="Properties">
    //private static final Logger logger = LoggerFactory.getLogger(CustomSetting.class);
    @Getter
    @Setter
    private String baseadress;
    @Getter
    @Setter
    private String h2_url;
    @Getter
    @Setter
    private String halexplorer_url;
    @Getter
    @Setter
    private String postman;
    @Getter
    @Setter
    private String serverUrl;
    @Getter
    @Setter
    private int serverPort;
    @Getter
    @Setter
    private Boolean debuggable;
    @Getter
    @Setter
    private String imageQuality;
    @Getter
    @Setter
    private String environment;
    @Getter
    @Setter
    private String datasource_password;
    @Getter
    @Setter
    private String datasource_username;
    @Getter
    @Setter
    private String datasource_url;
    @Getter
    @Setter
    private int cache_time;
    @Getter
    @Setter
    private String app_identifier;
    @Getter
    @Setter
    private String sec_u;
    @Getter
    @Setter
    private String sec_p;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
//    public CustomSetting() {
//        logger.info("Loading custom properties");
//    }
//
//    @PostConstruct
//    public void postConstruct() {
//        logger.info("Custom properties -> baseadress: '{}',h2_url: '{}', halexplorer_url: '{}', postman: '{}', serverUrl: '{}', serverPort: '{}', debuggable: '{}', imageQuality: '{}',environment: '{}', datasource_password: '{}', datasource_username: '{}', datasource_url: '{}',cache_time: '{}',app_identifier: '{}'",
//                baseadress, h2_url, halexplorer_url, postman, serverUrl, serverPort, debuggable, imageQuality, environment, datasource_password, datasource_username, datasource_url, cache_time, app_identifier);
//    }
    @Override
    public String toString() {
        return String.format("{ baseadress: %s, h2_url: %s, halexplorer_url: %s, postman: %s, serverUrl: %s, serverPort: %d, debuggable: %s, imageQuality: %s, environment: %s, cache_time: %s, app_identifier: %s}",
                baseadress, h2_url, halexplorer_url, postman, serverUrl, serverPort, debuggable, imageQuality, environment, cache_time, app_identifier);
    }

    //</editor-fold>
}
