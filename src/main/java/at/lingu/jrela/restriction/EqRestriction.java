package at.lingu.jrela.restriction;

import at.lingu.jrela.source.Expression;

/**
 *
 * @author flo
 */
public class EqRestriction extends Restriction {

	private Expression left;
	private Expression right;

	public EqRestriction(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	@Override
	public void acceptVisitor(RestrictionVisitor visitor) {
		visitor.visit(this);
	}
}
