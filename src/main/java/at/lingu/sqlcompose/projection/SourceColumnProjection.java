package at.lingu.sqlcompose.projection;

import at.lingu.sqlcompose.source.SourceColumn;

/**
 * Represents projection of a named column of a source.
 *
 * @author flo
 */
public class SourceColumnProjection extends Projection {

	private SourceColumn sourceColumn;

	private String alias;

	private boolean fullQualified;

	/**
	 * Projects the source column without alias.
	 *
	 * @param sourceColumn
	 * @return
	 */
	public static SourceColumnProjection unqualified(SourceColumn sourceColumn) {
		return new SourceColumnProjection(sourceColumn, null, false);
	}

	/**
	 * Project the source column with given alias.
	 *
	 * @param sourceColumn
	 * @param alias
	 * @return
	 */
	public static SourceColumnProjection withAlias(SourceColumn sourceColumn, String alias) {
		return new SourceColumnProjection(sourceColumn, alias, false);
	}

	/**
	 * Projects the source column with an generated alias of the form
	 * source-alias + "_" + column-name. E.g. the column 'id' of the table
	 * 'user' would get an alias 'user_id'. However if the user table had an
	 * alias 'u' the generated alias would be 'u_id'.
	 *
	 * @param sourceColumn
	 * @return
	 */
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
