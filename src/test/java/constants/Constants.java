package constants;

import config.AuthConfig;
import org.aeonbits.owner.ConfigFactory;

public class Constants {

    public static class ApiActions {
        public static final String ACCOUNT_V1_GENERATE_TOKEN = "/Account/v1/GenerateToken";
        public static final String ACCOUNT_V1_LOGIN = "/Account/v1/Login";
        public static final String ACCOUNT_V1_USER = "/Account/v1/User/";
        public static final String BOOKSTORE_V1_BOOK = "/BookStore/v1/Book";
        public static final String BOOKSTORE_V1_BOOKS = "/BookStore/v1/Books";
        public static final String PROFILE = "/profile";
        public static final String FAVICON = "/favicon.ico";
    }

    public static class Headers {
        public static final String APPLICATION_JSON_UTF_8 = "application/json; charset=utf-8";
    }

    public static class Credentials {
        static AuthConfig config = ConfigFactory.create(AuthConfig.class, System.getProperties());
        public static final String USER_NAME = System.getProperty("userName", config.userName());
        public static final String PASSWORD = System.getProperty("userPassword", config.userPassword());
//        public static final String USER_NAME ="vindisel1";
//        public static final String PASSWORD = "Q!az2wsx";
    }


    public static class Books {
        public static final String BOOK_ISBN_JAVASCRIPT = "9781449365035";
    }
}
