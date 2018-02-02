package com.whgtf.sportsbook.obfeed;

/**
 * Created by juri on 06/12/2016.
 */
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MyApp {

    static Logger logger = Logger.getLogger(MyApp.class.getName());

    public static void main(String[] args) {


        // BasicConfigurator replaced with PropertyConfigurator.
    //    PropertyConfigurator.configure(args[0]);

        logger.info("Entering application.");
   //     bar.doIt();
        logger.debug("Exiting application.");
    }
}
