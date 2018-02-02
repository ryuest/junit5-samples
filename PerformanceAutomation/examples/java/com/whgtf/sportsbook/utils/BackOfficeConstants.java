package com.whgtf.sportsbook.utils;

/** 
* @author israel.lozano@williamhill.com
*/
public class BackOfficeConstants {
	
	public static final String EVENT_TYPE_MATCH = "MTCH";
	
	public static final String EVENT_TYPE_TOURNAMENT = "TNMT";
	
	public static final String EVENT_STATUS_ACTIVE = "active";
	
	public static final String EVENT_STATUS_SUSPENDED = "suspended";
	
	public static final String EVENT_DISPLAYED_YES = "yes";
	
	public static final String EVENT_DISPLAYED_NO = "no";
	
	public static final String MARKET_STATUS_ACTIVE = "active";
	
	public static final String MARKET_STATUS_SUSPENDED = "suspended";
	
	public static final String MARKET_DISPLAYED_YES = "yes";
	
	public static final String MARKET_DISPLAYED_NO = "no";

    public static final String MARKET_SP_YES = "yes";

    public static final String MARKET_SP_NO = "no";

    public static final String MARKET_LP_YES = "yes";

    public static final String MARKET_LP_NO = "no";

    public static final String MARKET_EACH_WAY_YES = "yes";

    public static final String MARKET_EACH_WAY_NO = "no";
	
	public static final String SELECTION_STATUS_ACTIVE = "active";
	
	public static final String SELECTION_STATUS_SUSPENDED = "suspended";
	
	public static final String SELECTION_DISPLAYED_YES = "yes";
	
	public static final String SELECTION_DISPLAYED_NO = "no";
	
	public static final String EVENT_OFF_FLAG_NA = "undefined";
	
	public static final String EVENT_OFF_FLAG_NO = "no";
	
	public static final String EVENT_OFF_FLAG_YES = "yes";
	
	public static final String EVENT_STARTING_YES = "yes";
	
	public static final String EVENT_STARTING_NO = "no";
	
	public static final String MARKET_BIR_NO = "no";
	
	public static final String MARKET_BIR_YES = "yes";

    public static final String MARKET_ANTEPOST_YES = "yes";

    public static final String MARKET_ANTEPOST_NO = "no";

    public static final String RULE4_PRICE_LIVE = "live";

    public static final String RULE4_PRICE_START = "start";

    public static final String RULE4_VALID_YES = "yes";

    public static final String RULE4_VALID_NO = "no";

    public static final String DIVIDEND_TYPE_TOTE_WIN = "TW";

    public static final String DIVIDEND_TYPE_TOTE_PLACE = "TP";

    public static final String DIVIDEND_TYPE_FORECAST = "FC";

    public static final String DIVIDEND_TYPE_TRICAST = "TC";
	
	public static enum SelectionTypes {
		home, draw, away, odd, even, over, under, line, score, none
	}

	public static String[] horses = new String[]{"It's A Close Call", "Final Nudge", "Captain Probus",
			"Pursuitofhappiness", "Abidjan", "Multimedia", "Queen Of Rock", "Sandford Castle",
			"Whatthebutlersaw", "Dreamisi", "Master Hide", "Darsi's Dream", "East Hill"};

	public static String[] jockeys = new String[]{"Rawlinson, Alistair", "Egan, J", "Quinlan, Mr R P",
			"Fox, K", "Sousa, S De", "Broome, Miss A", "O'Connell, B T", "Twiston-Davies, W",
			"Best, J", "O'Brien, Paul", "Zechner, K", "Catton, Mr B", "Birkett, Shelley"};

	public static String[] greyhounds = new String[]{"Jogon Jenny", "Louliediem Gem", "Jayms Brightwood",
			"Witcombe Triumph", "No Picnic", "Our Girl Una", "Aintgottwobob ", "Mottos Rogue",
			"Minnies Trixie", "Crucial Diva", "Chickadee", "Chilly Baja", "Primo Lisa"};

}
