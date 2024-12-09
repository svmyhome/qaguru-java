package apipages.constants;

import static apipages.constants.Constants.Path.PATH;
import static apipages.constants.Constants.Servers.REQRES;

public class Constants {

    public static class RunVariables {
        final public static String server = REQRES;
        final public static String path = PATH;
    }

    public static class Servers {
        final public static String REQRES = "https://reqres.in/";
    }

    public static class Path {
        final public static String PATH = "api/";
    }

    public static class Actions {
        final public static String USERS = "users/";
        final public static String LIST_USERS = "users";
        final public static String LOGIN = "login";
    }
}
