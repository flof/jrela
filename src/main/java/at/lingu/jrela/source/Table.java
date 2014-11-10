/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

/**
 *
 * @author flo
 */
public class Table extends Source {

	public Table(String name) {
		this.name = name;
	}

	public AliasedTable alias(String alias) {
		return new AliasedTable(this, alias);
	}
}
