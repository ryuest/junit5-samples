package com.whgtf.sportsbook.model;

/**
 * Created by israel on 22/02/2016.
 */
public class Openbet {

    public enum Status {
        ACTIVE("A"),
        SUSPENDED("S");

        private final String status;

        Status(String status) {
            this.status = status;
        }

        public String getStatus() {
            return this.status;
        }
    }

    public enum Display {
        YES("true"),
        NO("false");

        private final String display;

        Display(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return this.display;
        }
    }

    public enum BetInRun {
        YES("true"),
        NO("false");

        private final String betInRun;

        private BetInRun(String betInRun) {
            this.betInRun = betInRun;
        }

        public String getBetInRun() {
            return this.betInRun;
        }
    }
}
