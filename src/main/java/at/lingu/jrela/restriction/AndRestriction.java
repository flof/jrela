/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.restriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author flo
 */
public class AndRestriction implements Restriction {

	private List<Restriction> restrictions = new ArrayList<>();

	public AndRestriction(Restriction... restrictions) {
		this.restrictions.addAll(Arrays.asList(restrictions));
	}

	public AndRestriction add(Restriction... restrictions) {
		this.restrictions.addAll(Arrays.asList(restrictions));
		return this;
	}

}
