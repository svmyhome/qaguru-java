package constants;

import static constants.Constants.Servers.DEMOQA;

public class Constants {

    public static class RunVariables {
        final public static String server = DEMOQA;
    }

    public static class Servers {
        final public static String DEMOQA = "https://demoqa.com";
    }

    public static class Path {
        final public static String ACCOUNT_V1 = "/Account/v1";
        final public static String BOOKSTORE_V1 = "/BookStore/v1";
    }

    public static class Actions {
        final public static String LOGIN = "/Login";
        final public static String USER = "/User/";
        final public static String BOOK = "/Book";
        final public static String BOOKS = "/Books";
    }

    public static class HEADERS {
        final public static String APPLICATION_JSON = "application/json";
    }
}
