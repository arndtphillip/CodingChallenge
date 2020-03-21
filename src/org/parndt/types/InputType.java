package org.parndt.types;

public enum InputType {
    STRING {
        @Override
        public TypedInput convert(String value) {
            return new StringInput(value);
        }
    },
    INT {
        @Override
        public TypedInput convert(String value) {
            return new IntInput(value);
        }
    },
    DOUBLE {
        @Override
        public TypedInput convert(String value) {
            return new DoubleInput(value);
        }
    };

    public abstract TypedInput convert(String value) throws IllegalArgumentException;
}
