/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

import at.lingu.jrela.SelectStatement;
import at.lingu.jrela.projection.Projection;
import at.lingu.jrela.restriction.Restriction;

/**
 *
 * @author flo
 */
public abstract class Source {

	public abstract void acceptVisitor(SourceVisitor visitor);

	public SourceColumn column(String name) {
		return new SourceColumn(this, name);
	}

	public SelectStatement where(Restriction... restrictions) {
		return new SelectStatement().from(this).where(restrictions);
	}

	public SelectStatement whereAny(Restriction... restrictions) {
		return new SelectStatement().from(this).whereAny(restrictions);
	}

	public SelectStatement leftJoin(Source source, Restriction restriction) {
		return new SelectStatement().from(this).leftJoin(source, restriction);
	}

	public SelectStatement project(Projection... projections) {
		return new SelectStatement().from(this).project(projections);
	}

	public SelectStatement project(SourceColumn... sourceColumns) {
		return new SelectStatement().from(this).project(sourceColumns);
	}

}
