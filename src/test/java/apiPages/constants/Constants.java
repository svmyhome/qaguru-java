package apiPages.constants;

import static apiPages.constants.Constants.Path.PATH;
import static apiPages.constants.Constants.Servers.REQRES;

public class Constants {

    public static class RunVariables {
        public static String server = REQRES;
        public static String path = PATH;
    }

    public static class Servers {
        public static String REQRES = "https://reqres.in/";
        public static String SWAPI = "https://swapi.dev/";
    }

    public static class Path {
        public static String PATH = "api/";
    }

    public static class Actions {
        public static String USERS = "users/";
        public static String LIST_USERS = "users";
        public static String LOGIN = "login";

        // SWAPI

        public static String SWAPI_PEOPLE = "people";
    }
}
