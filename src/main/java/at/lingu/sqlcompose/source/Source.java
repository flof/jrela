package at.lingu.sqlcompose.source;

/**
 *
 * @author flo
 */
public abstract class Source {

	public abstract void acceptVisitor(SourceVisitor visitor);

	public SourceColumn column(String name) {
		return new SourceColumn(this, name);
	}
}
