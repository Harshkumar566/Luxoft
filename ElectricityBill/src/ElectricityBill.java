public class ElectricityBill {

	private String consumerNumber;
	private String consumerName;
	private String consumerAddress;
	private int unitsConsumed;
	private double billAmount;

	public String getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerAddress() {
		return consumerAddress;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public int getUnitsConsumed() {
		return unitsConsumed;
	}

	public void setUnitsConsumed(int unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public void calculateBillAmount() {
		int tempunits = unitsConsumed;
		double bill = 0;

		if (tempunits <= 100) {
			bill = 0;
		}

		if (tempunits > 100 && tempunits <= 300) {
			bill = (tempunits - 100) * 1.5;
		}
		if (tempunits > 300 && tempunits <= 600) {
			bill = (200 * 1.5 + (tempunits - 300) * 3.5);
		}
		if (tempunits > 600 && tempunits <= 1000) {
			bill = (200 * 1.5 + 300 * 3.5 + (tempunits - 600) * 5.5);
		}
		if (tempunits > 1000) {
			bill = (200 * 1.5 + 300 * 3.5 + 400 * 5.5 + (tempunits - 1000) * 7.5);
		}

		setBillAmount(bill);
	}
}
