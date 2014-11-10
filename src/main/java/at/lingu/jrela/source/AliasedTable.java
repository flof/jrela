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
public class AliasedTable extends Source {

	private Table table;

	public AliasedTable(Table table, String alias) {
		this.table = table;
		this.name = alias;
	}

}
