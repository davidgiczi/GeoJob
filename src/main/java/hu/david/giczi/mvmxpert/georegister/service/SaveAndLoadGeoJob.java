package hu.david.giczi.mvmxpert.georegister.service;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;;

public class SaveAndLoadGeoJob {

	private List<List<String>> stringListOfGeoJob;
	private List<Integer> pattern;
	private JFileChooser jFileChooser;
	private List<GeoJob> inputGeoJobStore;

	public SaveAndLoadGeoJob() {

	}

	public SaveAndLoadGeoJob(List<GeoJob> geoJobs, Map<String, String[]> inputPattern) {
	
		stringListOfGeoJob = transformGeoJobToStringList(geoJobs);
		pattern = transformInputPatternToIntList(inputPattern);

	}

	private List<List<String>> transformGeoJobToStringList(List<GeoJob> geoJobs) {

		stringListOfGeoJob = new ArrayList<>();

		for (GeoJob geoJob : geoJobs) {

			List<String> jobStringStore = new ArrayList<>();

			jobStringStore.add(geoJob.getSettlementNameOfWork());
			jobStringStore.add(geoJob.getPlaceOfWork());
			jobStringStore.add(geoJob.getMethod());
			jobStringStore.add(geoJob.getDate());
			jobStringStore.add(geoJob.getInvestmentManager());
			jobStringStore.add(geoJob.getInvestorCompany());
			jobStringStore.add(geoJob.getComment());
			jobStringStore.add(geoJob.getMeasTime());
			jobStringStore.add(geoJob.getMeasPointNumber());
			jobStringStore.add(geoJob.getMeasDist());
			jobStringStore.add(String.valueOf(geoJob.getIsReady()));

			stringListOfGeoJob.add(jobStringStore);

		}

		return stringListOfGeoJob;
	}

	private List<Integer> transformInputPatternToIntList(Map<String, String[]> inputPattern) {

		pattern = new ArrayList<>();

		for (int i = 0; i < 11; i++) {

			pattern.add(Integer.parseInt(inputPattern.get("labels")[i]));
		}

		return pattern;
	}

	public List<List<String>> getStringListOfGeoJob() {
		return stringListOfGeoJob;
	}

	public List<Integer> getPattern() {
		return pattern;
	}

	public JFileChooser getjFileChooser() {
		return jFileChooser;
	}
	
	
	public List<GeoJob> getInputGeoJobStore() {
		return inputGeoJobStore;
	}

	public void sortOfStringListOfGeoJobByPattern() {

		List<String> temp;

		for (int i = 0; i < stringListOfGeoJob.size(); i++) {

			temp = new ArrayList<>(stringListOfGeoJob.get(i));
			stringListOfGeoJob.get(i).clear();

			for (Integer index : pattern) {

				if (index == -1 || temp.get(index).isEmpty()) {

					stringListOfGeoJob.get(i).add("-");
				} else {

					stringListOfGeoJob.get(i).add(temp.get(index));
				}

			}

			stringListOfGeoJob.set(i, stringListOfGeoJob.get(i));
		}

	}

	public int createFileChooser(String path, boolean readOrWrite) {

		int rows = 0;

		jFileChooser = new JFileChooser(path) {

			private static final long serialVersionUID = 1L;

			@Override
			protected JDialog createDialog(Component parent) throws HeadlessException {

				JDialog dialog = super.createDialog(parent);

				dialog.setAlwaysOnTop(true);

				return dialog;
			}
		};

		jFileChooser.setDialogTitle("Fájl mentés/beolvasás");

		if (readOrWrite)
			jFileChooser.setApproveButtonText("Mentés");
		else
			jFileChooser.setApproveButtonText("Beolvasás");

		jFileChooser.setAcceptAllFileFilterUsed(false);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("txt fájl", "txt");

		jFileChooser.addChoosableFileFilter(filter);

		int returnValue = jFileChooser.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			
			if (readOrWrite)
				rows = writeGeoJobsToFile(jFileChooser.getSelectedFile().getAbsolutePath());
			else
				rows = readGeoJobsFromFile(jFileChooser.getSelectedFile().getAbsolutePath());
			
		}
		else if(returnValue == JFileChooser.CANCEL_OPTION){
			
			rows = -1;
		}

		return rows;
	}

	public int writeGeoJobsToFile(String path) {

		File outputFile = path.endsWith(".txt") ? new File(path) : new File(path + ".txt");

		int rowCounter = 0;

		if (!outputFile.exists()) {

			try (BufferedWriter out = new BufferedWriter(new FileWriter(outputFile))) {

				for (List<String> list : stringListOfGeoJob) {

					String row = createRowFrom(list);
					out.write(row);
					out.newLine();
					rowCounter++;
				}
				
				
				Runtime.getRuntime().exec("notepad.exe " + outputFile.getAbsolutePath());
				
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			rowCounter = -3;
		}

		return rowCounter;
	}

	private String createRowFrom(List<String> geoJobStringList) {

		StringBuilder build = new StringBuilder();

		for (String str : geoJobStringList) {

			build.append(str).append("^");
		}

		return build.toString().substring(0, build.toString().length() - 1);
	}

	private int readGeoJobsFromFile(String path) {

		List<String> inputData = loadData(path);
		List<String> validData = validateInputData(inputData);
		int numberOfGeoJob = createGeoJobFromInputData(validData);
				
		return numberOfGeoJob;
	}

	private List<String> loadData(String path) {

		List<String> measData = new ArrayList<>();

		try (BufferedReader reader=new BufferedReader(new FileReader(new File(path)))) {

			String row;
			
			while((row = reader.readLine()) != null) {
			
					measData.add(row);
				
			}
			
		} catch (IOException e) {

			System.out.println("Incorrect path and/or file name: \'" + path + "\'");
		}

		return measData;
	}

	private List<String> validateInputData(List<String> inputData) {

		return inputData.stream()
				.filter(row -> row.split("\\^").length == 11 && !row.split("\\^")[0].equals("-")
						&& !row.split("\\^")[0].isEmpty() && !row.split("\\^")[1].equals("-")
						&& !row.split("\\^")[1].isEmpty() && !row.split("\\^")[2].equals("-")
						&& !row.split("\\^")[2].isEmpty() && !row.split("\\^")[3].equals("-")
						&& !row.split("\\^")[3].isEmpty() && isValidDate(row.split("\\^")[3]))
				.collect(Collectors.toList());
	}

	
	private int createGeoJobFromInputData(List<String> validData) {
		
		inputGeoJobStore = new ArrayList<>();
		
		for (String str : validData) {
			
		String[] data = str.split("\\^");
		
		GeoJob geoJob = new GeoJob();
		geoJob.setSettlementNameOfWork(data[0]);
		geoJob.setPlaceOfWork(data[1]);
		geoJob.setMethod(data[2]);
		geoJob.setDate(data[3]);
		geoJob.setInvestmentManager(data[4]);
		geoJob.setInvestorCompany(data[5]);
		geoJob.setComment(data[6]);
		geoJob.setMeasTime(data[7]);
		geoJob.setMeasPointNumber(data[8]);
		geoJob.setMeasDist(data[9]);
		geoJob.setIsReady(isBoolean(data[10]) ? true : false);
		
		inputGeoJobStore.add(geoJob);
		
		}
		
		return inputGeoJobStore.size();
	}
	
	private boolean isBoolean(String isReady) {
		
		try {
			
			Boolean.parseBoolean(isReady);
			
		} catch (Exception e) {
			
			return false;
			
		}
		
		return Boolean.parseBoolean(isReady);
	}
	
	
	private boolean isValidDate(String date) {
		
		return GeoJob.parseGeoJobDate(date) != 0L;
	
	}

	public static String formatInputDate(String year, String month, String day) {

		month = Integer.parseInt(month) < 10 ? "0" + month : month;
		day = Integer.parseInt(day) < 10 ? "0" + day : day;

		return year + "-" + month + "-" + day;
	}

}
