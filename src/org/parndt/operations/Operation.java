package org.parndt.operations;

import org.parndt.types.TypedInput;

/**
 * This enum represents all available operations.
 *
 * @author Philipp Arndt
 */
public enum Operation {
    /**
     * Capitalize operation, available on {@link TypedInput} objects which implement the {@link Capitalizable} interface.
     */
    CAPITALIZE {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Capitalizable) {
                ((Capitalizable) typedInput).capitalize();
            }
        }
    },
    /**
     * Reverse operation, available on {@link TypedInput} objects which implement the {@link Reversable} interface.
     */
    REVERSE {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Reversable) {
                ((Reversable) typedInput).reverse();
            }
        }
    },
    /**
     * Negate operation, available on {@link TypedInput} objects which implement the {@link Negatable} interface.
     */
    NEG {
        @Override
        public void apply(TypedInput typedInput) {
            if (typedInput instanceof Negatable) {
                ((Negatable) typedInput).negate();
            }
        }
    };

    /**
     * Each operation has to implement the apply-method, which applies the operation on a {@link TypedInput} object.
     *
     * @param typedInput The {@link TypedInput} the operation should be applied on.
     */
    public abstract void apply(TypedInput typedInput);
}
