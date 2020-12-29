package mas.emma.webapi.controllers;

import java.util.stream.Collectors;
import mas.emma.data.statictypes.constants.ControllerConstants;
import mas.emma.services.Application;
import mas.emma.webapi.WebapiApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import mas.emma.services.interfaces.ActionResponse;

/**
 *
 * @author nlmsc
 */
@Service
public class BaseController {

    protected static ResponseEntity runWithErrorHandeling(ActionResponse action, MultiValueMap<String, String> headers) {
        try {
            log(headers);

            if (isAppAuthorized(headers.getFirst(ControllerConstants.HEADER_APP_ID))) {
                Application.cacheInstance().cacheUser(headers.getFirst(ControllerConstants.HEADER_USERID));
                ResponseEntity responseEntity = action.invokeRespone();
                if (responseEntity.hasBody()) {
                    return responseEntity;
                } else {
                    return new ResponseEntity<>(
                            responseEntity.getBody(),
                            HttpStatus.FAILED_DEPENDENCY);
                }
            } else {
                return new ResponseEntity<>(
                        null,
                        HttpStatus.FORBIDDEN);
            }
        } catch (Exception exception) {
            System.err.print(exception);
        }
        return new ResponseEntity<>(
                null,
                HttpStatus.BAD_REQUEST);
    }

    /**
     *
     * @param id
     * @return
     */
    protected static boolean isAppAuthorized(String id) {
        return WebapiApplication.authenticatedApps.stream().anyMatch(auth -> (auth.getId().equals(id)));
    }

    protected static void log(MultiValueMap<String, String> headers) {
        headers.forEach((key, value) -> {
            System.out.println((String.format(
                    "Header '%s' = %s", key, value.stream().collect(Collectors.joining("|")))));
        });
    }
}
