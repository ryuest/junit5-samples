package com.whgtf.sportsbook.model;

import com.whgtf.sportsbook.utils.BackOfficeConstants;
import com.whgtf.sportsbook.utils.Timer;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/** 
* @author israel.lozano@williamhill.com
*/
public class Event {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Event.class.getName());
	
	public Integer id;
	
	public String localId;

	public String pdsId;
	
	public String sport;
	
	public String competition;
	
	public Integer competitionId;

	public String allowSettle;
	
	public String name;

	public String externalEventId;
	
	public String status;
	
	public String startTime;
	
	public String offFlag;
	
	public String displayed;

	public String homeTeam;

	public String awayTeam;
	
	public Commentary commentary;

	public ArrayList<Market> markets;
	
	public String sort;

	private static final String PREFIX = "OB_EV";



	public Event () {
		this.id = -1;
		this.pdsId = StringUtils.EMPTY;
		this.name = StringUtils.EMPTY;
		this.displayed = BackOfficeConstants.EVENT_DISPLAYED_YES;
		this.status = BackOfficeConstants.EVENT_STATUS_ACTIVE;
		this.offFlag = StringUtils.EMPTY;
		this.sort = BackOfficeConstants.EVENT_TYPE_MATCH;
		this.localId = StringUtils.EMPTY;
		this.competitionId = -1;
		this.competition = StringUtils.EMPTY;
		this.startTime = StringUtils.EMPTY;
		markets = new ArrayList<Market>();
		homeTeam = StringUtils.EMPTY;
		awayTeam = StringUtils.EMPTY;
	}

	public Event(final String pdsId) {
		this.pdsId = pdsId;
		this.id = Integer.parseInt(pdsId.replace(PREFIX,""));
		this.name = StringUtils.EMPTY;
		this.displayed = BackOfficeConstants.EVENT_DISPLAYED_YES;
		this.status = BackOfficeConstants.EVENT_STATUS_ACTIVE;
		this.offFlag = StringUtils.EMPTY;
		this.sort = BackOfficeConstants.EVENT_TYPE_MATCH;
		this.localId = StringUtils.EMPTY;
		this.competitionId = -1;
		this.competition = StringUtils.EMPTY;
		this.startTime = StringUtils.EMPTY;
		markets = new ArrayList<Market>();
		homeTeam = StringUtils.EMPTY;
		awayTeam = StringUtils.EMPTY;
	}

	/**
	 * 
	 * @param competitionId is the competition number where 0 is the primary competition.
	 */
	public Event (Integer competitionId) {
		this("event_" + RandomStringUtils.randomAlphabetic(8), competitionId);
	}
	
	public Event (String name, Integer competitionId) {
		this.id = -1;
		this.pdsId = StringUtils.EMPTY;
		this.name = name;
		this.displayed = BackOfficeConstants.EVENT_DISPLAYED_YES;
		this.status = BackOfficeConstants.EVENT_STATUS_ACTIVE;
		this.offFlag = BackOfficeConstants.EVENT_OFF_FLAG_NA;
		this.sort = BackOfficeConstants.EVENT_TYPE_MATCH;
		this.localId = "01";
        this.competitionId = competitionId;
        this.competition = CompetitionIds.getCompetitionName(competitionId);

		// by default set event start time for today
        this.startTime = Timer.getDate(Timer.DayFilter.Today);
		
		markets = new ArrayList<Market>();
		homeTeam = StringUtils.EMPTY;
		awayTeam = StringUtils.EMPTY;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		this.pdsId = PREFIX.concat(String.valueOf(id));
	}


	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}
	
	public Integer getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	public String getName() {
		return name;
	}

	public String getAllowSettle() {
		allowSettle="yes";
		return allowSettle;
	}

	public String setRandomNameWithPrefix(String prefix) {
		char[] chars = "0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 5; i++) {
			char randomString = chars[random.nextInt(chars.length)];
			sb.append(randomString);
		}
		String output = sb.toString();
		name = prefix + "_"+ output;
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String date) {

		startTime = null;
		Calendar cal = Calendar.getInstance();
		switch(date.toUpperCase().trim().replaceAll(" ", "")) {
			case "YESTERDAY":
				cal.add(Calendar.DATE, -1);
				break;
			case "NOW" :
				cal.add(Calendar.SECOND, 30);
				break;
			case "INFIVEMINUTES":
				cal.add(Calendar.MINUTE, 5);
				break;
			case "MIDDAY":
				cal.set(Calendar.HOUR, 0);
				cal.set(Calendar.MINUTE, 00);
				break;
			case "MIDNIGHT":
				cal.set(Calendar.HOUR, 12);
				cal.set(Calendar.MINUTE, 00);
				break;
			case "ONEHOURFROMNOW":
				cal.add(Calendar.HOUR, 1);
				break;
			case "THREEHOURSFROMNOW":
				cal.add(Calendar.HOUR, 3);
				break;
			case "SIXHOURSFROMNOW":
				cal.add(Calendar.HOUR, 6);
				break;
			case "TOMORROW":
				cal.add(Calendar.DATE, 1);
				break;
			case "DAYAFTERTOMORROW":
				cal.add(Calendar.DATE, 2);
				break;
			case "NEXTWEEK":
				cal.add(Calendar.DATE, 7);
				break;
			case "NEXTMONTH":
				cal.add(Calendar.MONTH, 1);
				break;
			default : throw new IllegalArgumentException("Not valid startTime: " + date.toUpperCase().trim().replaceAll(" ", "")
			+ ". Valid examples: YESTERDAY, NOW, INFIVEMINUTES, MIDDAY, MIDNIGHT, ONEHOURFROMNOW, THREEHOURSFROMNOW, SIXHOURSFROMNOW," +
					"TOMORROW, DAYAFTERTOMORROW, NEXTWEEK, NEXTMONTH");
		}
		Date eventTime = cal.getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		startTime = dateFormat.format(eventTime);

		logger.info("Event start time: "+startTime);

	}

	public String getDisplayed() {

		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	public String getOffFlag() {
		return offFlag;
	}

	public void setOffFlag(String offFlag) {
		this.offFlag = offFlag;
	}

	public Commentary getCommentary() {
		return commentary;
	}

	public void setCommentary(Commentary commentary) {
		this.commentary = commentary;
	}

	public String getExternal_id() {
		return externalEventId;
	}

	public void setExternal_id(final String externalEventId) {
		this.externalEventId = externalEventId;
	}

	public ArrayList<Market> getMarkets() {
		return markets;
	}

	public void addMarket(Market market) {
		markets.add(market);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getPdsId() {
		return pdsId.isEmpty() ? "OB_EV" + id : pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
		this.id = Integer.parseInt(pdsId.replace(PREFIX,StringUtils.EMPTY));
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public boolean isInPlay() {
		if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_YES) &&
				hasInPlayMarkets())
			return true;

		else if (Timer.getDate(startTime).before(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NA) &&
				hasInPlayMarkets())
			return true;

		else if (Timer.getDate(startTime).before(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_YES) &&
				hasInPlayMarkets())
			return true;

		else
			return false;
	}

	public boolean isPreMatch() {
		if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NO) &&
				hasPreMatchMarkets())
			return true;

		else if (Timer.getDate(startTime).after(Timer.getCurrentDate()) &&
				offFlag.equals(BackOfficeConstants.EVENT_OFF_FLAG_NA) &&
				hasPreMatchMarkets())
			return true;

		else
			return false;

	}

	public boolean hasInPlayMarkets() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_YES))
				return true;

		return false;
	}

	public boolean hasPreMatchMarkets() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_NO))
				return true;

		return false;
	}

	public Market getFirstPreMatchMarket() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_NO))
				return market;

		throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any pre-match market.",
				this.getId()));
	}

	public Market getFirstInPlayMarket() {
		for (Market market : markets)
			if (market.getBir().equalsIgnoreCase(
					BackOfficeConstants.MARKET_BIR_YES))
				return market;

		throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any in-play market.",
				this.getId()));
	}

	public Market getFirstMarket() {
		if(!getStartTime().isEmpty() && isPreMatch())
			return getFirstPreMatchMarket();
		else if(!getStartTime().isEmpty() && isInPlay())
			return getFirstInPlayMarket();
		else if(!markets.isEmpty())
			return markets.get(0);
		else
			throw new IllegalArgumentException(String.format(
					"The event %s doesn't contain any markets.",
					this.getId()));
	}

	public Market getMarket(String marketName) {
		for (Market market : markets)
			if (market.getName().equalsIgnoreCase(marketName))
				return market;

		throw new IllegalArgumentException(String.format(
				"The event %s doesn't contain any market called %s",
				this.getId(), marketName));
	}

	public boolean isEventActive() {
		return this.getStatus()== null || this.getStatus().isEmpty() || this.getStatus().equalsIgnoreCase("A");
	}
}
