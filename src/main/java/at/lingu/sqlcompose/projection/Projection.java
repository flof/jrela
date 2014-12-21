package at.lingu.sqlcompose.projection;

import at.lingu.sqlcompose.source.Source;

/**
 * Base class for Projections
 *
 * @author flo
 */
public abstract class Projection {

	public static final Projection ALL = new AllProjection();

	public static Projection allFromSource(Source source) {
		return new AllFromSourceProjection(source);
	}

	public abstract void acceptVisitor(ProjectionVisitor visitor);
}
