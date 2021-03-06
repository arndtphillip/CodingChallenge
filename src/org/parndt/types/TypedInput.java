package org.parndt.types;

/**
 * This interface is used to define new input types. Each new input type should implement this interface.
 *
 * @author Philipp Arndt
 */
public interface TypedInput {

    /**
     * Converts and returns the value of the input as string
     *
     * @return The value as string
     */
    String getStringValue();
}
