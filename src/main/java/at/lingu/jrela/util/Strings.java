package at.lingu.jrela.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

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

	public static boolean isEqualIgnoringWhitespace(String s1, String s2) {
		String[] expectedTokens = getNonWhitespaceTokens(s1);
		String[] actualTokens = getNonWhitespaceTokens(s2);
		return Arrays.equals(expectedTokens, actualTokens);
	}

	private static String[] getNonWhitespaceTokens(String s) {
		String[] tokens = s.split("\\s");
		List<String> result = new ArrayList<>();
		for (int i = 0; i < tokens.length; i++) {
			String arr1 = tokens[i];
			if (StringUtils.isNotBlank(arr1)) {
				result.add(arr1);
			}
		}
		return result.toArray(new String[result.size()]);
	}

	public static void assertEqualIgnoringWhitespace(String expected, String actual) {
		if (!isEqualIgnoringWhitespace(expected, actual)) {
			throw new AssertionError(
					"Strings not equal ignoring whitespace. Expected: "
					+ expected
					+ ", but was: "
					+ actual);
		}
	}
}
