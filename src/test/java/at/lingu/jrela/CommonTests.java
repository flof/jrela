/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela;

import at.lingu.jrela.generator.SqlGenerator;
import at.lingu.jrela.generator.SqlResult;
import at.lingu.jrela.restriction.Restriction;
import static at.lingu.jrela.restriction.Restriction.and;
import static at.lingu.jrela.restriction.Restriction.or;
import at.lingu.jrela.source.Table;
import at.lingu.jrela.source.TableAlias;
import org.junit.Test;

/**
 *
 * @author flo
 */
public class CommonTests {

	@Test
	public void testSelect() {
		Table userTable = new Table("users");
		Table addressTable = new Table("addresses");

		TableAlias u = userTable.alias("u");
		//Table u = userTable;
		TableAlias a = addressTable.alias("a");

		Restriction isAdmin = u.column("role").eq("admin");

		Restriction isActive = u.column("active").eq(true);

		Restriction hasNoSuspension = or(
				u.column("suspension").eq(""),
				u.column("suspension").eq(null));

		SelectStatement activeUsers = u
				.leftJoin(a, u.column("id").eq(a.column("user_id")))
				.whereAny(
						isAdmin,
						and(isActive, hasNoSuspension))
				.projectFullQualified(u.column("username"))
				.project(u.column("id").as("userid"));

		activeUsers.projectFullQualified(a.column("city"));

		SqlGenerator generator = new SqlGenerator();
		SqlResult result = generator.generate(activeUsers);
		System.out.println(result.getSql());
	}
}
