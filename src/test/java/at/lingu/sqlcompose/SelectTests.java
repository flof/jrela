package at.lingu.sqlcompose;

import at.lingu.sqlcompose.projection.Projection;
import at.lingu.sqlcompose.source.Table;
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

	@Test
	public void testSelectFields() {
		Select select = new Select(users)
				.project(
						users.column("id"),
						users.column("last_name"),
						users.column("first_name"));

		generateResult(select);

		assertSql("SELECT id, last_name, first_name FROM users");
		assertNoParams();
	}

	@Test
	public void testSelectFieldsWithAlias() {
		Select select = new Select(users)
				.project(
						users.column("id").as("user_id"),
						users.column("last_name").as("l_name"),
						users.column("first_name").as("f_name"));

		generateResult(select);

		assertSql("SELECT id AS user_id, last_name AS l_name, "
				+ "first_name AS f_name FROM users");
		assertNoParams();
	}
}
