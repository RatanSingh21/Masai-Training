package in.ratansgh.UT_Assignment;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    StringUtils utils;

    @BeforeEach
    void setUp() {
        utils = new StringUtils();
    }

    @ParameterizedTest
    @CsvSource({
            "madam,true",
            "racecar,true",
            "hello,false",
            "'',true",
            "A man a plan a canal Panama,true"
    })
    void testIsPalindrome(String input, boolean expected) {
        assertEquals(expected, utils.isPalindrome(input));
    }

    @Test
    void testReverse() {
        assertEquals("cba", utils.reverse("abc"));
        assertEquals("", utils.reverse(""));
        assertNull(utils.reverse(null));
    }

    @Test
    void testIsBlank() {
        assertTrue(utils.isBlank(null));
        assertTrue(utils.isBlank(""));
        assertTrue(utils.isBlank("   "));
        assertFalse(utils.isBlank("abc"));
    }
}

