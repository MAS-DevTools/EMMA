package mas.emma.webapi;

import java.io.IOException;
import java.util.List;
import lombok.Getter;
import mas.emma.data.models.AppAuth;
import mas.emma.data.models.CustomSetting;
import mas.emma.data.statictypes.enums.ApplicationType;
import mas.emma.services.Application;
import mas.emma.webapi.services.AppAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"mas.emma.data.models"})
@EnableJpaRepositories(basePackages = {"mas.emma.webapi.repositories.interfaces", "mas.emma.webapi.services"})
@ComponentScan(basePackages = {"mas.emma.data.models", "mas.emma.webapi.controllers", "mas.emma.webapi.services", "mas.emma.webapi.configurations"})
public class WebapiApplication extends Application {

    @Getter
    public static List<AppAuth> authenticatedApps;

    @Autowired
    private CustomSetting Setting;

    @Autowired
    private AppAuthService AppAuthService;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebapiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onAfterStartup() {
        authenticatedApps = (List<AppAuth>) AppAuthService.getAll().getBody();
        AppAuth appAuth = (AppAuth) AppAuthService.getByName(ApplicationType.WEBAPI.name()).getBody();
        configure(ApplicationType.WEBAPI, Setting, appAuth);
    }
}
