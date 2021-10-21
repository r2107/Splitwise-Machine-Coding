import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entities.User;
import entities.split.EqualSplit;
import entities.split.ExactSplit;
import entities.split.PercentSplit;
import entities.split.Split;
import exceptions.InputException;
import entities.ExpenseType;

public class Driver {

	public static void main(String[] args) {
		ExpenseManager expenseManager = new ExpenseManager();
		
		// Add Users
		expenseManager.add(new User("user1"));
		expenseManager.add(new User("user2"));
		expenseManager.add(new User("user3"));
		expenseManager.add(new User("user4"));
		
		// 4 types of Input:-
		// EXPENSE user1 100 4 user1 user2 user3 user4 EQUAL
		// EXPENSE user1 200 2 user2 user3 EXACT 130 170
		// SHOW
		// SHOW user1
		
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			String command = scanner.nextLine();
			
			String[] commands = command.split(" ");
			String commandType = commands[0];
			switch (commandType) {
				case "SHOW":
					if (commands.length == 1) {
						expenseManager.showBalances();
					}
					else {
						expenseManager.showBalance(commands[1]);
					}
					break;
				case "EXPENSE":
					String paidBy = commands[1];
					Double amount = Double.parseDouble(commands[2]);
					int noOfUsers = Integer.parseInt(commands[3]);
					String expenseType = commands[4 + noOfUsers];
					List<Split> splits = new ArrayList();
					switch (expenseType) {
						case "EQUAL":
							for (int i = 0; i < noOfUsers; i++) {
								splits.add(new EqualSplit(
										expenseManager.userMap.get(
												commands[4 + i])));
							}
							try {
								expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits);
							} catch (InputException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "EXACT":
							for (int i = 0; i < noOfUsers; i++) {
								Double splitAmount = Double.parseDouble(commands[5 + noOfUsers + i]);
								splits.add(new ExactSplit(
										expenseManager.userMap.get(
												commands[4 + i]), splitAmount));
							}
							try {
								expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits);
							} catch (InputException e) {
								System.out.println(e.getMessage());
							}
							break;
						case "PERCENT":
							for (int i = 0; i < noOfUsers; i++) {
								Double splitPercent = Double.parseDouble(commands[5 + noOfUsers + i]);
								splits.add(new PercentSplit(
										expenseManager.userMap.get(
												commands[4 + i]), splitPercent));
							}
							try {
								expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits);
							} catch (InputException e) {
								e.getMessage();
							}
							break;
					}
					break;		
			}
		}
		
	}
}
