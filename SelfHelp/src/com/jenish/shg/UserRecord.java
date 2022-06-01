package com.jenish.shg;

import java.util.*;

public class UserRecord {

	int refId;
	String name;
	double saving;
	double charge;
	double loan;
	double payedLoan;
	double tempayLoan;
	double balLoan;
	double interest;
	double finalAmount;
	Date gmDate;

	public UserRecord(int refId, String name, double saving, double charge, double loan, double payedLoan,
			double tempayLoan, double balLoan, double interest, double finalAmount, Date gmDate) {
		this.refId = refId;
		this.name = name;
		this.saving = saving;
		this.charge = charge;
		this.loan = loan;
		this.payedLoan = payedLoan;
		this.tempayLoan = tempayLoan;
		this.balLoan = balLoan;
		this.interest = interest;
		this.finalAmount = finalAmount;
		this.gmDate = gmDate;
	}

	public UserRecord(String name, double saving, double charge, double loan, double payedLoan, double tempayLoan, double balLoan,
			double interest, double finalAmount, Date gmDate) {

		this.name = name;
		this.saving = saving;
		this.charge = charge;
		this.loan = loan;
		this.payedLoan = payedLoan;
		this.tempayLoan = tempayLoan;
		this.balLoan = balLoan;
		this.interest = interest;
		this.finalAmount = finalAmount;
		this.gmDate = gmDate;
	}

	public String getName() {
		return name;
	}

	public double getTempayLoan() {
		return tempayLoan;
	}

	public double getSaving() {
		return saving;
	}

	public double getCharge() {
		return charge;
	}

	public double getLoan() {
		return loan;
	}

	public double getPayLoan() {
		return payedLoan;
	}

	public double getBalLoan() {
		return balLoan;
	}

	public double getInterest() {
		return interest;
	}

	public double getFinalAmount() {
		return finalAmount;
	}

	public Date getGmDate() {
		return gmDate;
	}

	public int getRefId() {
		return refId;
	}

}
