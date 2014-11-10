/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.restriction;

import at.lingu.jrela.source.Source;

/**
 *
 * @author flo
 */
public class EqValueRestriction implements Restriction {

	private Source source;

	private String column;

	private Object value;

	public EqValueRestriction(Source source, String column, Object value) {
		this.source = source;
		this.column = column;
		this.value = value;
	}

}
