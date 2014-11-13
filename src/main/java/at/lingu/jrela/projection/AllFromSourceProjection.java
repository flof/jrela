/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

import at.lingu.jrela.source.Source;

/**
 * Represents table.* projection.
 *
 * @author flo
 */
public class AllFromSourceProjection extends Projection {

	private Source source;

	public AllFromSourceProjection(Source source) {
		this.source = source;
	}

	@Override
	public void acceptVisitor(ProjectionVisitor visitor) {
		visitor.visit(this);
	}

	public Source getSource() {
		return source;
	}

}
