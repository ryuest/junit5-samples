package com.whgtf.sportsbook.model;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/** 
* Market class to model Market information
*/
public class Market {

	public Integer id;
	
	public String localId;

	public String pdsId;
	
	public String name;
	
	public String displayed;
	
	public Integer order;
	
	public String status;
	
	public String bir;
	
	public String blurb;
	
	public String starting;
	
	public String marketGroup;
	
	public String handicap;

    public String antepost;

    public String startPrice;

    public String livePrice;

    public String guaranteedPrice;

    public String earlyPrices;

    public String eachWay;

    public String eachWayPlaces;

    public String eachWayPlacesAt;

	public boolean forecastAvailable;

	public boolean tricastAvailable;
	
	public List<Selection> selections;

	public String PREFIX = "OB_MA";

	/**
	 * Constructor
	 */
	public Market() {
		this.id = -1;
		this.localId = StringUtils.EMPTY;
		this.pdsId = StringUtils.EMPTY;
		this.name = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.order = 0;
		this.status = StringUtils.EMPTY;
		this.bir = StringUtils.EMPTY;
		this.blurb = StringUtils.EMPTY;
		this.selections = new ArrayList<Selection>();
		this.marketGroup = StringUtils.EMPTY;
		this.handicap = StringUtils.EMPTY;
		this.antepost = StringUtils.EMPTY;
		this.startPrice = StringUtils.EMPTY;
		this.livePrice = StringUtils.EMPTY;
		this.guaranteedPrice = StringUtils.EMPTY;
		this.earlyPrices = StringUtils.EMPTY;
		this.eachWay = StringUtils.EMPTY;
		this.eachWayPlaces = StringUtils.EMPTY;
		this.eachWayPlacesAt = StringUtils.EMPTY;
	}

	/**
	 * Constructor with parameters
	 * @param id market id
	 * @param marketName market name
	 */
	public Market(final String id,final String marketName) {
		this.id = Integer.parseInt(id.replace(PREFIX,""));
		this.localId = StringUtils.EMPTY;
		this.pdsId = id;
		this.name = marketName;
		this.displayed = StringUtils.EMPTY;
		this.order = 0;
		this.status = StringUtils.EMPTY;
		this.bir = StringUtils.EMPTY;
		this.blurb = StringUtils.EMPTY;
		this.selections = new ArrayList<Selection>();
		this.marketGroup = StringUtils.EMPTY;
		this.handicap = StringUtils.EMPTY;
		this.antepost = StringUtils.EMPTY;
		this.startPrice = StringUtils.EMPTY;
		this.livePrice = StringUtils.EMPTY;
		this.guaranteedPrice = StringUtils.EMPTY;
		this.earlyPrices = StringUtils.EMPTY;
		this.eachWay = StringUtils.EMPTY;
		this.eachWayPlaces = StringUtils.EMPTY;
		this.eachWayPlacesAt = StringUtils.EMPTY;
	}

	/**
	 * Constructor with parameter
	 * @param id market id
	 */
	public Market(String id) {
		this.id = Integer.parseInt(id.replace(PREFIX,""));
		this.localId = StringUtils.EMPTY;
		this.pdsId = id;
		this.name = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.order = 0;
		this.status = StringUtils.EMPTY;
		this.bir = StringUtils.EMPTY;
		this.blurb = StringUtils.EMPTY;
		this.selections = new ArrayList<Selection>();
		this.marketGroup = StringUtils.EMPTY;;
		this.handicap = StringUtils.EMPTY;
        this.antepost = StringUtils.EMPTY;
        this.startPrice = StringUtils.EMPTY;
        this.livePrice = StringUtils.EMPTY;
        this.guaranteedPrice = StringUtils.EMPTY;
        this.earlyPrices = StringUtils.EMPTY;
        this.eachWay = StringUtils.EMPTY;
        this.eachWayPlaces = StringUtils.EMPTY;
        this.eachWayPlacesAt = StringUtils.EMPTY;

	}

	/**
	 * Return the market id
	 * @return the market id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
		this.pdsId = PREFIX.concat(String.valueOf(id));
	}

	/**
	 * Get market name
	 * @return the name as String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the market name
	 * @param name the new market name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the value of "displayed".
	 * @return the value of displayed
	 */
	public String getDisplayed() {
		return displayed;
	}

	/**
	 * Set displayed value
	 * @param displayed the new displayed value
	 */
	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	/**
	 * Return the status value of the market
	 * @return the value of the market
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status of the market
	 * @param status the new market status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the Bir (bet in run) value of the market
	 * @return the value of the BIR
	 */
	public String getBir() {
		return bir;
	}

	/**
	 * Set the Bir value of the market
	 * @param bir the new bit value for BIR
	 */
	public void setBir(String bir) {
		this.bir = bir;
	}

	/**
	 * Return the Blurb value
	 * @return the blurb value
	 */
	public String getBlurb() {
		return blurb;
	}

	/**
	 * Set the blurb value
	 * @param blurb the new blurb value
	 */
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}

	/**
	 * Get the Selection list in the market
	 * @return a list of {@link Selection} objects
	 */
	public List<Selection> getSelections() {
		return selections;
	}

	/**
	 * Set the selection list of the market
	 * @param selections the new selection list
	 */
	public void setSelections(List<Selection> selections) {
		this.selections = selections;
	}

	/**
	 * Add selection to the market
	 * @param selection a {@link Selection} object
	 */
	public void addSelection(Selection selection) {
		this.selections.add(selection);
	}

	/**
	 * Return the local Id of the market
	 * @return the local Id of the market
	 */
	public String getLocalId() {
		return localId;
	}

	/**
	 * Set the local Id for the market
	 * @param localId the new local Id
	 */
	public void setLocalId(String localId) {
		this.localId = localId;
	}

	/**
	 * Return the Pds id of the market
	 * @return the Pds id
	 */
	public String getPdsId() {
		return pdsId.isEmpty() ? "OB_MA" + id : pdsId;
	}

	/**
	 * Set the Pds id for the market
	 * @param pdsId the new Pds id
	 */
	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
		this.id = Integer.parseInt(pdsId.replace(PREFIX,""));
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = Integer.valueOf(order);
	}
	
	public String getMarketGroup() {
		return marketGroup;
	}

	public void setMarketGroup(String marketGroup) {
		this.marketGroup = marketGroup;
	}

	public String getHandicap() {
		return handicap;
	}

	public void setHandicap(String handicap) {
		this.handicap = handicap;
	}

    public String getAntepost() {
        return antepost;
    }

    public void setAntepost(String antepost) {
        this.antepost = antepost;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getLivePrice() {
        return livePrice;
    }

    public void setLivePrice(String livePrice) {
        this.livePrice = livePrice;
    }

    public String getGuaranteedPrice() {
        return guaranteedPrice;
    }

    public void setGuaranteedPrice(String guaranteedPrice) {
        this.guaranteedPrice = guaranteedPrice;
    }

    public String getEarlyPrices() {
        return earlyPrices;
    }

    public void setEarlyPrices(String earlyPrices) {
        this.earlyPrices = earlyPrices;
    }

    public String getEachWay() {
        return eachWay;
    }

    public void setEachWay(String eachWay) {
        this.eachWay = eachWay;
    }

    public String getEachWayPlaces() {
        return eachWayPlaces;
    }

    public void setEachWayPlaces(String eachWayPlaces) {
        this.eachWayPlaces = eachWayPlaces;
    }

    public String getEachWayPlacesAt() {
        return eachWayPlacesAt;
    }

    public void setEachWayPlacesAt(String eachWayPlacesAt) {
        this.eachWayPlacesAt = eachWayPlacesAt;
    }

	public boolean isForecastAvailable() {
		return forecastAvailable;
	}

	public void setForecastAvailable(boolean forecastAvailable) {
		this.forecastAvailable = forecastAvailable;
	}

	public boolean isTricastAvailable() {
		return tricastAvailable;
	}

	public void setTricastAvailable(boolean tricastAvailable) {
		this.tricastAvailable = tricastAvailable;
	}

	public Selection getFirstSelection() {
		if (!selections.isEmpty())
				return selections.get(0);

		throw new IllegalArgumentException(String.format(
				"The market %s doesn't contain any selection.",
				this.getId()));
	}

    public Selection getSelection(String selectionName) {
        for (Selection selection : selections)
            if (selection.getName().equalsIgnoreCase(selectionName))
                return selection;

        throw new IllegalArgumentException(String.format(
                "The market %s doesn't contain any selection called %s",
                this.getId(), selectionName));
    }

}
