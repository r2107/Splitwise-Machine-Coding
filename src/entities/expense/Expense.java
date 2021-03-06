package entities.expense;

import java.util.List;

import entities.User;
import entities.split.Split;
import exceptions.InputException;

public abstract class Expense {
	
	private String id;
	private double amount;
	private User paidBy;
	private List<Split> splits;
	
	Expense(double amount, User paidBy, List<Split> splits) {
		this.amount = amount;
		this.paidBy = paidBy;
		this.splits = splits;
	}
	
	public abstract boolean validate() throws InputException;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public User getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	public List<Split> getSplits() {
		return splits;
	}
	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}
	
	
	
}
