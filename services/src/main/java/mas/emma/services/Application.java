package mas.emma.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import mas.emma.data.models.AppAuth;
import mas.emma.data.models.CustomSetting;
import mas.emma.data.statictypes.constants.ApplicationConstants;
import mas.emma.data.statictypes.constants.ControllerConstants;
import mas.emma.data.statictypes.enums.ApplicationType;
import mas.emma.data.statictypes.enums.Environment;
import mas.emma.services.helpers.AES;
import mas.emma.services.helpers.BrowserHelper;
import mas.emma.services.helpers.ConfigurationHelper;
import mas.emma.services.helpers.ExceptionHelper;
import mas.emma.services.helpers.FileHelper;
import mas.emma.services.interfaces.TaskScheduler;
import org.springframework.http.HttpMethod;

/**
 *
 * @author nlmsc
 */
public class Application extends BaseService {

    //<editor-fold defaultstate="collapsed" desc="Properties ">
    @Getter
    private static volatile ApplicationType appType;
    @Getter
    private static CustomSetting settings;
    @Getter
    private static AppAuth appAuth;
    private static String baseAdress;
    private static Map<String, String> config;
    private static ConfigurationHelper configurationHelper;
    private static Environment env;
    private static String userDir;
    private static DatabaseService databaseService;
    private static FileHelper fileHelper;
    private static TaskScheduler task;
    private static Cache cache;
    private static AuthenticationService authenticationService;
    private static AES aes;
    private static HttpClientHandler clientHandler;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Singletons">
    protected static ConfigurationHelper configurationHelperInstance() {
        if (configurationHelper == null) {
            configurationHelper = new ConfigurationHelper();
        }
        return configurationHelper;
    }

    public static HttpClientHandler clientHandlerInstance() {
        if (clientHandler == null) {
            clientHandler = new HttpClientHandler();
        }
        return clientHandler;
    }

    protected static DatabaseService dbServiceInstance() {
        if (databaseService == null) {
            databaseService = new DatabaseService(env, settings.getDatasource_url(), appType, settings.getDatasource_username(), settings.getDatasource_password());
        }
        return databaseService;
    }

    protected static FileHelper fileHelperInstance() {
        if (fileHelper == null) {
            fileHelper = new FileHelper();
        }
        return fileHelper;
    }

    protected static TaskScheduler taskInstance() {
        if (task == null) {
            switch (appType) {
                case WEBAPI -> {
                    task = new ChatServerTask();
                }
                case CHATSERVER -> {
                    task = new WebAPITask();
                }
                default -> {
                    break;
                }
            }
        }
        return task;
    }

    public static Cache cacheInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static AES aesInstance() {
        if (aes == null) {
            aes = new AES(appAuth.getGuid());
        }
        return aes;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">

    public static void configure(ApplicationType app, CustomSetting settings, AppAuth appAuth) {

        runWithErrorHandeling(() -> {
            //<editor-fold defaultstate="collapsed" desc="Create Instances">
            Application.appAuth = appAuth;
            Application.appType = app;
            Application.env = Environment.valueOf(settings.getEnvironment());
            Application.settings = settings;
            if (appAuth == null) {
                String path = String.format("%s%s%s%s", settings.getServerUrl(), ControllerConstants.APPAUTHPATH, ControllerConstants.GET_BY_ID2, settings.getApp_identifier());
                String entity = Application.clientHandlerInstance().getEntity(path, Application.appType.name(), HttpMethod.GET, null);
                Application.appAuth = new AppAuth().valueOf(entity);
            }
            config = new HashMap<>();
            cacheInstance();//Start Singleton
            taskInstance(); //Start Singleton
            aesInstance(); //Start Singleton

            //</editor-fold>
            userDir = System.getProperty(ApplicationConstants.USERDIR);
            getApplicationFile(configurationHelperInstance().getFileKeys(ApplicationConstants.APPLICATIONFILENAME));

            if (env.equals(Environment.DEVELOPMENT) && (app == app.WEBAPI || app == app.WEBCLIENT)) {
                dbServiceInstance().runScript(fileHelperInstance().getFileFromResource(ApplicationConstants.SCHEMAFILENAME));
                dbServiceInstance().runScript(fileHelperInstance().getFileFromResource(ApplicationConstants.DATAFILENAME));
                BrowserHelper.open(settings.getBaseadress() + settings.getH2_url());
                //BrowserHelper.open(settings.getBaseadress() + settings.getHalexplorer_url());
                //String dataFolder = System.getenv(ApplicationConstants.LOCALAPPDATA);
                //OpenPogram(dataFolder + settings.getPostman());
            }
        });
    }

    protected void openPogram(String program) {
        try {
            Process process = Runtime.getRuntime().exec(program);
        } catch (IOException e) {
            ExceptionHelper.logException(e.getMessage(), null, appType);
        }
    }

    private static void getApplicationFile(Set<Object> keys) {
        if (keys != null) {

            keys.forEach(key -> {
                String propValue = configurationHelperInstance().getKeyValue(key.toString());
                String value;
                if (configurationHelperInstance().isExcluded(propValue) || configurationHelperInstance().isExcluded(key.toString())) {
                    value = propValue;
                } else {
                    value = propValue;// AES.decrypt(StringHelper.FilterKey((propValue),AESType.Decrypt)); // Decryption
                }
                String keyValue = key.toString();
                config.put(keyValue, value);
            });
        }
    }
    //</editor-fold>
}
