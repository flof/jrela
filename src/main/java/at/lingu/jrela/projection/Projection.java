/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

import at.lingu.jrela.source.Source;

/**
 *
 * @author flo
 */
public abstract class Projection {

	private static final Projection ALL = new AllProjection();

	public static Projection all() {
		return ALL;
	}

	public static Projection allFromSource(Source source) {
		return new AllFromSourceProjection(source);
	}

	public abstract void acceptVisitor(ProjectionVisitor visitor);
}
