package alas.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationPropertiesReader {


    //creating object of properties
    private static Properties properties = new Properties();

    //using static method to load file in java memory
    static {

        try {

            FileInputStream file = new FileInputStream("configuration.properties");

            properties.load(file);

            file.close();

        } catch (IOException e) {

            System.out.println("File not found!");

            e.printStackTrace();
        }


    }

    //method to getProperty from configuration.properties file
    public static String getProperty (String keyword) {

        return properties.getProperty(keyword);

    }


}
