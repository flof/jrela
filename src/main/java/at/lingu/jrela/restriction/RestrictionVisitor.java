/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.restriction;

/**
 *
 * @author flo
 */
public interface RestrictionVisitor {

	void visit(AndRestriction andRestriction);

	void visit(OrRestriction orRestriction);

	void visit(EqRestriction eqRestriction);

	void visit(GtEqRestriction gtEqRestriction);

	void visit(GtRestriction gtRestriction);

	void visit(NotEqRestriction notEqRestriction);

	void visit(LtEqRestriction ltEqRestriction);

	void visit(LtRestriction ltRestriction);

}
