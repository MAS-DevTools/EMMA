/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import mas.emma.data.statictypes.constants.ApplicationConstants;

/**
 *
 * @author nlmsc
 */
public class ConfigurationHelper {

    private static Properties AppKeys;

    public Set<Object> getFileKeys(String file) {
        try ( InputStream input = this.getClass().getClassLoader().getResourceAsStream(file)) {
            AppKeys = new Properties();
            AppKeys.load(input);
            return AppKeys.keySet();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public Set<Object> getFileKeys(File file) {
        try ( InputStream input = new FileInputStream(file)) {
            AppKeys = new Properties();
            AppKeys.load(input);
            return AppKeys.keySet();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return null;
    }

    public static String getKeyValue(String key) {
        return AppKeys.getProperty(key);
    }

    public boolean isExcluded(String valueString) {
        return valueString.equals(String.valueOf(false))
                || valueString.equals(ApplicationConstants.PORTNAME)
                || valueString.equals(String.valueOf(true));
    }
}
