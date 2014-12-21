package at.lingu.sqlcompose.projection;

/**
 * Visitor interface
 *
 * @author flo
 */
public interface ProjectionVisitor {

	void visit(AllFromSourceProjection projection);

	void visit(AllProjection projection);

	void visit(SourceColumnProjection projection);

}
