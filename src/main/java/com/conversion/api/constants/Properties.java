package com.conversion.api.constants;

/**
 * Static class holding all miscellaneous constants and properties that we need.
 */
public class Properties {

    /** The user access key. Used to "authenticate" when communicating with the third party exchange rate provider. */
    public static final String USER_ACCESS_KEY = "b744b3b90df7f0a66a36f7e60221e01f";

    /** The base url which provides live currency exchange rates. */
    public static final String BASE_LIVE_URL = "http://api.currencylayer.com/live";

    /** String literal to represent the US dollar currency. Used during conversion/calculating exchange rates. */
    public static final String CURRENCY_USD = "USD";

    /** The API doc url. */
    public static final String DOCUMENTATION_URL = "swagger-ui/index.html";

    /**
     * Inner class which is used to hold query parameters that are frequently used while constructing URLs
     * for communication with the third party currency provider.
     */
    public static class URLQueryParameters {

        public static final String ACCESS_KEY = "access_key";

        public static final String CURRENCES = "currencies";

        public static final String FORMAT = "format";

    }

    /**
     * Inner class which is used to store messages describing response codes to the end user.
     */
    public static class ResponseMessage {

        public static final String OK = "OK: Your request was processed successfully.";

        public static final String NOT_FOUND = "Not Found: The resource you are trying to access was not found.";

        public static final String SERVER_ERROR = "Server Error: The server could not process your request.";
    }
}
