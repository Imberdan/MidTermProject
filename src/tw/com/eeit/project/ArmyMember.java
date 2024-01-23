package tw.com.eeit.project;

import java.io.Serializable;

public class ArmyMember implements Serializable{


	
	public ArmyMember(int id, String bMIRange, String armyType, int number, String per) {
		super();
		Id = id;
		BMIRange = bMIRange;
		ArmyType = armyType;
		Number = number;
		Per = per;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String BMIRange; 
	private String ArmyType;
	private int Number;
	private String Per;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getBMIRange() {
		return BMIRange;
	}
	public void setBMIRange(String bMIRange) {
		BMIRange = bMIRange;
	}
	public String getArmyType() {
		return ArmyType;
	}
	public void setArmyType(String armyType) {
		ArmyType = armyType;
	}
	public int getNumber() {
		return Number;
	}
	public void setNumber(int number) {
		Number = number;
	}
	public String getPer() {
		return Per;
	}
	public void setPer(String per) {
		Per = per;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
