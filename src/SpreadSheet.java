
import java.util.HashMap;
import java.util.Map;

public class SpreadSheet {
	Map<String, Object> cells = new HashMap<String, Object>();	  
	
	/*Values can be an integer or a formula
	Formula
	 - Always starts with '='
	 - Only supports addition '+'
	 - Refers to other cells by its id - example: A1, B5, C7 etc.
	*/
	public void setCellValue(String cellId, Object value) {
		cells.put(cellId, value);
	}    
    
    public int getCellValue(String cellId) {
    	try {	    	
	    	if (!cells.containsKey(cellId)) return 0;
			String cellValue = cells.get(cellId).toString();    	
			if (!cellValue.substring(0,1).equals("=")) { // an integer			
				return  Integer.parseInt(cellValue);
			} else {  // a formula	
				cellValue = cellValue.substring(1);	 
	    		String[] parts = cellValue.split("\\+");
	    		int sum = 0;
	    		for (int i = 0; i < parts.length; i++) {    			
	    			sum += getCellValue(parts[i]);								
				}
	    		return sum;
			}
    	} catch (NumberFormatException ex) {}
    	return 0;
	}    
    
        
    public static void main(String[] args) {
    	SpreadSheet s = new SpreadSheet();
    	s.setCellValue("A1", 13);
    	s.setCellValue("A2", 14);
    	s.setCellValue("A3", "=A1+A2"); // -> 27
    	s.setCellValue("A4", "=A1+A2+A3"); // -> 54 	
    	
    	System.out.println("A1=" + s.getCellValue("A1"));
    	System.out.println("A2=" + s.getCellValue("A2"));
    	System.out.println("A3=" + s.getCellValue("A3"));    
    	System.out.println("A4=" + s.getCellValue("A4"));      	
    }	
}
