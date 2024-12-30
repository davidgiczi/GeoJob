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
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import hu.david.giczi.mvmxpert.georegister.names.Apr;
import hu.david.giczi.mvmxpert.georegister.names.Aug;
import hu.david.giczi.mvmxpert.georegister.names.Dec;
import hu.david.giczi.mvmxpert.georegister.names.Feb;
import hu.david.giczi.mvmxpert.georegister.names.Jan;
import hu.david.giczi.mvmxpert.georegister.names.Jul;
import hu.david.giczi.mvmxpert.georegister.names.Jun;
import hu.david.giczi.mvmxpert.georegister.names.Mar;
import hu.david.giczi.mvmxpert.georegister.names.May;
import hu.david.giczi.mvmxpert.georegister.names.Nov;
import hu.david.giczi.mvmxpert.georegister.names.Oct;
import hu.david.giczi.mvmxpert.georegister.names.Sep;




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
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("hu-Hu"));
		return getNamesOfTheDay(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)) +  " - " +
				calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.DAY_OF_MONTH) + 
			". " + getTheNameOfDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)) + " - " 
			+ calendar.get(Calendar.WEEK_OF_YEAR) + ". hét " + calendar.get(Calendar.DAY_OF_YEAR) + ". nap";
	}
	
	public static List<String> getRemainingDayValues(){
		List<String> dayValues = new ArrayList<>();
		for (Note note : noteList) {
			String[] parts = note.getId().substring(0, note.getId().indexOf('.')).split("_");
			int year = Integer.parseInt(parts[1]);
			int month = Integer.parseInt(parts[2]);
			int day = Integer.parseInt(parts[3]);
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("hu-Hu"));
			calendar.set(year, month - 1, day);
			dayValues.add(getNamesOfTheDay(month - 1, day) + " - " + year + "." + ( 10 > month ? "0" + month : month) + "." + 
			(10 > day ? "0" + day : day) + ". " + getTheNameOfDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
		}
		return dayValues;
	}
	
	private static String getTheNameOfDayOfWeek(int dayValue) {
		String name = "Szombat";
		switch (dayValue) {
		case 1:
			name = "Vasárnap";
			break;
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
		}
		
		return name;
	}
	
	private static String getNamesOfTheDay(int month, int day) {
		String names;
		
		switch (month) {
		case 0:
			names = Jan.NAMES[day - 1];
			break;
		case 1:
			names = Feb.NAMES[day - 1];
			break;
		case 2:
			names = Mar.NAMES[day - 1];
			break;
		case 3:
			names = Apr.NAMES[day - 1];
			break;
		case 4:
			names = May.NAMES[day - 1];
			break;
		case 5:
			names = Jun.NAMES[day - 1];
			break;
		case 6:
			names = Jul.NAMES[day - 1];
			break;
		case 7:
			names = Aug.NAMES[day - 1];
			break;
		case 8:
			names = Sep.NAMES[day - 1];
			break;
		case 9:
			names = Oct.NAMES[day - 1];
			break;
		case 10:
			names = Nov.NAMES[day - 1];
			break;
		case 11:
			names = Dec.NAMES[day - 1];
			break;
		default:
			names = "Emlékeztető";
		}	
		return names;
	}
	
}
