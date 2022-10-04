import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String filePath = "src/ElectricityBill.txt"; // fetching from electrictyBill.txt

		List<ElectricityBill> billList = new ArrayList<>(); // for calculation of bill by calling generate bill

		ElectricityBoard electricityBoard = new ElectricityBoard();

		billList = electricityBoard.generateBill(filePath);

		System.out.println("Bills parsed from file...");
		for (ElectricityBill bill : billList) {

			System.out.println(String.format("id : %s, name: %s, address : %s, units: %d, bill : %f",  //formatting all types to string
					bill.getConsumerNumber(), bill.getConsumerName(), bill.getConsumerAddress(),
					bill.getUnitsConsumed(), bill.getBillAmount()));

		}
		electricityBoard.totalBill(billList); 
		System.out.println("Successfully Inserted");
		sc.close();

	}
}
