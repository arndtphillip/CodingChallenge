package org.parndt.types;

/**
 * This enum represents all available input types
 *
 * @author Philipp Arndt
 */
public enum InputType {
    /**
     * String input, with {@link StringInput} as {@link TypedInput} implementation.
     */
    STRING {
        @Override
        public TypedInput convert(String value) {
            return new StringInput(value);
        }
    },
    /**
     * Int input, with {@link IntInput} as {@link TypedInput} implementation.
     */
    INT {
        @Override
        public TypedInput convert(String value) {
            return new IntInput(value);
        }
    },
    /**
     * Double input, with {@link DoubleInput} as {@link TypedInput} implementation.
     */
    DOUBLE {
        @Override
        public TypedInput convert(String value) {
            return new DoubleInput(value);
        }
    };

    /**
     * Each input type has to implement the convert-method, which converts a string input value into a
     * {@link TypedInput} object.
     *
     * @param value The string value to convert
     * @return A new {@link TypedInput} object, that contains the string value
     * @throws IllegalArgumentException Throws an IllegalArgumentException if the value couldn't be parsed
     */
    public abstract TypedInput convert(String value) throws IllegalArgumentException;
}
