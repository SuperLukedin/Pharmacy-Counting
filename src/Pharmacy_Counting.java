import java.io.*;
import java.util.List;
public class Pharmacy_Counting {

	public static void main(String[] args) {      
        /*
	The file are read in a while loop by BufferedReader.
	Each line will be tokenized in Process_Line class.
	After all contents of the file are processed and stored in to 
	Drug Objects, "top_cost_drug.txt" will be written by PrintWriter 
	line by line in an order as they are in the ArrayList. 
	*/
	BufferedReader reader;
        PrintWriter writer;
        try {
            File file = new File("../input/itcont.txt");
            reader = new BufferedReader(new FileReader(file));
            String line;
            line = reader.readLine();
            Process_Line processLine  = new Process_Line();
            while ((line = reader.readLine()) != null) {
            	processLine.ingestLine(line);
            }
            reader.close();
            List<Drug> drugList = processLine.sortAndGetDrugs();
            
            writer = new PrintWriter("../output/top_cost_drug.txt");
            writer.println("drug_name,num_prescriber,total_cost");
                        
            for (Drug drug : drugList) {
                writer.println(drug.getDrugName() + "," + drug.getprescribers().size() + "," + drug.getTotalCost());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
