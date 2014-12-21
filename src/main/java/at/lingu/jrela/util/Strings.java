package at.lingu.jrela.util;

import java.util.Collection;

/**
 *
 * @author flo
 */
public class Strings {

	public static String join(Collection<String> strings, String separator) {
		StringBuilder result = new StringBuilder();
		for (String string : strings) {
			if (result.length() > 0) {
				result.append(separator);
			}
			result.append(string);
		}
		return result.toString();
	}
}
