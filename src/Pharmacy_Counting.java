import java.io.*;
import java.util.List;
public class Pharmacy_Counting {

	public static void main(String[] args) {
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
