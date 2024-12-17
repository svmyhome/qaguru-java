package apipages.constants;

import static apipages.constants.Constants.Path.PATH;
import static apipages.constants.Constants.Servers.REQRES;

public class Constants {

    public static class RunVariables {
        public final static String server = REQRES;
        public final static String path = PATH;
    }

    public static class Servers {
        public final static String REQRES = "https://reqres.in/";
    }

    public static class Path {
        public final static String PATH = "api/";
    }

    public static class Actions {
        public final static String USERS = "users/";
        public final static String LIST_USERS = "users";
        public final static String LOGIN = "login";
    }
}
