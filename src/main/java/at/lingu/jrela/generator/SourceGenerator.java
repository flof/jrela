/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.generator;

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
public class SourceGenerator implements SourceVisitor {

	private List<String> joins = new ArrayList<>();

	public void generate(JoinedSource joinedSource) {
		joinedSource.acceptVisitor(this);
	}

	public String getSql() {
		return Strings.join(joins, " ");
	}

	@Override
	public void visit(JoinedSource joinedSource) {
	}

	@Override
	public void visit(Join join) {
		switch (join.getJoinType()) {
			case INNER_JOIN:
				joins.add("INNER JOIN");
				break;
			case LEFT_OUTER_JOIN:
				joins.add("LEFT OUTER JOIN");
				break;
			case RIGHT_OUTER_JOIN:
				joins.add("RIGHT OUTER JOIN");
				break;
			case FULL_OUTER_JOIN:
				joins.add("FULL OUTER JOIN");
				break;
			case CROSS_JOIN:
				joins.add("CROSS JOIN");
				break;
		}
		join.getRight().acceptVisitor(this);
		joins.add("ON");
		RestrictionGenerator restrictionGenerator = new RestrictionGenerator();
		restrictionGenerator.generate(join.getRestriction());
		joins.add(restrictionGenerator.getSql());
	}

	@Override
	public void visit(Table table) {
		joins.add(table.getName());
	}

	@Override
	public void visit(TableAlias tableAlias) {
		joins.add(tableAlias.getTable().getName() + " AS " + tableAlias.getAlias());
	}

	@Override
	public void visit(SubselectAlias subselectAlias) {
		SqlGenerator generator = new SqlGenerator();	// TODO: Get real SqlGenerator
		SqlResult sql = generator.generate(subselectAlias.getSubselect());
		joins.add("(" + sql.getSql() + ") AS " + subselectAlias.getAlias());
	}

	@Override
	public void visit(SourceColumn sourceColumn) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void visit(Expression expression) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
