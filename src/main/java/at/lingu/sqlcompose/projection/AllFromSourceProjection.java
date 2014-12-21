package at.lingu.sqlcompose.projection;

import at.lingu.sqlcompose.source.Source;

/**
 * Represents table.* projection.
 *
 * @author flo
 */
public class AllFromSourceProjection extends Projection {

	private Source source;

	public AllFromSourceProjection(Source source) {
		this.source = source;
	}

	@Override
	public void acceptVisitor(ProjectionVisitor visitor) {
		visitor.visit(this);
	}

	public Source getSource() {
		return source;
	}

}
