/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela;

import at.lingu.jrela.restriction.Restriction;
import at.lingu.jrela.source.AliasedTable;
import at.lingu.jrela.source.Table;
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

		AliasedTable u = userTable.alias("u");
		AliasedTable a = addressTable.alias("a");

		Restriction isActive = u.column("active").eq(true);

		SelectStatement activeUsers = u
				.leftJoin(a, u.column("id").eq(a.column("user_id")))
				.where(isActive)
				.project(u.column("id"), u.column("username"));

		activeUsers.project(a.column("city"));

		System.out.println(activeUsers);
	}
}
