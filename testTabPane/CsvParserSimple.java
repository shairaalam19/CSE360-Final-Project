import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
//TESTING VSC
public class CsvParserSimple {

	private VaccineViewBundler viewBundler;

    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DOUBLE_QUOTES = '"';
    private static final char DEFAULT_QUOTE_CHAR = DOUBLE_QUOTES;
    private static final String NEW_LINE = "\n";

    private boolean isMultiLine = false;
    private String pendingField = "";
    private String[] pendingFieldLine = new String[]{};
    
    private List<String[]> result;
    
    LinkedList<String> one_inst_locations = new LinkedList<String>();
    LinkedList<String> total_locations = new LinkedList<String>();
    LinkedList<String> one_inst_type = new LinkedList<String>();
    LinkedList<String> total_type = new LinkedList<String>();
    
    public CsvParserSimple() {
    	
    }
    
    public void parse(File csvFile) throws Exception {
        result = readFile(csvFile, 1);
        getLocationsInst();
        printList(one_inst_locations);
//        printLocations();
//        System.out.println();
//        for(int i = 0; i < total_locations.size(); i++) {
//        	System.out.print(total_locations.get(i) + ",  ");
//        }
//        System.out.println();
//        int count = getCount(total_locations, "Israel");
//        System.out.println(count);
    }
    
    private void printList(LinkedList<String> list) {
    	for(int i = 0; i < list.size(); i++) {
    		System.out.println(list.get(i));
    	}
    }
    
    /**
     * linked list of locations with only one instance 
     * @return linked list 
     */
    public LinkedList<String> getLocationsInst(){
        int listIndex = 0;
        int locationIndex = 0;
        int totalLocationIndex = 0;
        
        //2d array: Result contains numerous "arrays" 
        for (String[] arrays : result) { //arrays = row. result = file 
//            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays)); 
            
            // add to records list
            VaccineRecord newRecord = new VaccineRecord(arrays[0], arrays[1], arrays[2], arrays[3], arrays[4], arrays[5]);	
            viewBundler.getController().addNewLoadRecord(newRecord);
            
           int index = 0;
            for (String item : arrays) {//arrays = row. array = item per column 
            	if(index == 5) {
            		if(!(one_inst_locations.contains(item))){ //existing locations in file 
            			one_inst_locations.add(locationIndex, item);
                		locationIndex++;
            		}
            	}
            }
        }
    	return one_inst_locations;
    }
    
    public LinkedList<String> getLocationsTotal(){
    	 int listIndex = 0;
         int locationIndex = 0;
         int totalLocationIndex = 0;
         
         //2d array: Result contains numerous "arrays" 
         for (String[] arrays : result) { //arrays = row. result = file 
//             System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays)); 
             
             // add to records list
             VaccineRecord newRecord = new VaccineRecord(arrays[0], arrays[1], arrays[2], arrays[3], arrays[4], arrays[5]);	
             viewBundler.getController().addNewLoadRecord(newRecord);
             
            int index = 0;
             for (String item : arrays) {//arrays = row. array = item per column 
             	if(index == 5) {
             		total_locations.add(totalLocationIndex, item);
             		totalLocationIndex++;
             	}
             }
         }
    	return total_locations;
    }
    
    /**
     * linked list of vaccine type with only one instance 
     * @return linked list 
     */
    public LinkedList<String> getTypeInst(){
    	int listIndex = 0;
        int typeIndex = 0;
        int totalTypeIndex = 0;
        
        //2d array: Result contains numerous "arrays" 
        for (String[] arrays : result) { //arrays = row. result = file 
//            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays)); 
            
            // add to records list
            VaccineRecord newRecord = new VaccineRecord(arrays[0], arrays[1], arrays[2], arrays[3], arrays[4], arrays[5]);	
            viewBundler.getController().addNewLoadRecord(newRecord);
            
            int index = 0;
	        for (String item : arrays) {//arrays = row. array = item per column 
	        	if(index == 3) {
	        		if(!(one_inst_type.contains(item))){ //existing locations in file 
	        			one_inst_type.add(typeIndex, item);
	            		typeIndex++;
	        		}
	        	}
	        }
        }
    	return one_inst_type;
    }
    
    public LinkedList<String> getTypeTotal(){
    	int listIndex = 0;
        int typeIndex = 0;
        int totalTypeIndex = 0;
        
        //2d array: Result contains numerous "arrays" 
        for (String[] arrays : result) { //arrays = row. result = file 
//            System.out.println("\nString[" + listIndex++ + "] : " + Arrays.toString(arrays)); 
            
            // add to records list
            VaccineRecord newRecord = new VaccineRecord(arrays[0], arrays[1], arrays[2], arrays[3], arrays[4], arrays[5]);	
            viewBundler.getController().addNewLoadRecord(newRecord);
            
           int index = 0;
            for (String item : arrays) {//arrays = row. array = item per column 
            	if(index == 3) {
            		total_type.add(totalTypeIndex, item);
            		totalTypeIndex++;
            	}
            }
        }
    	return total_type;
    }
    
    /**
     * checks size of file and number of vaccines distributed total 
     * @return
     */
    public int getSize() {
    	return total_locations.size();
    }
    
    public LinkedList<String> total(LinkedList<String> total){
    	LinkedList<String> totalInstance = new LinkedList<String>();
    	return totalInstance;
    }
    
    private int getCount(LinkedList<String> list, String inst) {
    	    int count = 0;
    	    for(int i = 0; i < list.size(); i++) {
    	    	if((list.get(i).equals(inst))){ 
        			count++;
        		}
    	    }
    	    return count;
    }

    public void printLocations() {
    	getLocationsTotal();
    	getLocationsInst();
    	System.out.println("\n------------------------------------\nLocations, instances:\n------------------------------------" );
        for(int i = 0; i < one_inst_locations.size(); i++) {
        	System.out.println(one_inst_locations.get(i) + ", " + getCount(total_locations, one_inst_locations.get(i)));
        }
    }
    
    public void printType() {
    	getTypeTotal();
    	getTypeInst();
        System.out.println("\n------------------------------------\nType, instances:\n------------------------------------" );
        for(int i = 0; i < one_inst_type.size(); i++) {
        	System.out.println(one_inst_type.get(i) + ", " + getCount(total_type, one_inst_type.get(i)));
        }
    }
    
    /*
     * This section are for methods that assist in reading and transposing the csv file 
     */
    public List<String[]> readFile(File csvFile) throws Exception {
        return readFile(csvFile, 0);
    }

    public List<String[]> readFile(File csvFile, int skipLine)
        throws Exception {

        List<String[]> result = new ArrayList<>();
        int indexLine = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            String line;
            while ((line = br.readLine()) != null) {

                if (indexLine++ <= skipLine) {
                    continue;
                }

                String[] csvLineInArray = parseLine(line);

                if (isMultiLine) {
                    pendingFieldLine = joinArrays(pendingFieldLine, csvLineInArray);
                } else {

                    if (pendingFieldLine != null && pendingFieldLine.length > 0) {
                        // joins all fields and add to list
                        result.add(joinArrays(pendingFieldLine, csvLineInArray));
                        pendingFieldLine = new String[]{};
                    } else {
                        // if dun want to support multiline, only this line is required.
                        result.add(csvLineInArray);
                    }

                }


            }
        }

        return result;
    }

    public String[] parseLine(String line) throws Exception {
        return parseLine(line, DEFAULT_SEPARATOR);
    }

    public String[] parseLine(String line, char separator) throws Exception {
        return parse(line, separator, DEFAULT_QUOTE_CHAR).stream().toArray(String[]::new);
    }

    private List<String> parse(String line, char separator, char quoteChar)
        throws Exception {

        List<String> result = new ArrayList<>();

        boolean inQuotes = false;
        boolean isFieldWithEmbeddedDoubleQuotes = false;

        StringBuilder field = new StringBuilder();

        for (char c : line.toCharArray()) {

            if (c == DOUBLE_QUOTES) {               // handle embedded double quotes ""
                if (isFieldWithEmbeddedDoubleQuotes) {

                    if (field.length() > 0) {       // handle for empty field like "",""
                        field.append(DOUBLE_QUOTES);
                        isFieldWithEmbeddedDoubleQuotes = false;
                    }

                } else {
                    isFieldWithEmbeddedDoubleQuotes = true;
                }
            } else {
                isFieldWithEmbeddedDoubleQuotes = false;
            }

            if (isMultiLine) {                      // multiline, add pending from the previous field
                field.append(pendingField).append(NEW_LINE);
                pendingField = "";
                inQuotes = true;
                isMultiLine = false;
            }

            if (c == quoteChar) {
                inQuotes = !inQuotes;
            } else {
                if (c == separator && !inQuotes) {  // if find separator and not in quotes, add field to the list
                    result.add(field.toString());
                    field.setLength(0);             // empty the field and ready for the next
                } else {
                    field.append(c);                // else append the char into a field
                }
            }

        }

        //line done, what to do next?
        if (inQuotes) {
            pendingField = field.toString();        // multiline
            isMultiLine = true;
        } else {
            result.add(field.toString());           // this is the last field
        }

        return result;

    }

    private String[] joinArrays(String[] array1, String[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(String[]::new);
    }
    
	public void setBundler(VaccineViewBundler viewBundler) {
		this.viewBundler = viewBundler;
	}
}
