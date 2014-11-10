/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

import at.lingu.jrela.AstVisitor;
import at.lingu.jrela.source.SourceColumn;

/**
 *
 * @author flo
 */
public class SourceColumnProjection extends Projection {

	private SourceColumn sourceColumn;

	private String alias;

	public SourceColumnProjection(SourceColumn sourceColumn) {
		this(sourceColumn, getDefaultAlias(sourceColumn));
	}

	private static String getDefaultAlias(SourceColumn sourceColumn) {
		return sourceColumn.getSource().getName() + "_" + sourceColumn.getColumn();
	}

	public SourceColumnProjection(SourceColumn sourceColumn, String alias) {
		this.sourceColumn = sourceColumn;
		this.alias = alias;
	}

	@Override
	public void acceptVisitor(AstVisitor visitor) {
		visitor.visit(this);
	}

	public SourceColumn getSourceColumn() {
		return sourceColumn;
	}

	public String getAlias() {
		return alias;
	}

}
