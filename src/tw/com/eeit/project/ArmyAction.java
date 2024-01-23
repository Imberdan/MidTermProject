package tw.com.eeit.project;

import java.util.Scanner;
import java.io.PrintWriter;

public class ArmyAction {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		InterArmy ia = ArmyFactory.createArmyFactory();
		ia.createConn();

		System.out.println("輸入ID:");
		int id = sc.nextInt();

		ArmyMember find = ia.findById(id);

		if (find != null) {
			System.out.println("ID:" + find.getId() + "\nBMI:" + find.getBMIRange() + "\n體位:" + find.getArmyType()
					+ "\n人數:" + find.getNumber() + "\n百分比:" + find.getPer() + "%");

		} else {
			System.out.println("無資料");
		}

		if (find != null) {
			PrintWriter pw = new PrintWriter("C:\\Temp/Army_Data.txt");
			pw.println("ID:" + find.getId() + "\nBMI:" + find.getBMIRange() + "\n體位:" + find.getArmyType() + "\n人數:"
					+ find.getNumber() + "\n百分比:" + find.getPer() + "%");
			pw.close();
		} else {
			
		}
		
		sc.close();

	}

}
