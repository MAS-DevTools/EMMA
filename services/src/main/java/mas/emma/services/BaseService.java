/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services;

import com.google.gson.Gson;
import java.util.List;
import mas.emma.services.interfaces.Action;
import mas.emma.services.interfaces.ActionRequest;
import mas.emma.services.interfaces.ActionWithReturn;

/**
 *
 * @author nlmsc
 */
@SuppressWarnings("unchecked")

public class BaseService<T> {

    @SuppressWarnings("unchecked")
    public static void runWithErrorHandeling(Action action) {
        try {
            action.invoke();
        } catch (InterruptedException exception) {
            System.err.print(exception);
        } catch (Exception exception) {
            System.err.print(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public List<T> returnGenericListWithErrorHandeling(ActionWithReturn action) {
        try {
            return action.invokeT();
        } catch (Exception exception) {
            System.err.print(exception);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public String runHttpRequestWithErrorHandeling(ActionRequest action) {
        try {
            return action.invokeRequest();
        } catch (Exception exception) {
            System.err.print(exception);
        }
        return null;
    }

    protected void log(String message) {
        System.out.println(message);
    }

    protected void logHttpRequest(String user_id, String method, String message, String uri, String statusCode) {
        String record = String.format("{ user: %s \r\n method: %s \r\n message: %s \r\n uri: %s \r\n statusCode: %s}",
                user_id, method, message, uri, statusCode);
        System.out.println(record);
    }

    public static <T> T or(T object, T ifNull) {
        return object != null ? object : ifNull;
    }

    public static String toJson(Object in) {
        Gson gson = new Gson();
        return gson.toJson(in);
    }
}
