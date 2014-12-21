package at.lingu.sqlcompose;

import at.lingu.sqlcompose.Select;
import at.lingu.sqlcompose.generator.SqlGenerator;
import at.lingu.sqlcompose.generator.SqlResult;
import static at.lingu.sqlcompose.util.Strings.assertEqualIgnoringWhitespace;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Base class for sql tests.
 *
 * @author Florian Fankhauser <flo@lingu.at>
 */
public class BaseSqlTests {

	protected SqlResult sqlResult;

	protected void generateResult(Select select) {
		SqlGenerator generator = new SqlGenerator();
		sqlResult = generator.generate(select);
	}

	protected void assertNoParams() {
		assertEquals(0, sqlResult.getParams().length);
	}

	protected void assertParams(Object... params) {
		assertArrayEquals(params, sqlResult.getParams());
	}

	protected void assertSql(String sql) {
		assertEqualIgnoringWhitespace(sql, sqlResult.getSql());
	}

}
