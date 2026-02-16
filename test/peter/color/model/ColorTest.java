package peter.color.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    private int red = 255;
    private int green = 183;
    private int blue = 3;
    private String hexValue = "#FFB703";
    private Character[] allowedNumbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private Character[] allowedLetters = {'A', 'B', 'C', 'D', 'E', 'F'};

    private Color colorRgb;
    private Color colorHex;

    @BeforeEach
    public void setUp() {
        colorRgb = new Color(red, green, blue);
        colorHex = new Color(hexValue);
    }

    @AfterEach
    public void tearDown() {
        colorRgb = null;
        colorHex = null;
    }

    @Test
    public void testGetRed() {
        assertEquals(red, colorRgb.getRed());
    }

    @Test
    public void testSetRed() {
        colorRgb.setRed(red);
        assertEquals(red, colorRgb.getRed());
    }

    @Test
    public void testGetGreen() {
        assertEquals(green, colorRgb.getGreen());
    }

    @Test
    public void testSetGreen() {
        colorRgb.setGreen(green);
        assertEquals(green, colorRgb.getGreen());
    }

    @Test
    public void testGetBlue() {
        assertEquals(blue, colorRgb.getBlue());
    }

    @Test
    public void testSetBlue() {
        colorRgb.setBlue(blue);
        assertEquals(blue, colorRgb.getBlue());
    }

    @Test
    public void testGetHexValue() {
        assertEquals(hexValue, colorHex.getHexValue());
    }

    @Test
    public void testSetHexValue() {
        colorHex.setHexValue(hexValue);
        assertEquals(hexValue, colorHex.getHexValue());
    }

    @Test
    public void testColorValueIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> colorRgb.setRed(-5));
        assertThrows(IllegalArgumentException.class, () -> colorRgb.setGreen(320));
    }

    @Test
    public void testToString() {
        String expected = "[value=#FFB703, r=255, g=183, b=3]";
        colorRgb.setRed(red);
        colorRgb.setGreen(green);
        colorRgb.setBlue(blue);
        colorRgb.setHexValue(hexValue);

        String colorRgbStr = colorRgb.toString();

        assertEquals(expected, colorRgbStr);
    }

    @Test
    public void testConstructorArgIsString() {
        String arg = hexValue;
        Color color = new Color(hexValue);

        assertInstanceOf(String.class, arg);
        assertInstanceOf(Color.class, color);
    }

    @Test
    public void testConstructorArgIsHexadecimal() {
        String arg = hexValue;

        assertEquals(7, arg.length());
    }

    @Test
    public void testConstructorArgIsNotHexadecimal() {
        String arg = "#FFFFFFFFF";

        assertNotEquals(7, arg.length());
        assertThrows(IllegalArgumentException.class, () -> new Color(arg));
    }

    @Test
    public void testHexadecimalCharIsLetterOrDigit() {
        String arg = hexValue;

        assertAll(arg, () -> {
            for (int i = 1; i < hexValue.length(); i++) {
                assertTrue(Character.isLetterOrDigit(arg.charAt(i)));
            }
        });
    }

    @Test
    public void testHexadecimalCharIsNotLetterOrDigit() {
        String arg = "#FF%!44";

        for (int i = 1; i < arg.length(); i++) {
            assertFalse(Character.isLetterOrDigit(arg.charAt(i)));
        }

        assertThrows(IllegalArgumentException.class, () -> new Color(arg));
    }

    @Test
    public void testHexadecimalStartsWithHashtag() {
        String arg = hexValue;
        char hashtag = '#';

        assertEquals(hashtag, arg.charAt(0));
    }

    @Test
    public void testHexadecimalDoesNotStartWithHashtag() {
        String arg = "FFFFFF";
        char hashtag = '#';

        assertNotEquals(hashtag, arg.charAt(0));
    }

    @Test
    public void testHexadecimalOnlyHasLettersWithUpperCase() {
        String arg = hexValue;
        String expected = "#FFB703";

        assertEquals(expected, arg);
    }

    @Test
    public void testHexadecimalDoesNotOnlyHaveLettersWithUpperCase() {
        String arg = "#ffb703";
        String expected = hexValue;

        assertNotEquals(expected, arg);
    }

    @Test
    public void testHexadecimalContainsCorrectValues() {
        String arg = hexValue;

        assertAll(arg, () -> {
            for (int i = 1; i < arg.length(); i++) {
                int finalI = i;
                if (Character.isDigit(arg.charAt(finalI))) {
                    assertTrue(Arrays.stream(allowedNumbers).anyMatch(numberAsCharacter -> numberAsCharacter.equals(arg.charAt(finalI))));
                }
                if (Character.isLetter(arg.charAt(finalI))) {
                    assertTrue(Arrays.stream(allowedLetters).anyMatch(letterAsCharacter -> letterAsCharacter.equals(arg.charAt(finalI))));
                }
            }
        });
    }

    @Test
    public void testHexadecimalContainsIncorrectValues() {
        String arg = "#GZ89PL";

        assertAll(arg, () -> {
            for (int i = 1; i < arg.length(); i++) {
                int finalI = i;
                if (Character.isLetter(arg.charAt(finalI))) {
                    assertFalse(Arrays.stream(allowedLetters).anyMatch(letterAsCharacter -> letterAsCharacter.equals(arg.charAt(finalI))));
                }
            }
        });
    }
}