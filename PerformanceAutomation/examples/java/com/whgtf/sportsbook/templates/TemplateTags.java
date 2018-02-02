package com.whgtf.sportsbook.templates;

/**
 * Created by javierg on 13/09/2016.
 */
public class TemplateTags {

    public class COMMON_TAGS {

        public static final String HEADER_TAG = "<?xml version='1.0' encoding='UTF-8'?><oxiFeedRequest version='1.0'><auth username='%s' password='%s'/>\r\n";

        public static final String SORT_TAG = "<sort>%s</sort>\r\n";

        public static final String STATUS_TAG = "<status>%s</status>\r\n";

        public static final String DISPLAY_TAG = "<display displayed=\"%s\"/>\r\n";

        public static final String CLOSE_BODY_TAG = "</oxiFeedRequest>";
    }

    public class EVENT_TAGS {

        public static final String EVENT_OPEN_TAG = "<eventInsert>\r\n";

        public static final String EVENT_CLOSE_TAG = "</eventInsert>\r\n";

        public static final String TEAM_OPEN_TAG = "<teamInsert>\r\n";

        public static final String TEAM_CLOSE_TAG = "</teamInsert>\r\n";

        public static final String TEAM_HOME_TAG = "<teamLocation>home</teamLocation>\r\n";

        public static final String TEAM_AWAY_TAG = "<teamLocation>away</teamLocation>\r\n";

        public static final String TEAM_NAME_TAG = "<teamName>%s</teamName>\r\n";

        public static final String EVENT_OPEN_BET_ID_TAG = "<typeId><openbetId>%s</openbetId></typeId>\r\n";

        public static final String EVENT_EXTERNAL_ID_TAG = "<eventId><externalId>%s</externalId></eventId>\r\n";

        public static final String EVENT_NAME_TAG = "<eventName>%s</eventName>\r\n";

        public static final String EVENT_START_TIME = "<startTime isOff='%s'>%s</startTime>\r\n";

        public static final String EVENT_HOME_TEAM_TAG = "<homeTeam>%s</homeTeam>\r\n";

        public static final String EVENT_AWAY_TEAM_TAG = "<awayTeam>%s</awayTeam>\r\n";

        public static final String EVENT_ALLOW_SETTLE = "<allowSettle>%s</allowSettle>\r\n";

    }

    public class MARKET_TAGS {

        public static final String MARKET_OPEN_TAG = "<marketInsert>\r\n";

        public static final String MARKET_CLOSE_TAG = "</marketInsert>\r\n";

        public static final String MARKET_EXTERNAL_EVENT_ID_TAG = "<eventId><externalId>%s</externalId></eventId>\r\n";

        public static final String MARKET_EXTERNAL_ID_TAG = "<marketId><externalId>%s</externalId></marketId>\r\n";

        public static final String MARKET_TEMPLATE_TAG = "<marketTemplate>%s</marketTemplate>\r\n";

        public static final String MARKET_TEMPLATE_TAG_WITH_PIPES = "<marketTemplate>|%s|</marketTemplate>\r\n";

        public static final String MARKET_NAME_TAG = "<marketName>%s</marketName>\r\n";

        public static final String MARKET_NAME_TAG_WITH_PIPES = "<marketName>|%s|</marketName>\r\n";

        public static final String MARKET_DISPLAY_TAG = "<display displayed='%s' order='%s'/>\r\n";

        public static final String MARKET_SORT_TAG = "<marketSort code=\"" + "WH" + "\" name=\"" + "%s" + "\" />\r\n";

        public static final String MARKET_SORT_TAG_WITH_PIPES = "<marketSort code=\"" + "WH" + "\" name=\"" + "|%s|" + "\" />\r\n";

        public static final String MARKET_BETTING_IN_RUNNING_TAG = "<bettingInRunning active=\"%s\"/>\r\n";

        public static final String MARKET_HANDICAP_TAG = "<handicapValue>%s</handicapValue>\r\n";

        public static final String MARKET_ANTEPOST_TAG = "<isApMarket>%s</isApMarket>\r\n";

        public static final String MARKET_PRICING_TAG = "<pricing live='%s' starting='%s'/>\r\n";

        public static final String MARKET_GUARANTEED_PRICE_TAG = "<gpAvail>%s</gpAvail>\r\n";

        public static final String MARKET_EARLY_PRICES_TAG = "<epActive>%s</epActive>\r\n";

        public static final String MARKET_EACHWAY_TAG = "<ewAvail>%s</ewAvail>\r\n";

        public static final String MARKET_EACHWAYPLACES_TAG = "<ewPlaces>%s</ewPlaces>\r\n";

        public static final String MARKET_EACHWAYPLACESAT_TAG = "<ewFacNum>%s</ewFacNum><ewFacDen>%s</ewFacDen>\r\n";

        public static final String MARKET_FORECAST_TAG = "<fcAvail>%s</fcAvail>\r\n";

        public static final String MARKET_TRICAST_TAG = "<tcAvail>%s</tcAvail>\r\n";

        public static final String MARKET_UPDATE_OPEN_TAG = "<marketUpdate>\r\n";

        public static final String MARKET_UPDATE_CLOSE_TAG = "</marketUpdate>\r\n";

        public static final String MARKET_ID_TAG = "<marketId><openbetId>%s</openbetId></marketId>\r\n";
    }

    public class SELECTION_TAGS {

        public static final String SELECTION_OPEN_TAG = "<selectionInsert>\r\n";

        public static final String SELECTION_CLOSE_TAG = "</selectionInsert>\r\n";

        public static final String SELECTION_UPDATE_OPEN_TAG = "<selectionUpdate>\r\n";

        public static final String SELECTION_UPDATE_CLOSE_TAG = "</selectionUpdate>\r\n";

        public static final String SELECTION_EXTERNAL_MARKET_ID_TAG = "<marketId><externalId>%s</externalId></marketId>\r\n";

        public static final String SELECTION_EXTERNAL_ID_TAG = "<selectionId><externalId>%s</externalId></selectionId>\r\n";

        public static final String SELECTION_NAME_TAG = "<selectionName>%s</selectionName>\r\n";

        public static final String SELECTION_TYPE_TAG = "<selectionType>%s</selectionType>\r\n";

        public static final String SELECTION_PRICE_TAG = "<price type=\"fractional\">%s</price>\r\n";

        public static final String SELECTION_SCORE = "<score %s"+"/>\r\n";

        public static final String SELECTION_SELECTION_SCORE = "<selectionScore %s"+"/>\r\n";

        public static final String SELECTION_RUNNER_TAG = "<stallNo>%s</stallNo>\r\n";

        public static final String SELECTION_ID_TAG = "<selectionId><openbetId>%s</openbetId></selectionId>\r\n";

        public static final String SELECTION_DISPLAY_ORDER = "<display displayed='%s' order='%s'/>\r\n";

    }
}
