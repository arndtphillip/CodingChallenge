package org.parndt.operations;

import org.parndt.types.TypedInput;

public enum Operation {
    CAPITALIZE {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Capitalizable) {
                ((Capitalizable) typedInput).capitalize();
            }
        }
    },
    REVERSE {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Reversable) {
                ((Reversable) typedInput).reverse();
            }
        }
    },
    NEG {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Negatable) {
                ((Negatable) typedInput).negate();
            }
        }
    };

    public abstract void apply(TypedInput typedInput);
}
