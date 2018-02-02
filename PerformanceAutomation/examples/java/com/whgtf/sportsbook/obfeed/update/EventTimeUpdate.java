package com.whgtf.sportsbook.obfeed.update;

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.obfeed.request.Request;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;


public class EventTimeUpdate {
	
	public void setEventYesterday(Map<String, String> eventsIdMap, String startTime, String offFlag, String displayed, String env) throws IOException, SAXException, ParserConfigurationException
	{
		PropArgs props = new PropArgs(env);
	StringBuilder sb = new StringBuilder();
	sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
	sb.append("<oxiFeedRequest version=\"1.0\">\r\n");
	sb.append("\t");
	sb.append("<auth username=\""+props.getOpenbetUsername()+"\" password=\""+props.getOpenbetPassword()+"\"/>\r\n");
	for (int a = 0; a<=eventsIdMap.size(); a++) {
		sb.append("\t");
		sb.append("<eventUpdate>\r\n");
		sb.append("\t\t");
		sb.append("<eventId>\r\n");
		sb.append("\t\t\t");
		sb.append("<openbetId>"+ eventsIdMap.get("id"+a)+"</openbetId>\r\n");
		sb.append("\t\t");
		sb.append("</eventId>\r\n");
		sb.append("\t\t");
		sb.append("<startTime isOff="+"\"" + offFlag + "\">"+startTime+"</startTime>\r\n");
		sb.append("\t\t");
		sb.append("<channels></channels>\r\n");
		sb.append("\t\t");
		sb.append("<display displayed=\""+displayed+"\" order=\""+"1"+"\"/>\r\n");
		sb.append("\t");
		sb.append("</eventUpdate>\r\n");
	}
	sb.append("</oxiFeedRequest>");
	String requestBody = sb.toString();
	Request request = new Request();
	request.sendRequest(requestBody, env);
	}
	
	
}
