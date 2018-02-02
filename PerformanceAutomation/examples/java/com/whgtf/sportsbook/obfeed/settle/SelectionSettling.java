package com.whgtf.sportsbook.obfeed.settle;

import com.whgtf.sportsbook.common.PropArgs;
import com.whgtf.sportsbook.model.ResponseEvent;
import com.whgtf.sportsbook.obfeed.request.Request;
import org.apache.http.client.ClientProtocolException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

public class SelectionSettling{

	public ResponseEvent createRequest(Map<String, String> selectionsIdMap, String env) throws ClientProtocolException, IOException,
			ParserConfigurationException, SAXException {

		PropArgs props = new PropArgs(env);
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		sb.append("<oxiFeedRequest version=\"1.0\">\r\n");
		sb.append("\t");
		sb.append("<auth username=\""+props.getOpenbetUsername()+"\" password=\""+props.getOpenbetPassword()+"\"/>\r\n");
		for (int a=0; a< selectionsIdMap.size(); a++) {
			sb.append("\t");
			sb.append("<selectionSettle>\r\n");
			sb.append("\t\t");
			sb.append("<selectionId>\r\n");
			sb.append("\t\t\t");
			sb.append("<openbetId>"+selectionsIdMap.get("id"+a)+"</openbetId>\r\n");
			sb.append("\t\t");
			sb.append("</selectionId>\r\n");
			sb.append("\t");
			sb.append("</selectionSettle>\r\n");
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

