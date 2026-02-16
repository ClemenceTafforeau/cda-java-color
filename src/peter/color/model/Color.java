package peter.color.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Color {

    private int red = -1;
    private int green = -1;
    private int blue = -1;
    private String hexValue;
    private final Pattern rgbPattern = Pattern.compile("^(25[0-5]|2[0-4]\\d|1\\d\\d|[1-9]?\\d)$");
    private final Pattern hexPattern = Pattern.compile("^#[0-9A-F]{6}$");

    public Color(int pRed, int pGreen, int pBlue) {
        red = verifyColorValue(pRed);
        green = verifyColorValue(pGreen);
        blue = verifyColorValue(pBlue);
    }

    public Color(String pHexValue) {
        hexValue = verifyHexValue(pHexValue);
    }

    public int getRed() {
        return this.red;
    }

    public void setRed(int colorValue) {
        red = verifyColorValue(colorValue);
    }

    public int getGreen() {
        return this.green;
    }

    public void setGreen(int colorValue) {
        green = verifyColorValue(colorValue);
    }

    public int getBlue() {
        return this.blue;
    }

    public void setBlue(int colorValue) {
        blue = verifyColorValue(colorValue);
    }

    public String getHexValue() {
        return this.hexValue;
    }

    public void setHexValue(String colorValue) {
        hexValue = verifyHexValue(colorValue);
    }

    @Override
    public String toString() {
        if (this.hexValue.isEmpty()) {
            throw new NullPointerException("A hex value is required.");
        }

        String hexVal = verifyHexValue(this.hexValue);
        int redVal = verifyColorValue(this.red);
        int greenVal = verifyColorValue(this.green);
        int blueVal = verifyColorValue(this.blue);

        return "[value=" + hexVal + ", r=" + redVal + ", g=" + greenVal + ", b=" + blueVal + "]";
    }

    private String verifyHexValue(String pHexValue) throws IllegalArgumentException {
        Matcher matcher = hexPattern.matcher(pHexValue);

        if (matcher.matches()) {
            return pHexValue;
        } else {
            throw new IllegalArgumentException("The provided argument doesn't match the expected hexadecimal format.");
        }
    };

    private int verifyColorValue(int pColorValue) throws IllegalArgumentException {
        Matcher matcher = rgbPattern.matcher(String.valueOf(pColorValue));

        if (matcher.matches()) {
            return pColorValue;
        } else {
            throw new IllegalArgumentException("The provided argument doesn't match the expected format (integer from 0 to 255.");
        }
    }
}