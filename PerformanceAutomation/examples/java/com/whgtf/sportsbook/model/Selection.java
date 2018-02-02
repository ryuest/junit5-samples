package com.whgtf.sportsbook.model;

import org.apache.commons.lang3.StringUtils;

/**
* @author israel.lozano@williamhill.com
*/
public class Selection {
	
	public String id;

	public String localId;

	public String pdsId;
	
	public String name;
	
	public String price;

	public String status;
	
	public String displayed;
	
	public String type;
	
	public Integer order;

    public String runner_order;

    public String result;

    public String place;

    public String SP;

	private String PREFIX = "OB_OU";
	
	
	public Selection() {
		this.id = "-1";
		this.localId = StringUtils.EMPTY;
		this.pdsId = StringUtils.EMPTY;
		this.name = StringUtils.EMPTY;
		this.price = StringUtils.EMPTY;
		this.status = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.type = StringUtils.EMPTY;
		this.order = 0;
        this.runner_order = StringUtils.EMPTY;
        this.result = StringUtils.EMPTY;
        this.place = StringUtils.EMPTY;
        this.SP = StringUtils.EMPTY;
	}

	public Selection(String id) {
		this.id = id.replace(PREFIX,"");
		this.localId = StringUtils.EMPTY;
		this.pdsId = id;
		this.name = StringUtils.EMPTY;
		this.price = StringUtils.EMPTY;
		this.status = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.type = StringUtils.EMPTY;
		this.order = 0;
		this.runner_order = StringUtils.EMPTY;
		this.result = StringUtils.EMPTY;
		this.place = StringUtils.EMPTY;
		this.SP = StringUtils.EMPTY;
	}

	public Selection(final String id, final String price) {
		this.id = id.replace(PREFIX,"");
		this.localId = StringUtils.EMPTY;
		this.pdsId = id;
		this.name = StringUtils.EMPTY;
		this.price = price;
		this.status = StringUtils.EMPTY;
		this.displayed = StringUtils.EMPTY;
		this.type = StringUtils.EMPTY;
		this.order = 0;
		this.runner_order = StringUtils.EMPTY;
		this.result = StringUtils.EMPTY;
		this.place = StringUtils.EMPTY;
		this.SP = StringUtils.EMPTY;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.pdsId = PREFIX.concat(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = Integer.valueOf(order);
	}

    public String getRunnerOrder() {
        return runner_order;
    }

    public void setRunnerOrder(String runner_order) {
        this.runner_order = runner_order;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSP() {
        return SP;
    }

    public void setSP(String SP) {
        this.SP = SP;
    }

	public String getLocalId() {
		return localId;
	}

	public void setLocalId(String localId) {
		this.localId = localId;
	}

	public String getPdsId() {
		return pdsId.isEmpty() ? "OB_OU" + id : pdsId;
	}

	public void setPdsId(String pdsId) {
		this.pdsId = pdsId;
	}
}
