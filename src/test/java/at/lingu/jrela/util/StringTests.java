package at.lingu.jrela.util;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Florian Fankhauser <flo@lingu.at>
 */
public class StringTests {

	@Test
	public void testIsEqualsIgnoreWhitespace() {
		Assert.assertTrue(Strings.isEqualIgnoringWhitespace("abc", "abc"));
		Assert.assertFalse(Strings.isEqualIgnoringWhitespace("abc", "def"));

		Assert.assertTrue(Strings.isEqualIgnoringWhitespace("abc def", " abc  def  "));
		Assert.assertTrue(Strings.isEqualIgnoringWhitespace("abc def", " abc \n def  "));
	}

	@Test(expected = AssertionError.class)
	public void testAssertEqualIgnoreWhiteSpaceThrowsException() {
		Strings.assertEqualIgnoringWhitespace("abc", "def");
	}

	@Test
	public void testAssertEqualIgnoreWhiteSpaceIsSilent() {
		Strings.assertEqualIgnoringWhitespace("abc def", " abc \n def  ");
	}
}
