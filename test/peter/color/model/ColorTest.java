package peter.color.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest {

    private int red = 255;
    private int green = 183;
    private int blue = 3;
    private String hexValue = "#FFB703";

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
    public void testConstructorArgIsString() {
        String arg = hexValue;
        Color color = new Color(hexValue);

        assertInstanceOf(String.class, arg);
        assertInstanceOf(Color.class, color);
    }

    @Test
    public void testHexValueIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> colorHex.setHexValue("#FFFFFFFFFFFF"));
        assertThrows(IllegalArgumentException.class, () -> colorHex.setHexValue("#FF"));
        assertThrows(IllegalArgumentException.class, () -> colorHex.setHexValue("#GKL967"));
        assertThrows(IllegalArgumentException.class, () -> colorHex.setHexValue("000000"));
    }

    @Test
    public void testToStringWithCorrectValues() {
        String expected = "[value=#FFB703, r=255, g=183, b=3]";
        colorRgb.setRed(red);
        colorRgb.setGreen(green);
        colorRgb.setBlue(blue);
        colorRgb.setHexValue(hexValue);

        String colorRgbStr = colorRgb.toString();

        assertEquals(expected, colorRgbStr);
    }

    @Test
    public void testToStringWithMissingHexadecimal() {
        colorRgb.setRed(12);
        colorRgb.setGreen(20);
        colorRgb.setBlue(200);

        assertThrows(NullPointerException.class, () -> colorRgb.toString());
    }

    @Test
    public void testToStringWithNoValues() {
        assertThrows(IllegalArgumentException.class, () -> colorHex.toString());
    }
}