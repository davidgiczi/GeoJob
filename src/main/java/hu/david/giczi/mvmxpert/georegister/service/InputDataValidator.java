package hu.david.giczi.mvmxpert.georegister.service;

public class InputDataValidator {

	
	public static boolean isValidInputGeoRegistration(GeoRegistration inputGeoReg) {
		
		
		if(!inputGeoReg.getInvestorCompany().isEmpty() && !inputGeoReg.getPlaceOfWork().isEmpty()
				&& !inputGeoReg.getMethod().isEmpty() && !inputGeoReg.getDate().isEmpty() && GeoJob.parseGeoJobDate(inputGeoReg.getDate()) != 0L){
					
					return true;
				}
		
		return false;
	}
	
	
	public static boolean isNumber(String input) {
		
		try {
			
			Integer.parseInt(input);
			
			if(input.isEmpty())
				throw new NumberFormatException();
			
		}
		catch(NumberFormatException e ) {
			
			return false;
		}
		
		return true;
	}
	
	
	public static boolean containRegs(int sizeOfGeoJobList, int recordNumber) {
		
		return recordNumber  >= 1 && recordNumber <= sizeOfGeoJobList;
	}
	
	
}
