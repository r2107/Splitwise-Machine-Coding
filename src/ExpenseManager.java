import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Expense;
import entities.ExpenseType;
import entities.User;
import entities.split.Split;
import exceptions.InputException;

public class ExpenseManager {
	
	private List<Expense> expenses;
	private Map<String, Map<String, Double> > expenseSheet;
	
	Map<String, User> userMap;
	
	public ExpenseManager() {
		userMap = new HashMap<String, User>();
		expenses = new ArrayList<Expense>();
		expenseSheet = new HashMap<String, Map<String, Double>>();
	}

	public void add(User user) {
		userMap.put(user.getId(), user);
		expenseSheet.put(user.getId(), new HashMap<String, Double>());
	}

	public void showBalances() {
		boolean isBalances = true;
		
		for (Map.Entry<String, Map<String, Double>> allBalances : expenseSheet.entrySet()) {
            for (Map.Entry<String, Double> userBalance : allBalances.getValue().entrySet()) {
                if (userBalance.getValue() > 0) {
                	isBalances = false;
                    printBalance(allBalances.getKey(), userBalance.getKey(), userBalance.getValue());
                }
            }
        }
		if (isBalances) {
            System.out.println("No balances");
        }
	}

	public void showBalance(String userId) {
		boolean isBalances = true;
		for (Map.Entry<String, Double> userBalance : expenseSheet.get(userId).entrySet()) {
			if (userBalance.getValue() != 0) {
				isBalances = false;
				printBalance(userId, userBalance.getKey(), userBalance.getValue());
			}
		}
		if (isBalances) {
            System.out.println("No balances");
        }
	}
	
	private void printBalance(String user1, String user2, double amount) {
        
        if (amount < 0) {
            System.out.println(user1 + " owes " + user2 + ": " + Math.abs(amount));
        } else if (amount > 0) {
            System.out.println(user2 + " owes " + user1 + ": " + Math.abs(amount));
        }
    }

	public void addExpense(ExpenseType expenseType, Double amount, 
			String paidBy, List<Split> splits) throws InputException {
		
		Expense expense = ExpenseService.createExpense(expenseType, amount, userMap.get(paidBy), splits);
		expenses.add(expense);
		
		for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getId();
            Map<String, Double> balances = expenseSheet.get(paidBy);
            if (!balances.containsKey(paidTo)) {
                balances.put(paidTo, 0.0);
            }
            balances.put(paidTo, balances.get(paidTo) + split.getAmount());

            balances = expenseSheet.get(paidTo);
            if (!balances.containsKey(paidBy)) {
                balances.put(paidBy, 0.0);
            }
            balances.put(paidBy, balances.get(paidBy) - split.getAmount());
        }
		
	}

}
