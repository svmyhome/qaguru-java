package constants;

import static constants.Constants.Servers.DEMOQA;

public class Constants {

    public static class RunVariables {
        public static final String server = DEMOQA;
    }

    public static class Servers {
        public static final String DEMOQA = "https://demoqa.com";
    }

    public static class Path {
        public static final String ACCOUNT_V1 = "/Account/v1";
        public static final String BOOKSTORE_V1 = "/BookStore/v1";
    }

    public static class ApiActions {
        public static final String LOGIN = "/Login";
        public static final String USER = "/User/";
        public static final String BOOK = "/Book";
        public static final String BOOKS = "/Books";
    }


    public static class HEADERS {
        public static final String APPLICATION_JSON_UTF_8 = "application/json; charset=utf-8";
    }

    public static class CREDENTIALS {
        public static final String USER_NAME = "vindisel";
        public static final String PASSWORD = "!Qaz2wsx";
    }
}
