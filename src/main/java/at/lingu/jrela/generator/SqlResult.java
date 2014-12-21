package at.lingu.jrela.generator;

/**
 *
 * @author flo
 */
public class SqlResult {

	private final String sql;

	private final Object[] params;

	public SqlResult(String sql, Object[] params) {
		this.sql = sql;
		this.params = params;
	}

	public String getSql() {
		return sql;
	}

	public Object[] getParams() {
		return params;
	}

}
