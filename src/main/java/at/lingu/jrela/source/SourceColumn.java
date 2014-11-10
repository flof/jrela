/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

import at.lingu.jrela.restriction.EqValueRestriction;
import at.lingu.jrela.restriction.Restriction;

/**
 *
 * @author flo
 */
public class SourceColumn {

	private Source source;

	private String column;

	public SourceColumn(Source source, String column) {
		this.source = source;
		this.column = column;
	}

	public Restriction eq(Object value) {
		return new EqValueRestriction(source, column, value);
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

}
