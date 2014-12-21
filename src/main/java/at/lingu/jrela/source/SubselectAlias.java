package at.lingu.jrela.source;

import at.lingu.jrela.Select;

/**
 *
 * @author flo
 */
public class SubselectAlias extends Source {

	private Select subselect;

	private String alias;

	public SubselectAlias(Select subselect, String alias) {
		this.subselect = subselect;
		this.alias = alias;
	}

	public Select getSubselect() {
		return subselect;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}
}
