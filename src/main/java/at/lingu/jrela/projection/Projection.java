package at.lingu.jrela.projection;

import at.lingu.jrela.source.Source;

/**
 * Base class for Projections
 *
 * @author flo
 */
public abstract class Projection {

	private static final Projection ALL = new AllProjection();

	public static Projection all() {
		return ALL;
	}

	public static Projection allFromSource(Source source) {
		return new AllFromSourceProjection(source);
	}

	public abstract void acceptVisitor(ProjectionVisitor visitor);
}
