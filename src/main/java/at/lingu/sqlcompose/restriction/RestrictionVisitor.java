package at.lingu.sqlcompose.restriction;

/**
 *
 * @author flo
 */
public interface RestrictionVisitor {

	void visit(AndRestriction andRestriction);

	void visit(OrRestriction orRestriction);

	void visit(EqRestriction eqRestriction);

	void visit(GtEqRestriction gtEqRestriction);

	void visit(GtRestriction gtRestriction);

	void visit(NotEqRestriction notEqRestriction);

	void visit(LtEqRestriction ltEqRestriction);

	void visit(LtRestriction ltRestriction);

}
