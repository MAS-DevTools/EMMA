package mas.emma.chatapi;

import mas.emma.data.models.CustomSetting;
import mas.emma.data.statictypes.enums.ApplicationType;
import static mas.emma.services.Application.configure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;

/**
 * Created by Marvin
 */
@SpringBootApplication
@ComponentScan(basePackages = {"mas.emma.data.models"})
public class ServerMain {

    @Autowired
    private CustomSetting settings;
    private Server server;

    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);

    }

    @EventListener(ApplicationReadyEvent.class)
    private void onAfterRender() {
        configure(ApplicationType.CHATSERVER, settings, null);
        server = new Server(settings.getServerPort());
        server.start();
    }
}
