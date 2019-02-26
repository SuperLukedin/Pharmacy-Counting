import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Process_Line {
	List<Drug> drugList;
    private Map<String, Drug> map;
	
	public Process_Line() {
		this.drugList = new ArrayList<>();
		this.map = new HashMap<>();	
	}
	
	
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
	
	public List<Drug> sortAndGetDrugs() {
		this.drugList.addAll(this.map.values());
//		Comparator<Drug> comparator = (o1, o2) -> o2.getTotalCost() - o1.getTotalCost();
//        comparator = comparator.thenComparing((o1, o2) -> o1.getDrugName().compareTo(o2.getDrugName()));
		Comparator<Drug> comparator = Comparator.comparingDouble(Drug::getTotalCost); 
		comparator = comparator.reversed();
		comparator = comparator.thenComparing((o1, o2) -> o1.getDrugName().compareTo(o2.getDrugName()));
        this.drugList.sort(comparator);
        return this.drugList;
	}
}
