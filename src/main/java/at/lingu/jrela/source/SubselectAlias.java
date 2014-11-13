/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

import at.lingu.jrela.SelectStatement;

/**
 *
 * @author flo
 */
public class SubselectAlias extends Source {

	private SelectStatement subselect;

	private String alias;

	public SubselectAlias(SelectStatement subselect, String alias) {
		this.subselect = subselect;
		this.alias = alias;
	}

	public SelectStatement getSubselect() {
		return subselect;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
	}
}
