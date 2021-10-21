	package entities.expense;

import java.util.List;

import entities.User;
import entities.split.PercentSplit;
import entities.split.Split;

public class PercentExpense extends Expense {

	public PercentExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
            if (!(split instanceof PercentSplit)) {
                return false;
            }
        }
		
		double totalPercent = 100.0;
		double splitPercent = 0.0;
		for (Split split : getSplits()) {
			PercentSplit percentSplit = (PercentSplit) split;
			splitPercent += percentSplit.getPercent();
        }
		
		if (totalPercent != splitPercent) {
			return false;
		}
		
		return true;
	}

}
