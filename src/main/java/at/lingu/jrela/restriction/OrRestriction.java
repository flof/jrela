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
public class OrRestriction implements Restriction {

	private List<Restriction> restrictions = new ArrayList<>();

	public OrRestriction(Restriction... restrictions) {
		this.restrictions.addAll(Arrays.asList(restrictions));
	}

}
