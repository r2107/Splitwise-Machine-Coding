package entities.split;

import entities.User;

public class ExactSplit extends Split {

	public ExactSplit(User user, Double amount) {
		super(user);
		this.amount = amount;
	}
}
