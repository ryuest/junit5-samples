package com.whgtf.sportsbook.obfeed.settle;

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.obfeed.request.Request;
import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class EventSettle{


	public ResponseEvent createRequest(Map<String, String> eventsIdMap, String env) throws IOException, ParserConfigurationException, SAXException
	{
		PropArgs props = new PropArgs(env);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<oxiFeedRequest version=\"1.0\">\r\n");
		sb.append("\t");
		sb.append("<auth username=\""+props.getOpenbetUsername()+"\" password=\""+props.getOpenbetPassword()+"\"/>\r\n");
		sb.append("\t");
		for (int a = 0; a< eventsIdMap.size(); a++) {
			sb.append("<eventSettle>\r\n");
			sb.append("\t\t");
			sb.append("<eventId>\r\n");
			sb.append("\t\t\t");
			sb.append("<openbetId>"+ eventsIdMap.get("id"+a)+"</openbetId>\r\n");
			sb.append("\t\t");
			sb.append("</eventId>\r\n");
			sb.append("\t");
			sb.append("</eventSettle>\r\n");
		}
		sb.append("</oxiFeedRequest>");
		ResponseEvent event = null;
		String requestBody = sb.toString();
		Request request = new Request();
		event = request.sendRequest(requestBody, env);
		return event;
	}

}
