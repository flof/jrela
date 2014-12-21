package at.lingu.jrela.generator;

import at.lingu.jrela.Select;
import at.lingu.jrela.util.Strings;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flo
 */
public class SqlGenerator {

	private ProjectionGenerator projectionGenerator = new ProjectionGenerator();

	private SourceGenerator sourceGenerator = new SourceGenerator();

	private RestrictionGenerator restrictionGenerator = new RestrictionGenerator();

	private List<Object> params = new ArrayList<>();

	public SqlResult generate(Select selectStatement) {
		projectionGenerator.generate(selectStatement.getProjections());
		sourceGenerator.generate(selectStatement.getJoinedSource());
		restrictionGenerator.generate(selectStatement.getRestriction());

		List<String> parts = new ArrayList<>();
		parts.add("SELECT");
		parts.add(projectionGenerator.getSql());
		parts.add("FROM");
		parts.add(sourceGenerator.getSql());

		if (!restrictionGenerator.isEmpty()) {
			parts.add("WHERE");
			parts.add(restrictionGenerator.getSql());
		}

		return new SqlResult(Strings.join(parts, " "), params.toArray());
	}
}
