package at.lingu.sqlcompose.source;

/**
 *
 * @author flo
 */
public class Table extends Source {

	private String name;

	public Table(String name) {
		this.name = name;
	}

	public TableAlias alias(String alias) {
		return new TableAlias(this, alias);
	}

	public String getName() {
		return name;
	}

	@Override
	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}

}
