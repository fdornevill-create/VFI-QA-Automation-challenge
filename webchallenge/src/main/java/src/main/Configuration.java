package src.main;

import java.io.FileInputStream;
import java.util.Properties;

public interface Configuration {

    Properties prop = new Properties();

    String configurationPath = "config.properties";

    int PASS = 1;
    int FAIL = 5;


    String PROJECT_DIR = getProjectDir();

    String BROWSER = getProperty("browser");
    String URL = getProperty("appUrl");
    String EMAIL = getProperty("user_email");
    String PASSWORD = getProperty("password");

    boolean IS_EXECUTE_ON_TESTRAIL = isTestRailEnable();


    String TESTRAIL_URL = getProperty("testrail_url");
    String TESTRAIL_USERNAME = getProperty("testrail_username");
    String TESTRAIL_PASSWORD = getProperty("testrail_password");
    String TESTRAIL_PROJECT = getProperty("testrail_project");

    int TESTRAIL_RUN_ID = Integer.parseInt(getProperty("run_id"));

    String BRIGHTCOVE_ACCOUNT_ID = getProperty("brightcove_account_id");

    String IOS_APP_NAME = getProperty("ios_appname");
    String IOS_DEVICE = getProperty("ios_devicename");
    String IOS_VERSION = getProperty("ios_version");

    static String getProjectDir() {
        return System.getProperty("user.dir");
    }

    static String getProperty(String key) {
        try {
            prop.load(new FileInputStream(configurationPath));
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return prop.getProperty(key);
    }

    static boolean isTestRailEnable() {
        return getProperty("enable_testrail").equalsIgnoreCase("Yes");
    }


}

