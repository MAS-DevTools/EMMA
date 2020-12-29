/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas.emma.services.helpers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nlmsc
 */
public class FileHelper {

    public static List<File> getlistFiles(final File folder) {
        List<File> listFiles = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            listFiles.add(fileEntry);
        }
        return listFiles;
    }

    public File getFileFromResource(String fileName) {

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource == null) {
                throw new IllegalArgumentException("file not found! " + fileName);
            } else {

                // failed if files have whitespaces or special characters
                //return new File(resource.getFile());
            }
            return new File(resource.toURI());
        } catch (Exception e) {
        }
        return null;
    }
}
