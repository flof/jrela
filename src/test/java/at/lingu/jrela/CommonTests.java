package at.lingu.jrela;

import at.lingu.jrela.generator.SqlGenerator;
import at.lingu.jrela.generator.SqlResult;
import at.lingu.jrela.restriction.Restriction;
import static at.lingu.jrela.restriction.Restriction.all;
import static at.lingu.jrela.restriction.Restriction.any;
import at.lingu.jrela.source.SubselectAlias;
import at.lingu.jrela.source.Table;
import at.lingu.jrela.source.TableAlias;
import org.junit.Test;

/**
 *
 * @author flo
 */
public class CommonTests {

	public enum AdminFilter {

		ONLY_ADMIN,
		ANY
	}

	public enum ActiveUsersFilter {

		ONLY_ACTIVE,
		ANY
	}

	@Test
	public void testSelect() {
		Table userTable = new Table("users");
		Table addressTable = new Table("addresses");

		TableAlias u = userTable.alias("u");
		//Table u = userTable;
		TableAlias a = addressTable.alias("a");

		Restriction isAdmin = u.column("role").eq("admin");

		Restriction isActive = u.column("active").eq(true);

		Restriction hasNoSuspension = any(
				u.column("suspension").eq(""),
				u.column("suspension").eq(null));

		Select activeUsers = new Select(u)
				.leftJoin(a, u.column("id").eq(a.column("user_id")))
				.whereAny(
						isAdmin,
						all(isActive, hasNoSuspension))
				.projectFullQualified(u.column("username"))
				.project(u.column("id").as("userid"));

		activeUsers.projectFullQualified(a.column("city"));

		SqlGenerator generator = new SqlGenerator();
		SqlResult result = generator.generate(activeUsers);

		System.out.println(result.getSql());	// TODO: Make real test of result
	}

	@Test
	public void testSubselectInJoin() {
		Table users = new Table("users");

		SubselectAlias preselection = new SubselectAlias(
				getPreselectedUsers(
						ActiveUsersFilter.ONLY_ACTIVE,
						AdminFilter.ONLY_ADMIN),
				"fu");

		Select detailsQuery = new Select(users)
				.leftJoin(
						preselection,
						users.column("id").eq(preselection.column("id")))
				.project(
						users.column("id"),
						users.column("first_name"),
						users.column("last_name"));

		SqlGenerator generator = new SqlGenerator();
		SqlResult result = generator.generate(detailsQuery);

		System.out.println(result.getSql());	// TODO: Make real test of result
	}

	private Select getPreselectedUsers(
			ActiveUsersFilter activeUsersFilter,
			AdminFilter adminFilter) {

		Table users = new Table("users");

		Select stmt = new Select(users).project(users.column("id"));

		if (adminFilter == AdminFilter.ONLY_ADMIN) {
			stmt.where(users.column("role").eq("admin"));
		}

		if (activeUsersFilter == ActiveUsersFilter.ONLY_ACTIVE) {
			stmt.where(users.column("active").eq(true));
		}

		return stmt;
	}
}
