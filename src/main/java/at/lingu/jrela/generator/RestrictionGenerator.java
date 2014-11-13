/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.generator;

import at.lingu.jrela.restriction.AndRestriction;
import at.lingu.jrela.restriction.EqRestriction;
import at.lingu.jrela.restriction.GtEqRestriction;
import at.lingu.jrela.restriction.GtRestriction;
import at.lingu.jrela.restriction.LtEqRestriction;
import at.lingu.jrela.restriction.LtRestriction;
import at.lingu.jrela.restriction.NotEqRestriction;
import at.lingu.jrela.restriction.OrRestriction;
import at.lingu.jrela.restriction.Restriction;
import at.lingu.jrela.restriction.RestrictionVisitor;
import at.lingu.jrela.source.Expression;
import at.lingu.jrela.source.Join;
import at.lingu.jrela.source.JoinedSource;
import at.lingu.jrela.source.SourceColumn;
import at.lingu.jrela.source.SourceVisitor;
import at.lingu.jrela.source.SubselectAlias;
import at.lingu.jrela.source.Table;
import at.lingu.jrela.source.TableAlias;
import at.lingu.jrela.util.Strings;
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
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
