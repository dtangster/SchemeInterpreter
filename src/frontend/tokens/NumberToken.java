package frontend.tokens;

import frontend.Source;
import frontend.Token;

import java.io.IOException;

import static frontend.TokenType.*;

public class NumberToken extends Token {
    public NumberToken(Source source) throws IOException {
        super(source);
    }

    protected void extract() throws IOException {
        StringBuilder textBuffer = new StringBuilder();  // token's characters
        extractNumber(textBuffer);
        text = textBuffer.toString();
    }

    protected void extractNumber(StringBuilder textBuffer) throws IOException {
        String wholeDigits = null;     // digits before the decimal point
        String fractionDigits = null;  // digits after the decimal point
        char currentChar;              // current character

        type = INTEGER;  // assume INTEGER token type for now

        // Extract the digits of the whole part of the number.
        wholeDigits = unsignedIntegerDigits(textBuffer);

        // Is there a . ?
        currentChar = currentChar();
        if (currentChar == '.') {
            type = REAL;  // decimal point, so token type is REAL
            textBuffer.append(currentChar);
            currentChar = nextChar();  // consume decimal point

            // Collect the digits of the fraction part of the number.
            fractionDigits = unsignedIntegerDigits(textBuffer);
        }

        if (type == INTEGER) {
            value = computeIntegerValue(wholeDigits);
        }
        else if (type == REAL) {
            value = computeFloatValue(wholeDigits, fractionDigits);
        }
    }

    private String unsignedIntegerDigits(StringBuilder textBuffer) throws IOException {
        char currentChar = currentChar();

        // Extract the digits.
        StringBuilder digits = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            textBuffer.append(currentChar);
            digits.append(currentChar);
            currentChar = nextChar();  // consume digit
        }

        return digits.toString();
    }

    private int computeIntegerValue(String digits) {
        // Return 0 if no digits.
        if (digits == null) {
            return 0;
        }

        int integerValue = 0;
        int prevValue = -1;    // overflow occurred if prevValue > integerValue
        int index = 0;

        // Loop over the digits to compute the integer value
        // as long as there is no overflow.
        while ((index < digits.length()) && (integerValue >= prevValue)) {
            prevValue = integerValue;
            integerValue = 10*integerValue +
                    Character.getNumericValue(digits.charAt(index++));
        }

        return integerValue;
    }

    private double computeFloatValue(String wholeDigits, String fractionDigits) {
        double floatValue = 0.0;
        String digits = wholeDigits;  // whole and fraction digits

        // If there are any fraction digits, adjust the exponent value
        // and append the fraction digits.
        if (fractionDigits != null) {
            digits += fractionDigits;
        }

        // Loop over the digits to compute the float value.
        int index = 0;
        while (index < digits.length()) {
            floatValue = 10 * floatValue + Character.getNumericValue(digits.charAt(index++));
        }

        return floatValue;
    }
}
