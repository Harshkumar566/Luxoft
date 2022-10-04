import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ElectricityBoard {

	public void totalBill(List<ElectricityBill> billList) {
		DBHandler db = new DBHandler();

		try (Connection con = db.establishConnection()) {

			PreparedStatement preparedStatement = con
					.prepareStatement("insert into ElectricityBill values(?,?,?,?,?);");

			for (ElectricityBill bill : billList) {

				preparedStatement.setString(1, bill.getConsumerNumber());

				preparedStatement.setString(2, bill.getConsumerName());

				preparedStatement.setString(3, bill.getConsumerAddress());

				preparedStatement.setInt(4, bill.getUnitsConsumed());

				preparedStatement.setFloat(5, (float) bill.getBillAmount());

				preparedStatement.execute();
			}
		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public List<ElectricityBill> generateBill(String filePath) {
		List<ElectricityBill> electricityBills = new ArrayList<>();
		File f = new File(filePath);

		try (BufferedReader br = new BufferedReader(new FileReader(f))) {

			String line = null;

			while ((line = br.readLine()) != null) {
				String records[] = null;
				String consumerNumber = "";
				String consumerName = "";
				String consumerAddress = "";
				int unitsConsumed = 0;

				records = line.split(",");
				consumerNumber = records[0];
				consumerName = records[1];
				consumerAddress = records[2];
				unitsConsumed = Integer.parseInt(records[3]);

				try {
					if (validate(consumerNumber)) {

						ElectricityBill electricityBill = new ElectricityBill();

						electricityBill.setConsumerNumber(consumerNumber);
						electricityBill.setConsumerName(consumerName);
						electricityBill.setConsumerAddress(consumerAddress);
						electricityBill.setUnitsConsumed(unitsConsumed);
						electricityBill.calculateBillAmount();

						electricityBills.add(electricityBill);

					}
				} catch (InvalidConsumerNumberException e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		return electricityBills;
	}

	public boolean validate(String consumerNumber) throws InvalidConsumerNumberException {

		boolean isValid = Pattern.matches("^[0][0-9]{9}", consumerNumber);
		if (isValid==false) {

			throw new InvalidConsumerNumberException("Invalid Consumer Number please enter correct consumer no");
		}
		return true;
	}

}
