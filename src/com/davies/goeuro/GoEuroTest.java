/**
 * 
 */
package com.davies.goeuro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Andrew Davies
 * andrewdavies11@gmail.com
 * 
 * This class will call a GoEuro API to retrieve a JSON string containing a list of Locations 
 * and will then write specific Location fields to a CSV file.
 *  
 */
public class GoEuroTest {

	private static final String MSG_CSV_FILE_WRITING_COMPLETE = "CSV file writing complete.";
	private static final String MSG_WRITING_CSV_FILE = "Writing CSV file: ";
	private static final String MSG_RETRIEVING_JSON_STRING = "Retrieving JSON string";
	private static final String MSG_END = "GoEuro API query to CSV file complete.";
	private static final String MSG_BEGIN = "Begin GoEuro API query to CSV file.";
	private static final String UTF_8 = "UTF-8";
	private static final String CSV_FILENAME = "output.csv";
	private static final String GOEURO_API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println(MSG_BEGIN);
			writeListToCSVFile(getLocationListWithString(args[0]));
			System.out.println(MSG_END);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static CSVPrinter createCSVPrinter() throws IOException {
		CSVFormat format = CSVFormat.RFC4180.withQuote(null); 
		return new CSVPrinter(new PrintWriter(CSV_FILENAME), format);
	}

	private static String getEncodedUrl(String param) throws IOException {
		return StringUtils.replace(GOEURO_API_URL+URLEncoder.encode(param, UTF_8), "+", "%20");
	}

	private static InputStream getInputStreamFromUrl(String url) throws IOException {
		return new URL(url).openStream();
	}

	private static List<Location> getLocationListWithString(String str) throws IOException {
		ObjectMapper mapper = new ObjectMapper(); 
		
		//use this method call to see the JSON returned from the API 
		//printJsonFromUrl(getEncodedUrl(str));
		
		JavaType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Location.class);
		System.out.println(MSG_RETRIEVING_JSON_STRING);
		return mapper.readValue(getInputStreamFromUrl(getEncodedUrl(str)), javaType);
	}

	@SuppressWarnings("unused")
	private static void printJsonFromUrl(String url) throws IOException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName(UTF_8)));
			StringBuilder sb = new StringBuilder();
			int cp;
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
			System.out.println(sb.toString());
		} finally {
			is.close();
		}
	}

	private static void writeListToCSVFile(List<?> list) throws IOException {
		System.out.println(MSG_WRITING_CSV_FILE+CSV_FILENAME);
		CSVPrinter printer = createCSVPrinter();
		printer.printRecord(Location.stringHeader());
		printer.printRecords(list);
		printer.close();
		System.out.println(MSG_CSV_FILE_WRITING_COMPLETE);
	}
}
