package at.lingu.sqlcompose.restriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author flo
 */
public class OrRestriction extends Restriction {

	private List<Restriction> restrictions = new ArrayList<>();

	public OrRestriction(Restriction... restrictions) {
		this.restrictions.addAll(Arrays.asList(restrictions));
	}

	public List<Restriction> getRestrictions() {
		return restrictions;
	}

	@Override
	public void acceptVisitor(RestrictionVisitor visitor) {
		visitor.visit(this);
	}
}
