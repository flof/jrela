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
public abstract class Restriction {

	public abstract void acceptVisitor(RestrictionVisitor visitor);

	public static Restriction or(Restriction... restrictions) {
		return new OrRestriction(restrictions);
	}

	public static Restriction and(Restriction... restrictions) {
		return new AndRestriction(restrictions);
	}
}
