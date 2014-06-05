package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import technical_investing_practice.DayData;


public class PriceParserCSV {


	public static ArrayList<DayData> parse (String symble) {
		DayData dayData;
		Calendar date;
		ArrayList<DayData> loDayData = new ArrayList<DayData>();
		String csvFile = "stockCSVs/" + symble + ".csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		String dateSplitBy = "-";


		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] stockData = line.split(cvsSplitBy);
				if(stockData[1].matches("-?\\d+(\\.\\d+)?")){
					dayData = new DayData();
					date = Calendar.getInstance();
					dayData.setOpen(Double.parseDouble(stockData[1]));
					dayData.setHigh(Double.parseDouble(stockData[2]));
					dayData.setLow(Double.parseDouble(stockData[3]));
					dayData.setClose(Double.parseDouble(stockData[4]));
					dayData.setVolume(Double.parseDouble(stockData[5]));
					dayData.setAdjClose(Double.parseDouble(stockData[6]));

					String[] dateData = stockData[0].split(dateSplitBy);
					date.set(Calendar.YEAR, Integer.parseInt(dateData[0]));
					date.set(Calendar.MONTH, Integer.parseInt(dateData[1])-1);
					date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateData[2]));
					date.set(Calendar.HOUR_OF_DAY, 0);
					date.set(Calendar.MINUTE, 0);
					date.set(Calendar.SECOND, 0);
					date.set(Calendar.MILLISECOND, 0);
					
					dayData.setDate(date);
					
					loDayData.add(0, dayData);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return loDayData;

	}
}
