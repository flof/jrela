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

	private String name;

	public Table(String name) {
		this.name = name;
	}

	public TableAlias alias(String alias) {
		return new TableAlias(this, alias);
	}

	public String getName() {
		return name;
	}

	@Override
	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}

}
