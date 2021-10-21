package entities;

import java.util.List;

import entities.split.ExactSplit;
import entities.split.Split;
import exceptions.InputException;

public class ExactExpense extends Expense {

	public ExactExpense(double amount, User paidBy, List<Split> splits) {
		super(amount, paidBy, splits);
	}

	@Override
	public boolean validate() throws InputException {
		for (Split split : getSplits()) {
            if (!(split instanceof ExactSplit)) {
                return false;
            }
        }
		
		double totalAmount = getAmount();
		double splitAmount = 0.0;
		for (Split split : getSplits()) {
			splitAmount += split.getAmount();
        }
		
		if (totalAmount != splitAmount) {
			throw new InputException("Split amount doesn't match with total amount");
		}
		
		return true;
	}
	
	

}
