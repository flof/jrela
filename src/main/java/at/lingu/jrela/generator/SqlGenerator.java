/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.generator;

import at.lingu.jrela.AstVisitor;
import at.lingu.jrela.SelectStatement;
import at.lingu.jrela.projection.AllFromSourceProjection;
import at.lingu.jrela.projection.AllProjection;
import at.lingu.jrela.projection.SourceColumnProjection;
import at.lingu.jrela.util.Strings;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flo
 */
public class SqlGenerator implements AstVisitor {

	private List<String> intro = new ArrayList<>();

	private List<String> projections = new ArrayList<>();

	private List<Object> params = new ArrayList<>();

	public SqlResult generate(SelectStatement selectStatement) {
		selectStatement.acceptVisitor(this);

		List<String> parts = new ArrayList<>();
		parts.add(Strings.join(intro, " "));
		parts.add(Strings.join(projections, ", "));

		return new SqlResult(Strings.join(parts, " "), params.toArray());
	}

	@Override
	public void visit(SelectStatement selectStatement) {
		intro.add("SELECT");
	}

	@Override
	public void visit(AllFromSourceProjection projection) {
		projections.add(projection.getSource().getName() + ".*");
	}

	@Override
	public void visit(AllProjection projection) {
		projections.add("*");
	}

	@Override
	public void visit(SourceColumnProjection projection) {
		projections.add(
				projection.getSourceColumn().getSource().getName()
				+ "." + projection.getSourceColumn().getColumn()
				+ " AS " + projection.getAlias());
	}
}
