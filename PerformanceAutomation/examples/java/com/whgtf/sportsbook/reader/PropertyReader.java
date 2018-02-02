package com.whgtf.sportsbook.reader;

/**
 * Created by Juri on 21/10/2016.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class PropertyReader {
    private final static Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    private final static Properties PROP_FILE = new Properties();
//    private static final String PROP_FILE_ROOT = "src/main/resources/";
    private static final String PROP_FILE_SUFFIX = ".environment.properties";
    private InputStream propFilePath;

    private static final String ENV_JVM_ARG = "env";

    // TODO: Remove all deprecated members

    /**
     * Default constructor
     * Initialises the target environment variable using JVM arg
     */
    public PropertyReader(String env) {

        final String environment = env;

        if (environment == null)
            throw new CustomExceptions.NullArgumentException("Null JVM arg " + ENV_JVM_ARG);
        else if (environment.isEmpty())
            throw new CustomExceptions.EmptyArgumentException("Empty JVM arg " + ENV_JVM_ARG);
        else {
            propFilePath = Thread.currentThread()
                    .getContextClassLoader().getResourceAsStream(env.toLowerCase().trim()+ PROP_FILE_SUFFIX);

            if (isPropFileThere(this.propFilePath)) loadPropFile();
            else throw new CustomExceptions.PropFileNotFoundException("Property file doesn't exist in path " + this.propFilePath+
            " or not supported environment - "+env);
        }

    }

    /**
     * Check if file exists in given path
     *
     * @return boolean
     */
    private boolean isPropFileThere(InputStream propFilePath) {
        BufferedReader eventIDList = new BufferedReader(new InputStreamReader(propFilePath));
        try {
            return eventIDList.ready();
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * Load property file
     */
    private void loadPropFile() {
        try {
        //    InputStream fileReader = new InputStream(this.propFilePath);
        PROP_FILE.load(propFilePath);
        } catch (FileNotFoundException e) {
            logger.error("****** Unable to find property file " + propFilePath, e);
        } catch (IOException e) {
            logger.error("****** Unable to read Property file " + propFilePath, e);
        }

    }

    /**
     * Get property by key
     *
     * @param propKey String
     * @return propValue String
     */
    public String getProperty(final String propKey) {

        String key, value;

        if (propKey == null)
            throw new CustomExceptions.NullArgumentException("Null argument: property key");
        else if (propKey.isEmpty())
            throw new CustomExceptions.EmptyArgumentException("Empty argument: property key");
        else
            key = propKey;

        value = PROP_FILE.getProperty(key);
        if (value == null)
            throw new CustomExceptions.InvalidKeyException("The property file doesn't contain property with key " + propKey);
        else if (value.isEmpty())
            throw new CustomExceptions.EmptyValueException("The value for property key " + propKey + " is empty");
        else
            return value;
    }

    /**
     * Custom exceptions
     */
    public static class CustomExceptions {

        /**
         * Custom exception - prop file not found
         */
        public static class PropFileNotFoundException extends RuntimeException {

            private static final long serialVersionUID = 1992953365232807009L;

            PropFileNotFoundException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - null argument
         */
        public static class NullArgumentException extends RuntimeException {

            private static final long serialVersionUID = 1996953365232807009L;

            NullArgumentException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - empty argument
         */
        public static class EmptyArgumentException extends RuntimeException {

            private static final long serialVersionUID = 1996953345232807009L;

            EmptyArgumentException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - null value
         */
        public static class EmptyValueException extends RuntimeException {

            private static final long serialVersionUID = 1999753365239807009L;

            EmptyValueException(String message) {
                super(message);
            }
        }

        /**
         * Custom exception - empty key
         */
        public static class InvalidKeyException extends RuntimeException {

            private static final long serialVersionUID = 1998753365152807009L;

            InvalidKeyException(String message) {
                super(message);
            }
        }

    }

}
