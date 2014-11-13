/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

import at.lingu.jrela.source.SourceColumn;

/**
 * Represents projection of a named column of a source.
 *
 * @author flo
 */
public class SourceColumnProjection extends Projection {

	private SourceColumn sourceColumn;

	private String alias;

	private boolean fullQualified;

	public static SourceColumnProjection unqualified(SourceColumn sourceColumn) {
		return new SourceColumnProjection(sourceColumn, null, false);
	}

	public static SourceColumnProjection withAlias(SourceColumn sourceColumn, String alias) {
		return new SourceColumnProjection(sourceColumn, alias, false);
	}

	public static SourceColumnProjection fullQualified(SourceColumn sourceColumn) {
		return new SourceColumnProjection(sourceColumn, null, true);
	}

	protected SourceColumnProjection(SourceColumn sourceColumn, String alias, boolean fullQualified) {
		this.sourceColumn = sourceColumn;
		this.alias = alias;
		this.fullQualified = fullQualified;
	}

	@Override
	public void acceptVisitor(ProjectionVisitor visitor) {
		visitor.visit(this);
	}

	public SourceColumn getSourceColumn() {
		return sourceColumn;
	}

	public String getAlias() {
		return alias;
	}

	public boolean isFullQualified() {
		return fullQualified;
	}

}
