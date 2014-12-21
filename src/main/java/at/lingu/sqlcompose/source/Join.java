package at.lingu.sqlcompose.source;

import at.lingu.sqlcompose.restriction.Restriction;

/**
 *
 * @author flo
 */
public class Join {

	public enum JoinType {

		INNER_JOIN,
		LEFT_OUTER_JOIN,
		RIGHT_OUTER_JOIN,
		FULL_OUTER_JOIN,
		CROSS_JOIN
	}

	private JoinedSource container;

	private Source left;

	private Source right;

	private JoinType joinType;

	private Restriction restriction;

	public Join(JoinedSource container, Source left, Source right, JoinType joinType, Restriction restriction) {
		this.container = container;
		this.left = left;
		this.right = right;
		this.joinType = joinType;
		this.restriction = restriction;
	}

	public JoinedSource getContainer() {
		return container;
	}

	public void setContainer(JoinedSource container) {
		this.container = container;
	}

	public Source getLeft() {
		return left;
	}

	public void setLeft(Source left) {
		this.left = left;
	}

	public Source getRight() {
		return right;
	}

	public void setRight(Source right) {
		this.right = right;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	public Restriction getRestriction() {
		return restriction;
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}
}
