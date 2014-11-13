/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.lingu.jrela.source;

import at.lingu.jrela.restriction.Restriction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author flo
 */
public class JoinedSource {

	private Source root;

	private List<Join> joins = new ArrayList<>();

	private Map<Source, Join> sourceToJoinMap = new HashMap<>();

	public JoinedSource(Source root) {
		this.root = root;
	}

	public Join getJoinForSource(Source source) {
		return sourceToJoinMap.get(source);
	}

	public JoinedSource leftJoin(Source source, Restriction restriction) {
		if (sourceToJoinMap.containsKey(source)) {
			throw new IllegalArgumentException(
					"Source already joined. Use an alias to join it again");
		}

		final Join join = new Join(
				this,
				getLastSource(),
				source,
				Join.JoinType.LEFT_OUTER_JOIN,
				restriction);
		joins.add(join);
		sourceToJoinMap.put(source, join);
		return this;
	}

	private Source getLastSource() {
		if (joins.isEmpty()) {
			return root;
		} else {
			return joins.get(joins.size() - 1).getRight();
		}
	}

	public Source getRoot() {
		return root;
	}

	public void acceptVisitor(SourceVisitor visitor) {
		visitor.visit(this);
		root.acceptVisitor(visitor);
		for (Join join : joins) {
			join.acceptVisitor(visitor);
		}
	}
}
