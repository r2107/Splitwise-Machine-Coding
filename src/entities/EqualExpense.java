package entities;

import java.util.List;

import entities.split.EqualSplit;
import entities.split.Split;

public class EqualExpense extends Expense {

	public EqualExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean validate() {
		for (Split split : getSplits()) {
            if (!(split instanceof EqualSplit)) {
                return false;
            }
        }

        return true;
	}

}
