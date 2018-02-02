package com.whgtf.sportsbook.obfeed.settle;

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.obfeed.request.Request;
import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class MarketSettle {

	public ResponseEvent createRequest(Map<String, String> marketsIdMap, String env)
			throws ClientProtocolException, IOException,
			ParserConfigurationException, SAXException {

		PropArgs props = new PropArgs(env);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<oxiFeedRequest version=\"1.0\">\r\n");
		sb.append("\t");
		sb.append("<auth username=\""+props.getOpenbetUsername()+"\" password=\""+props.getOpenbetPassword()+"\"/>\r\n");
		for (int a=0; a< marketsIdMap.size(); a++) {
			sb.append("\t");
			sb.append("<marketSettle>\r\n");
			sb.append("\t\t");
			sb.append("<marketId>\r\n");
			sb.append("\t\t\t");
			sb.append("<openbetId>"+marketsIdMap.get("id"+a)+"</openbetId>\r\n");
			sb.append("\t\t");
			sb.append("</marketId>\r\n");
			sb.append("\t");
			sb.append("</marketSettle>\r\n");
		}
		sb.append("</oxiFeedRequest>");
		ResponseEvent event = null;
		String requestBody = sb.toString();
		Request request = new Request();
		request.sendRequest(requestBody, env);
		event = request.sendRequest(requestBody, env);
		return event;
	}

}
