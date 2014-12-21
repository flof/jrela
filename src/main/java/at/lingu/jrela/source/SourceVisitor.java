package at.lingu.jrela.source;

/**
 *
 * @author flo
 */
public interface SourceVisitor {

	void visit(JoinedSource joinedSource);

	void visit(Join join);

	void visit(Table table);

	void visit(TableAlias tableAlias);

	void visit(SubselectAlias subselectAlias);

	void visit(SourceColumn sourceColumn);

	void visit(Expression expression);
}
