import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JTable;

//Main + Controller
public class VaccineController {

	private VaccineViewBundler viewBundler;	//reference to view inside controller
	private ArrayList<VaccineRecord> vaxRecordList = new ArrayList<VaccineRecord>();

	//this methods sets the viewBundler instantiated in main such that
	//controller has access to all of the view panes, and manipulate accordingly
	public void setBundler(VaccineViewBundler viewBundler) {
		this.viewBundler = viewBundler;
	}

	//method to add new record
	public void addNewRecord(VaccineRecord newRecord)
	{
		//add to array list of persistent vaccine records
		vaxRecordList.add(newRecord);
		// printRecordList();

		//change view of home after button submitted in table format
		viewBundler.getAddData().clearFields();
	}

	//method to add new record from Parser
	public void addNewLoadRecord(VaccineRecord newRecord)
	{
		//add to array list of persistent vaccine records
		vaxRecordList.add(newRecord);
	}

	//method checks if id exists in record list when adding
	public Boolean checkId(String id)
	{
		for(int i = 0; i < vaxRecordList.size(); i++)
			if(vaxRecordList.get(i).getId().equals(id))
				return true;
		return false;
	}

	//method to save current updated record to file by sending jTable into a .csv format
	public void saveData(BufferedWriter bufWrite)
	{
		//retrieve jtable
		JTable updatedTable = viewBundler.getHome().getJTable();

		try {
			//save column names for first row
			for (int i = 0; i < updatedTable.getColumnCount(); i++) {
				if(i != updatedTable.getColumnCount()-1)
					bufWrite.write(updatedTable.getColumnName(i) + ",");
				else
				bufWrite.write(updatedTable.getColumnName(i));
			}
			bufWrite.newLine();

			//nested for loop to iterate through updated jtable
			for(int row = 0; row < updatedTable.getRowCount(); row++)
			{
				for(int col = 0; col < updatedTable.getColumnCount(); col++)
				{	//condition eliminates last comma
					if((col != updatedTable.getColumnCount()-1))
					{
						bufWrite.write(updatedTable.getValueAt(row, col).toString() + ",");
					}
					else
						bufWrite.write(updatedTable.getValueAt(row, col).toString());
				}
				bufWrite.newLine();
			}
			bufWrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//method to get record list to populate table
	public ArrayList<VaccineRecord> getRecordList() {
		return vaxRecordList;
	}

	//THIS IS FOR ACCESSING CERTAIN COLUMNS
	private int locationCount(String location){
		LinkedList<String> totalLocation = new LinkedList<String>();
		int count;
		for(int i = 0; i < vaxRecordList.size(); i++){
			String loc = vaxRecordList.get(i).getLocation();
			totalLocation.add(loc);
		}

		return getCount(totalLocation, location);

	}

	private int typeCount(String type){
	LinkedList<String> totalType = new LinkedList<String>();
	for(int i = 0; i < vaxRecordList.size(); i++){
		String loc = vaxRecordList.get(i).getVaxType();
		totalType.add(loc);
	}

	return getCount(totalType, type);
	}

	 /**
	  * linked list of locations with only one instance
	  * @return linked list
	  * @throws Exception
	  */
	 public LinkedList<String> getLocationsInst() throws Exception{
		LinkedList<String> totalLocation = new LinkedList<String>();
		LinkedList<String> instLocation = new LinkedList<String>();
		for(int i = 0; i < vaxRecordList.size(); i++){
			String loc = vaxRecordList.get(i).getLocation();
			totalLocation.add(loc);
			if(!(instLocation.contains(loc))){ //existing locations in file
				instLocation.add(loc);
			}
		}

		return instLocation;
	 }

	 public LinkedList<String> getLocationsTotal() throws Exception{
		LinkedList<String> totalLocation = new LinkedList<String>();
		for(int i = 0; i < vaxRecordList.size(); i++){
			String loc = vaxRecordList.get(i).getLocation();
			totalLocation.add(loc);
		}
		 return totalLocation;
	 }

	 /**
	  * linked list of vaccine type with only one instance
	  * @return linked list
	  * @throws Exception
	  */
	public LinkedList<String> getTypeInst() throws Exception{
		LinkedList<String> totalType = new LinkedList<String>();
		LinkedList<String> instType = new LinkedList<String>();
		for(int i = 0; i < vaxRecordList.size(); i++){
			String type = vaxRecordList.get(i).getVaxType();
			totalType.add(type);
			if(!(instType.contains(type))){ //existing locations in file
				instType.add(type);
			}
		}

		return instType;
	}

	public LinkedList<String> getTypeTotal() throws Exception{
		LinkedList<String> totalType = new LinkedList<String>();
		for(int i = 0; i < vaxRecordList.size(); i++){
			String type = vaxRecordList.get(i).getVaxType();
			totalType.add(type);
		}

		return totalType;
	}

	public LinkedList<String> total(LinkedList<String> total){
		LinkedList<String> totalInstance = new LinkedList<String>();
		return totalInstance;
	}

	private int getCount(LinkedList<String> list, String inst) { //totalLocations, String i
		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			if((list.get(i).equals(inst))){
				count++;
			}
		}
		return count;
	}
}
