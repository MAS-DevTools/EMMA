/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import mas.emma.data.statictypes.constants.ApplicationConstants;
import mas.emma.data.statictypes.constants.SQLDbConstants;
import mas.emma.data.statictypes.enums.AESType;

/**
 *
 * @author nlmsc
 */
public class StringHelper {

    public static String filterKey(String key, AESType type) {

        char[] chars = key.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            if (type == AESType.Encrypt && ApplicationConstants.SPECIALCHARACTERS.contains(String.valueOf(c))) {
                sb.append('\\');
            } else if (type == AESType.Decrypt && String.valueOf(c).contains("\\")) {

                continue;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static String selectQueryBuilder(List<String> columns, String table, HashMap<String, String> parameters) {
        StringBuilder builder = new StringBuilder(SQLDbConstants.SELECT);
        int count = columns.size();
        for (int i = 0; i < count; i++) {
            builder.append(columns.get(i));
            if ((i + 1) < count) {
                builder.append(SQLDbConstants.COMMA);
            }
        }

        builder.append(SQLDbConstants.FROM + table);

        if (parameters != null && parameters.size() > 0) {
            builder.append(SQLDbConstants.WHERE);
            int i = 0;
            for (Entry<String, String> entry : parameters.entrySet()) {

                builder.append(entry.getKey() + entry.getValue());
                if ((i + 1) < count) {
                    builder.append(SQLDbConstants.AND);
                }
                i++;
            }
        }
        return builder.toString();
    }
}
