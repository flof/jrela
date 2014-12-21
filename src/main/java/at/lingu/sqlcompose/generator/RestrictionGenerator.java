package at.lingu.sqlcompose.generator;

import at.lingu.sqlcompose.restriction.AndRestriction;
import at.lingu.sqlcompose.restriction.EqRestriction;
import at.lingu.sqlcompose.restriction.GtEqRestriction;
import at.lingu.sqlcompose.restriction.GtRestriction;
import at.lingu.sqlcompose.restriction.LtEqRestriction;
import at.lingu.sqlcompose.restriction.LtRestriction;
import at.lingu.sqlcompose.restriction.NotEqRestriction;
import at.lingu.sqlcompose.restriction.OrRestriction;
import at.lingu.sqlcompose.restriction.Restriction;
import at.lingu.sqlcompose.restriction.RestrictionVisitor;
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
public class RestrictionGenerator implements RestrictionVisitor, SourceVisitor {

	private List<String> parts = new ArrayList<>();

	public void generate(Restriction restriction) {
		restriction.acceptVisitor(this);
	}

	public String getSql() {
		return Strings.join(parts, "");
	}

	public boolean isEmpty() {
		return parts.isEmpty();
	}

	@Override
	public void visit(AndRestriction andRestriction) {
		boolean mustClose = false;
		if (andRestriction.getRestrictions().size() > 1 && !parts.isEmpty()) {
			parts.add("(");
			mustClose = true;
		}
		int counter = 0;
		for (Restriction restriction : andRestriction.getRestrictions()) {
			if (counter > 0) {
				parts.add(" AND ");
			}
			restriction.acceptVisitor(this);
			counter++;
		}
		if (mustClose) {
			parts.add(")");
		}
	}

	@Override
	public void visit(OrRestriction orRestriction) {
		boolean mustClose = false;
		if (orRestriction.getRestrictions().size() > 1 && !parts.isEmpty()) {
			parts.add("(");
			mustClose = true;
		}
		int counter = 0;
		for (Restriction restriction : orRestriction.getRestrictions()) {
			if (counter > 0) {
				parts.add(" OR ");
			}
			restriction.acceptVisitor(this);
			counter++;
		}
		if (mustClose) {
			parts.add(")");
		}
	}

	@Override
	public void visit(EqRestriction eqRestriction) {
		eqRestriction.getLeft().acceptVisitor(this);
		if (eqRestriction.getRight().isNull()) {
			parts.add(" IS NULL ");
		} else {
			parts.add(" = ");
			eqRestriction.getRight().acceptVisitor(this);
		}
	}

	@Override
	public void visit(GtEqRestriction gtEqRestriction) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(GtRestriction gtRestriction) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(NotEqRestriction notEqRestriction) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(LtEqRestriction ltEqRestriction) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(LtRestriction ltRestriction) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(JoinedSource joinedSource) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(Join join) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
		parts.add(subselectAlias.getAlias());
	}

	@Override
	public void visit(SourceColumn sourceColumn) {
		sourceColumn.getSource().acceptVisitor(this);
		parts.add(".");
		parts.add(sourceColumn.getColumn());
	}

	@Override
	public void visit(Expression expression) {
		if (expression == null || expression.isNull()) {
			parts.add(" NULL ");
		} else if (expression.isPlainObject()) {
			parts.add(" ? ");
		} else if (expression.isSourceColumn()) {
			expression.getSourceColumn().getSource().acceptVisitor(this);
			parts.add(".");
			parts.add(expression.getSourceColumn().getColumn());
		}
	}

}
