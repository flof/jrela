Jrela
=====

A SQL generator library for Java.

Jrela lets you compose SQL queries with a simple Java API. 
The goal of jrela is *not* to hide the developer from SQL. 
We believe SQL is good. The only problem with SQL is when you have to
support multiple database systems. Because most of them support a slightly different
dialect of SQL. And this is one of the key features of jrela. It abstracts away these
small differences and provides a unified API for composing SQL queries.

The other key feature of jrela is its composability. You can create whole queries
with a single line of code. Or you can create individual parts of a query
individually and compose them together in any way you like. This means you can
add the individual parts of a query in any order and any number of times. This
is ideal for composing large queries with a lot of conditional parts.

Here's an example. This code...

		Table users = new Table("users");
		Table addresses = new Table("addresses");

		Restriction isAdmin = users.column("role").eq("admin");
		Restriction isActive = users.column("active").eq(true);

		Restriction hasNoEmail = or(
				users.column("email").eq(""),
				users.column("email").eq(null));

		SelectStatement userList = users
				.leftJoin(
						addresses,
						users.column("id").eq(addresses.column("user_id")))
				.whereAny(
						isAdmin,
						and(isActive, hasNoEmail))
				.project(
						users.column("id"),
						users.column("username"),
						addresses.column("city"));

...produces this SQL snippet:

    SELECT u.id, u.username, a.city 
    FROM users 
    LEFT OUTER JOIN addresses 
    ON users.id = addresses.user_id 
    WHERE users.role = ? 
    OR (users.active = ? AND (u.email = ? OR u.email IS NULL))

Jrela was inspired by Arel (https://github.com/rails/arel) and SQLAlchemy (http://www.sqlalchemy.org/).
More information follows. 

What already works:

 - Basic SQL Generation
 - Projection
 - Table aliases in joins and projections
 - Left-joins
 - Self-joins
 - Subselects in joins
 - Some basic restrictions in from-clauses

What's not implemented yet:

 - Support for multiple database systems
