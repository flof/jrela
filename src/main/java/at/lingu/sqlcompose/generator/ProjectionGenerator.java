package at.lingu.sqlcompose.generator;

import at.lingu.sqlcompose.projection.AllFromSourceProjection;
import at.lingu.sqlcompose.projection.AllProjection;
import at.lingu.sqlcompose.projection.Projection;
import at.lingu.sqlcompose.projection.ProjectionVisitor;
import at.lingu.sqlcompose.projection.SourceColumnProjection;
import at.lingu.sqlcompose.source.Expression;
import at.lingu.sqlcompose.source.Join;
import at.lingu.sqlcompose.source.JoinedSource;
import at.lingu.sqlcompose.source.SourceColumn;
import at.lingu.sqlcompose.source.SourceVisitor;
import at.lingu.sqlcompose.source.SubselectAlias;
import at.lingu.sqlcompose.source.Table;
import at.lingu.sqlcompose.source.TableAlias;
import at.lingu.sqlcompose.util.Strings;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flo
 */
public class ProjectionGenerator implements ProjectionVisitor, SourceVisitor {

	private List<String> parts = new ArrayList<>();

	void generate(List<Projection> projections) {
		int count = 0;
		for (Projection projection : projections) {
			if (count > 0) {
				this.parts.add(", ");
			}
			projection.acceptVisitor(this);
			count++;
		}
	}

	public String getSql() {
		return Strings.join(parts, "");
	}

	@Override
	public void visit(AllFromSourceProjection projection) {
		projection.getSource().acceptVisitor(this);
		parts.add(".*");
	}

	@Override
	public void visit(AllProjection projection) {
		parts.add("*");
	}

	@Override
	public void visit(SourceColumnProjection projection) {
		projection.getSourceColumn().getSource().acceptVisitor(this);
		parts.add(".");
		parts.add(projection.getSourceColumn().getColumn());
		if (projection.getAlias() != null) {
			parts.add(" AS ");
			parts.add(projection.getAlias());
		} else if (projection.isFullQualified()) {
			parts.add(" AS ");
			projection.getSourceColumn().getSource().acceptVisitor(this);
			parts.add("_");
			parts.add(projection.getSourceColumn().getColumn());
		}
	}

	@Override
	public void visit(Table table) {
		parts.add(table.getName());
	}

	@Override
	public void visit(TableAlias tableAlias) {
		parts.add(tableAlias.getAlias());
	}

	@Override
	public void visit(SubselectAlias subselectAlias) {
		throw new RuntimeException("Subselect in projection not yet supported.");
	}

	@Override
	public void visit(SourceColumn sourceColumn) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(Expression expression) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(JoinedSource joinedSource) {
	}

	@Override
	public void visit(Join join) {
	}
}
