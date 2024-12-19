package hu.david.giczi.mvmxpert.georegister.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;




public class Memento {

	private static List<Note> noteList;
	
	public Memento() {
		Memento.noteList = new ArrayList<>();
		openWeeklyNotes();
		if( noteList.isEmpty() ) {
			return;
		}
		 new MementoDialog( noteList, getMementoDialogTitle() );
	}

	private void openWeeklyNotes() {
		
		List<String> thisWeekNotes = getRemainingDaysOfWeek()
				.stream().map(d -> "Note_" + d.toString().replace("-", "_") + ".txt")
				.collect(Collectors.toList());
		
		File noteFile = new File(GeoJobPropertyStore.URL4);
		File[] notes = noteFile.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String fileName) {
				return thisWeekNotes.stream().filter(noteName -> noteName.equals(fileName) ).findAny().isPresent();
			}
		});
	
		for(File file : notes) {
		
			try(BufferedReader reader = 
					new BufferedReader(
							new InputStreamReader(
									new FileInputStream(file), StandardCharsets.UTF_8))){
			
			Note note = new Note();
			note.setId(file.getName());
			note.setColor(reader.readLine());
			note.setContent(reader.readLine());
			noteList.add(note);
		}
		catch (IOException e) {
		    e.printStackTrace();
		}
	}
		
}
	
	private List<LocalDate> getRemainingDaysOfWeek(){
		 List<LocalDate> remainingDays = new ArrayList<>();
		 LocalDate localDate = LocalDate.now();
	     LocalDate firstDayOfNextWeek = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
	     for(LocalDate date = LocalDate.now(); date.isBefore(firstDayOfNextWeek); date = date.plusDays(1)) {
	    	 remainingDays.add(date);
	     }
		return remainingDays;
	}
	

	private String getMementoDialogTitle() {
		Calendar calendar = Calendar.getInstance();
		return "Emlékeztető - " +  
				calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH) + 
			". " + getTheNameOfDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)) + " - " 
			+ calendar.get(Calendar.WEEK_OF_YEAR) + ". hét " + calendar.get(Calendar.DAY_OF_YEAR) + ". nap az évben";
	}
	
	public static List<String> getRemainingDayValues(){
		List<String> dayValues = new ArrayList<>();
		for (Note note : noteList) {
			String[] parts = note.getId().substring(0, note.getId().indexOf('.')).split("_");
			int year = Integer.parseInt(parts[1]);
			int month = Integer.parseInt(parts[2]) - 1;
			int day = Integer.parseInt(parts[3]);
			GregorianCalendar calendar = new GregorianCalendar(year, month, day);
			dayValues.add(year + "." + month + "." + day + ". - " + getTheNameOfDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
		}
		return dayValues;
	}
	
	private static String getTheNameOfDayOfWeek(int dayValue) {
		String name = "Vasárnap";
		switch (dayValue) {
		case 2:
			name = "Hétfő";
			break;
		case 3:
			name = "Kedd";
			break;
		case 4:
			name = "Szerda";
			break;
		case 5:
			name = "Csütörtök";
			break;
		case 6:
			name = "Péntek";
			break;
		case 7:
			name = "Szombat";
		}
		
		return name;
	}
	
}
