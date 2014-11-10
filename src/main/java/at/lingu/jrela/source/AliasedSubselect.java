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
public class AliasedSubselect extends Source {

	private SelectStatement subselect;

	public AliasedSubselect(SelectStatement subselect, String alias) {
		this.subselect = subselect;
		this.name = alias;
	}
}
