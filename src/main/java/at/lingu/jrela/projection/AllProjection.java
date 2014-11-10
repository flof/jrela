/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.projection;

import at.lingu.jrela.AstVisitor;

/**
 *
 * @author flo
 */
public class AllProjection extends Projection {

	@Override
	public void acceptVisitor(AstVisitor visitor) {
		visitor.visit(this);
	}

}
