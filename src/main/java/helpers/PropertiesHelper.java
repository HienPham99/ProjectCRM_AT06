package helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault="src/test/resources/configs/configs.properties";

    public static Properties loadAllFiles(){
        LinkedList<String> files = new LinkedList<>();
        files.add("src/test/resources/configs/configs.properties");
        try {
            properties = new Properties();
            for (String f:files){
                Properties tempProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
            return properties;
        }catch (IOException ioe){
            return new Properties();
        }
    }

    public static String getValue(String key){
        String keyValue = null;
        try {
            if(file==null){
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            keyValue = properties.getProperty(key);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return keyValue;
    }
}
