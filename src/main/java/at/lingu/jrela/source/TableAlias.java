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
public class TableAlias extends Source {

	private Table table;

	private String alias;

	public TableAlias(Table table, String alias) {
		this.table = table;
		this.alias = alias;
	}

	public Table getTable() {
		return table;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}
}
