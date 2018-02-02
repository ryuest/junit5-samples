package com.whgtf.sportsbook.utils;

import com.whgtf.sportsbook.common.PropArgs;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ResponseFiles {

	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ResponseFiles.class.getName());

	public static ArrayList<String> eventId = new ArrayList<>() ;

	public void splitIds(boolean marketsLive, String env) throws InterruptedException, IOException {
		PropArgs props = new PropArgs(env);
		logger.info("Markets added...");
		ArrayList<String> idArray = null;
		BufferedReader idBufferReader = null;
		BufferedWriter bufferedWriterEvent = null;
		BufferedWriter bufferedWriterMarket = null;
		BufferedWriter bufferedWriterSelection = null;


			idBufferReader = Files.newBufferedReader(Paths.get("EventData" + "/" + env + "/IDList.txt"));

			bufferedWriterEvent = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/EventIDList.txt"),
				StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			if (!marketsLive) {
				 bufferedWriterMarket = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/MarketIDList.txt"),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				 bufferedWriterSelection = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/SelectionIDList.txt"),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			} else {
				 bufferedWriterMarket = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/MarketIDListLive.txt"),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
				 bufferedWriterSelection = Files.newBufferedWriter(Paths.get("EventData" + "/" + env + "/SelectionIDListLive.txt"),
						StandardOpenOption.CREATE, StandardOpenOption.APPEND);
			}

		try {
			idArray = getArrayListFromFile(idBufferReader);
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		String idNumberName;
		assert idArray != null;
		if (!idArray.isEmpty()) {
			for (int i = 0; i < idArray.size(); i++) {
				idNumberName = idArray.get(i);
				int idNumberNameint = 0;
				try {
				idNumberNameint = Integer.parseInt(idNumberName);
				} catch (NumberFormatException e3) {
					System.out.println("ERROR: problem with IDList.txt, ID number is not correct in file");
				}
				try {
					if (idNumberNameint > 5000) {
						if (idNumberNameint < props.getMarketCurrentNumbers()) {
							eventId.add(idNumberName);;
							bufferedWriterEvent.write(idNumberName);
							bufferedWriterEvent.newLine();

						} else if (idNumberNameint < props.getSelectionCurrentNumbers()) {
							bufferedWriterMarket.write(idNumberName);
							bufferedWriterMarket.newLine();

						} else {
							bufferedWriterSelection.write(idNumberName);
							bufferedWriterSelection.newLine();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		try {
			bufferedWriterEvent.close();
			bufferedWriterMarket.close();
			bufferedWriterSelection.close();
			cleanIdList(env);
		} catch (IOException e1) {
			logger.info("Cannot find data files *.txt");
		}

	}

	public void cleanIdList(String env) {
		try {
			FileWriter fileWriter = null;
				fileWriter = new FileWriter(new File(Paths.get("EventData" + "/" + env + "/IDList.txt").toUri()), false);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("");
			bufferedWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public ArrayList<String> getArrayListFromFile(BufferedReader in)
			throws FileNotFoundException {
		Scanner scanner = new Scanner(in);
		ArrayList<String> list = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			list.add(scanner.nextLine());
		}
		scanner.close();
		return list;
	}



}
