import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Process_Line {
	/*
	drugList is used for sort later.
	HashMap is used for storing drug name as key, and Drug object as the value, 
	therefore maintain the drug name to be unique.
	*/
	List<Drug> drugList;
    	private Map<String, Drug> map;
	
	public Process_Line() {
		this.drugList = new ArrayList<>();
		this.map = new HashMap<>();	
	}
	
	
	/*
	Line are tokenized by comma, but in case of double quotes that appears 
	to cap complex field such as last name and middle name, or a comma-contained 
	drug name, adjacent tokens inside double quotes are concatenate as a single
	field.
	*/
	public void ingestLine(String line) {
		String[] tokens = line.split(",");  
		List<String> tempTokens = new ArrayList<>();
		
		for (int i = 1; i < tokens.length; i++) {
			if (tokens[i].indexOf('"') != -1) {
				String temp = tokens[i] + "," +tokens[i + 1];
				StringBuilder sb = new StringBuilder(temp);
				sb.deleteCharAt(0);
				sb.deleteCharAt(sb.length() - 1);
				temp = sb.toString();
				tempTokens.add(temp);				
				i++;
			} else {
				tempTokens.add(tokens[i]);
			}
		}
		
	
	/*
	Assign tokens to prescriber, drugName and drugCost respectively.
	And stored into Drug object.
	*/	
		String prescriber = tempTokens.get(0)+ " " + tempTokens.get(1);
		String drugName = tempTokens.get(2);
		double drugCost = Double.parseDouble(tempTokens.get(3));  
        
		if (!map.containsKey(drugName)) {					
			Drug drug = new Drug(drugName, drugCost);
			drug.prescribers.add(prescriber);
			map.put(drugName, drug);
		} else {
			map.get(drugName).prescribers.add(prescriber);
			double updateCost = drugCost + map.get(drugName).getTotalCost();
			map.get(drugName).setTotalCost(updateCost);
		}
	}
	
	/*
	Lastly all drugs are sorted in Comparator.
	Total cost in descending order, 
	then Drug name in ascending order.
	This method can be called in main class and a sorted list will be returned.
	*/
	public List<Drug> sortAndGetDrugs() {
		this.drugList.addAll(this.map.values());
		Comparator<Drug> comparator = Comparator.comparingDouble(Drug::getTotalCost); 
		comparator = comparator.reversed();
		comparator = comparator.thenComparing((o1, o2) -> o1.getDrugName().compareTo(o2.getDrugName()));
        this.drugList.sort(comparator);
        return this.drugList;
	}
}
