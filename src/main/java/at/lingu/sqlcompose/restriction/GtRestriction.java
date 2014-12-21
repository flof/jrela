package at.lingu.sqlcompose.restriction;

import at.lingu.sqlcompose.source.SourceColumn;

/**
 *
 * @author flo
 */
public class GtRestriction extends Restriction {

	private SourceColumn left;

	private SourceColumn right;

	@Override
	public void acceptVisitor(RestrictionVisitor visitor) {
		visitor.visit(this);
	}
}
