package at.lingu.jrela;

import at.lingu.jrela.projection.Projection;
import at.lingu.jrela.source.Table;
import org.junit.Test;

/**
 * Tests for select statements.
 *
 * @author Florian Fankhauser <flo@lingu.at>
 */
public class SelectTests extends BaseSqlTests {

	private Table users = new Table("users");

	@Test
	public void testSelectAllFields() {

		Select select = new Select(users)
				.project(Projection.ALL);

		generateResult(select);

		assertSql("SELECT * FROM users");
		assertNoParams();
	}

}
