package at.lingu.jrela.restriction;

import at.lingu.jrela.source.SourceColumn;

/**
 *
 * @author flo
 */
public class GtEqRestriction extends Restriction {

	private SourceColumn left;

	private SourceColumn right;

	@Override
	public void acceptVisitor(RestrictionVisitor visitor) {
		visitor.visit(this);
	}
}
