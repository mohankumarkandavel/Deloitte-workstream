package nz.co.vincens.service;

/**
 * Parameters for database connection
 * Created by Shenghong Huang on 06/09/2017.
 * Last modified on 10/09/2017
 */
public class DatabaseController {
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://rm-wz9646cu4gae4478fo.mysql.rds.aliyuncs.com:3306/workload";
    private final String user = "root";
    private final String password = "Student2017SoftwareEngineering";

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
