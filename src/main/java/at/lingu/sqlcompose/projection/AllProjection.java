package at.lingu.sqlcompose.projection;

/**
 * Represents star-projection.
 *
 * @author flo
 */
public class AllProjection extends Projection {

	@Override
	public void acceptVisitor(ProjectionVisitor visitor) {
		visitor.visit(this);
	}

}
