import java.util.List;

import entities.EqualExpense;
import entities.ExactExpense;
import entities.Expense;
import entities.ExpenseType;
import entities.PercentExpense;
import entities.User;
import entities.split.PercentSplit;
import entities.split.Split;
import exceptions.InputException;

public class ExpenseService {

	public static Expense createExpense(ExpenseType expenseType, 
			Double amount, User paidBy, List<Split> splits) 
					throws InputException {
		switch (expenseType) {
			case EQUAL:
				int totalSplits = splits.size();
				double splitAmount = ((double) Math.round(amount * 100 / totalSplits)) / 100.0;
				for (Split split: splits) {
					split.setAmount(splitAmount);
				}
				splits.get(0).setAmount(splitAmount + (amount - splitAmount * totalSplits));
				return new EqualExpense(amount, paidBy, splits);
				
			case EXACT:
				ExactExpense exactExpense = new ExactExpense(amount, paidBy, splits);
				exactExpense.validate();
				return exactExpense;
			case PERCENT:
				
				for (Split split: splits) {
					PercentSplit percentSplit = (PercentSplit) split;
					double splitAmount1 = (amount * percentSplit.getPercent()) / 100;
					split.setAmount(splitAmount1);
				}
				return new PercentExpense(amount, paidBy, splits);
				
			default:
				return null;
			
		}
	}

}
