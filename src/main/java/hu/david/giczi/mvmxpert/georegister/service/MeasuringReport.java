package hu.david.giczi.mvmxpert.georegister.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class MeasuringReport {

	private final List<String> measData;

	public MeasuringReport(String path) {

		measData = new ArrayList<>();
		loadData(path);
	}

	public List<String> getMeasData() {
		return measData;
	}

	private void loadData(String path) {

		try (Stream<String> stream = Files.lines(Paths.get(path))) {

			stream.forEach(row -> measData.add(row));

		} catch (IOException e) {

			System.out.println("Incorrect path and/or file name: \'" + path + "\'");
		}

	}

	public int getNumberOfMeasuredPoint() {

		if (!measData.isEmpty() && measData.get(0).endsWith("MagKül")) {

			return measData.size() - 1;
		}

		return measData.size();
	}

	public String getStartTimeOfMeasuring() {

		String[] data;

		if (!measData.isEmpty()) {

			if (measData.get(0).endsWith("MagKül")) {
				data = measData.get(1).split("\t");
			} else {
				data = measData.get(0).split("\t");
			}

			if (data.length > 0) {
				return data[14];
			}
		}
		return "-";
	}

	public String getStopTimeOfMeasuring() {

		if (!measData.isEmpty()) {

			String[] data = measData.get(measData.size() - 1).split("\t");

			if (data.length  > 0) {
				return data[14];
			}
		}
		return "-";
	}

	public String getDurationOfMeasuring() {

		String startTime = getStartTimeOfMeasuring();
		String stopTime = getStopTimeOfMeasuring();

		if (!("-".equals(startTime) || "-".equals(stopTime))) {

			long diff = getDifferenceOfTime(startTime, stopTime);

			return cerateFormatOfDifferenceOfTime(diff);
		}

		return "-";
	}

	private long getDifferenceOfTime(String startTime, String stopTime)  {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date start = new Date();
		Date stop = new Date();
		
		try {
			start = sdf.parse(startTime);
			stop = sdf.parse(stopTime);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		

		return stop.getTime() - start.getTime();

	}

	private String cerateFormatOfDifferenceOfTime(long diff) {

		diff /= 1000;

		int h = (int) (diff / (3600));
		int m = (int) ((diff - (h * 3600)) / 60);
		int s = (int) (diff - (h * 3600) - m * 60);

		return String.format("%02d:%02d:%02d", h, m, s);

	}

	public String getTheLongestMeasuredDistance() {

		String[] data;
		List<Double> coords = new ArrayList<>();

		for (int i = 0; i < measData.size(); i++) {

			if (!measData.get(i).endsWith("MagKül")) {

				data = measData.get(i).split("\t");

				coords.add(getXCoord(data));
				coords.add(getYCoord(data));
				coords.add(getZCoord(data));
			}

		}

		if (coords.size() > 3 && coords.size() % 3 == 0) {

			return getTheLongestDistance(coords);
		}

		return "-";
	}

	private double getXCoord(String[] data) {

		return Double.parseDouble(data[1]);
	}

	private double getYCoord(String[] data) {

		return Double.parseDouble(data[2]);
	}

	private double getZCoord(String[] data) {

		return Double.parseDouble(data[3]);
	}

	private String getTheLongestDistance(List<Double> coords) {

		List<Double> x_coords = IntStream.range(0, coords.size()).filter(i -> i % 3 == 0).mapToObj(i -> coords.get(i))
				.collect(Collectors.toList());

		List<Double> y_coords = IntStream.range(0, coords.size()).filter(i -> i % 3 == 1).mapToObj(i -> coords.get(i))
				.collect(Collectors.toList());

		List<Double> z_coords = IntStream.range(0, coords.size()).filter(i -> i % 3 == 2).mapToObj(i -> coords.get(i))
				.collect(Collectors.toList());
		
		List<Point> pointStore = Point.createMeasuredPointStore(x_coords, y_coords, z_coords);
		
		double dist = Point.calcDistanceAmongMeasuredPoints(pointStore);
		

//		double maxDist = Math.sqrt(Math.pow(x_coords.get(0) - x_coords.get(1), 2)
//				+ Math.pow(y_coords.get(0) - y_coords.get(1), 2) + Math.pow(z_coords.get(0) - z_coords.get(1), 2));
//
//		for (int i = 0; i < x_coords.size() - 1; i++) {
//
//			for (int j = i + 1; j < y_coords.size(); j++) {
//
//				if (Math.sqrt(
//						Math.pow(x_coords.get(i) - x_coords.get(j), 2) + Math.pow(y_coords.get(i) - y_coords.get(j), 2)
//								+ Math.pow(z_coords.get(i) - z_coords.get(j), 2)) > maxDist) {
//
//					maxDist = Math.sqrt(Math.pow(x_coords.get(i) - x_coords.get(j), 2)
//							+ Math.pow(y_coords.get(i) - y_coords.get(j), 2)
//							+ Math.pow(z_coords.get(i) - z_coords.get(j), 2));
//
//				}
//			}
//
//		}

		return String.format("%.2f", dist);
	}

	public static Boolean createCoordReport(String root, String jobNumber , String place, String method_date) {
		
		String path = root +"\\" + jobNumber + "\\" + place + "\\" + method_date + "\\projekt\\coords.txt";
		
		File file =new File (path); 

		if (file.exists() && !new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + "_kit.txt").exists()) {
			
		createCoordsFile(dataConverter(readData(file)), file);
		return true;
		}
		
		return false;
	}

	
	private static List<String> readData(File file){
		
		List<String> rawData = new ArrayList<>();
		
		try(BufferedReader reader=new BufferedReader(new FileReader(file))) {
				
			String row;
			
			while((row = reader.readLine()) != null) {
			
					rawData.add(row);
				
			}
				
		} catch (IOException e) {
			
			System.out.println("Incorrect path and/or file name: \'" + file.getAbsolutePath() + "\'");
		}
		
		return rawData;
	}
	
	private static List<String> dataConverter(List<String> rawData) {
		
		List<String> resultData = new ArrayList<>();
		int numberOfPoint = 1;
		
		for (String row : rawData) {
			
			String dataLine = "";

			if (row.trim().startsWith("pont,")) {

				dataLine = row;

				String[] data = dataLine.trim().split(" ");

				String result = (numberOfPoint++) + "\t" + data[1].substring(2) + "\t" + data[3].substring(2) + "\t"
						+ data[data.length - 1];

				resultData.add(result);
			}

		}
		
		return resultData;
	}
	
	
	private static void createCoordsFile(List<String> resultData, File file) {
		
			
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(
				new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4) + "_kit.txt")))){
			
			for (String row : resultData) {
				
				writer.write(row);
				writer.newLine();
				
			}
			
		} catch (IOException e) {
			
			System.out.println("Incorrect path and/or file name: \'" + file.getAbsolutePath() + "\'");
		}
		
		
	}
	

}