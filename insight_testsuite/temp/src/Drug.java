import java.util.HashSet;
import java.util.Set;

public class Drug {
	String drugName;
	Set<String> prescribers;
	double totalCost;
	
	public Drug(String drugName, double totalCost) {
		super();
		this.drugName = drugName;
		this.prescribers = new HashSet<>();
		this.totalCost = totalCost;
	}


	public String getDrugName() {
		return drugName;
	}


	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public Set<String> getprescribers() {
		return prescribers;
	}


	public void setprescribers(Set<String> prescribers) {
		this.prescribers = prescribers;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
}
