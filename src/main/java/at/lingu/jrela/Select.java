/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela;

import at.lingu.jrela.projection.Projection;
import at.lingu.jrela.projection.SourceColumnProjection;
import at.lingu.jrela.restriction.AndRestriction;
import at.lingu.jrela.restriction.OrRestriction;
import at.lingu.jrela.restriction.Restriction;
import at.lingu.jrela.source.JoinedSource;
import at.lingu.jrela.source.Source;
import at.lingu.jrela.source.SourceColumn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author flo
 */
public class Select {

	private List<Projection> projections = new ArrayList<>();

	private JoinedSource joinedSource;

	private AndRestriction restriction = new AndRestriction();

	public Select() {
	}

	public Select(Source source) {
		this.joinedSource = new JoinedSource(source);
	}

	public List<Projection> getProjections() {
		return projections;
	}

	public JoinedSource getJoinedSource() {
		return joinedSource;
	}

	public void setJoinedSource(JoinedSource joinedSource) {
		this.joinedSource = joinedSource;
	}

	public AndRestriction getRestriction() {
		return restriction;
	}

	public Select project(Projection... projections) {
		this.projections.addAll(Arrays.asList(projections));
		return this;
	}

	public Select project(SourceColumn... sourceColumns) {
		for (SourceColumn sourceColumn : sourceColumns) {
			projections.add(SourceColumnProjection.unqualified(sourceColumn));
		}
		return this;
	}

	public Select projectFullQualified(SourceColumn... sourceColumns) {
		for (SourceColumn sourceColumn : sourceColumns) {
			projections.add(SourceColumnProjection.fullQualified(sourceColumn));
		}
		return this;
	}

	public Select where(Restriction... restrictions) {
		this.restriction.add(restrictions);
		return this;
	}

	public Select whereAny(Restriction... restrictions) {
		this.restriction.add(new OrRestriction(restrictions));
		return this;
	}

	public Select from(Source source) {
		this.joinedSource = new JoinedSource(source);
		return this;
	}

	public Select leftJoin(Source source, Restriction restriction) {
		this.joinedSource.leftJoin(source, restriction);
		return this;
	}

}
