/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

import at.lingu.jrela.projection.SourceColumnProjection;
import at.lingu.jrela.restriction.EqRestriction;
import at.lingu.jrela.restriction.Restriction;

/**
 *
 * @author flo
 */
public class SourceColumn {

	private Source source;

	private String column;

	public SourceColumn(Source source, String column) {
		this.source = source;
		this.column = column;
	}

	public Restriction eq(Object value) {
		return new EqRestriction(new Expression(this), new Expression(value));
	}

	public Restriction eq(SourceColumn sourceColumn) {
		return new EqRestriction(new Expression(this), new Expression(sourceColumn));
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}

	public SourceColumnProjection as(String alias) {
		return SourceColumnProjection.withAlias(this, alias);
	}

	public SourceColumnProjection asFullQualifiedAlias() {
		return SourceColumnProjection.fullQualified(this);
	}
}
