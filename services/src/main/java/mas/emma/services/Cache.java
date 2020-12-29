/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mas.emma.services.helpers.TimeHelper;
import org.springframework.beans.factory.support.ManagedMap;

/**
 *
 * @author nlmsc
 */
public class Cache {

    protected static volatile Map<String, Date> cachedUser;

    public Cache() {
        cachedUser = new ManagedMap<>();
    }

    @SuppressWarnings("unchecked")
    protected void removeOutDatedUSer() {
        Iterator iter = cachedUser.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, Date> user = (Entry<String, Date>) iter.next();
            if (user.getValue().before(TimeHelper.getDateWithOffset(Application.getSettings().getCache_time()))) {
                iter.remove();
            }
        }
    }

    public void cacheUser(String userId) {
        if (userId != null) {
            cachedUser.put(userId, new Date());
        }
    }
}
