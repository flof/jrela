package at.lingu.sqlcompose.source;

/**
 *
 * @author flo
 */
public class Expression {

	private Object obj;

	public Expression(Object obj) {
		if (obj instanceof Expression) {
			Expression expr = (Expression) obj;
			this.obj = expr.obj;
		} else {
			this.obj = obj;
		}
	}

	public boolean isNull() {
		return obj == null;
	}

	public boolean isSourceColumn() {
		return (obj instanceof SourceColumn);
	}

	public boolean isPlainObject() {
		return !isSourceColumn();
	}

	public Object getPlainObject() {
		return obj;
	}

	public SourceColumn getSourceColumn() {
		if (!isSourceColumn()) {
			throw new IllegalStateException("Contained object is no SourceColumn");
		}
		return (SourceColumn) obj;
	}

	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}
}
