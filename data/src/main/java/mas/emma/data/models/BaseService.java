/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.data.models;

import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author nlmsc
 */
public class BaseService {

    private static ObjectMapper mapper;

    public BaseService() {
        mapperInstance().setVisibility(JsonMethod.FIELD, Visibility.ANY);
    }

    public static ObjectMapper mapperInstance() {
        if (mapper == null) {
            mapper = new ObjectMapper();

        }
        return mapper;
    }

    public static <T> T toObject(String jObject, Class<T> type) {
        try {
            return mapperInstance().readValue(jObject, type);
        } catch (Exception exception) {
            return null;
        }

    }

}
