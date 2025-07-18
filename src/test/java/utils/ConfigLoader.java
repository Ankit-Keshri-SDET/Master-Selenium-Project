package utils;

import constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        switch (env) {
            case "STAGE":
                properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            case "PROD":
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalArgumentException("INVALID ENVIRONMENT TYPE " + env);
        }
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseURL");
        if (prop != null)
            return prop;
        else throw new RuntimeException("property baseUrl is not specified in the stg_config.properties file");
    }

    public String getUsername() {
        String prop = properties.getProperty("username");
        if (prop != null)
            return prop;
        else throw new RuntimeException("property username is not specified in the stg_config.properties file");
    }

    public String getPassword() {
        String prop = properties.getProperty("password");
        if (prop != null)
            return prop;
        else throw new RuntimeException("property password  is not specified in the stg_config.properties file");
    }
}
