/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.text.transform;

import com.google.common.collect.ImmutableList;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextRepresentable;

import java.util.Collection;
import java.util.Iterator;

/**
 * A TextFormatter is a mutable collection of {@link TextRepresentable}s which
 * are all concatenated to an empty {@link Text} object on {@link #format()}.
 */
public interface TextFormatter<E extends TextRepresentable> extends TextRepresentable, Iterable<E> {

    /**
     * Returns an {@link ImmutableList} of this formatter's elements.
     *
     * @return All elements
     */
    ImmutableList<E> getAll();

    /**
     * Returns the element at the specified index.
     *
     * @param i Index to retrieve from
     * @return Element at index
     */
    default E get(int i) {
        return getAll().get(i);
    }

    /**
     * Clears all elements from this formatter.
     */
    void clear();

    /**
     * Adds the specified element to the end of this formatter.
     *
     * @param element Element to add
     * @return True if the formatter changed as a result of the call
     */
    boolean add(E element);

    /**
     * Adds the specified elements to the end of this formatter.
     *
     * @param elements Elements to add
     * @return True if the formatter changed as a result of the call
     */
    boolean add(Collection<E> elements);

    /**
     * Adds the specified elements to the end of this formatter.
     *
     * @param elements Elements to add
     * @return True if the formatter changed as a result of the call
     */
    boolean add(Iterable<E> elements);

    /**
     * Adds the specified elements to the end of this formatter.
     *
     * @param elements Elements to add
     * @return True if the formatter changed as a result of the call
     */
    boolean add(Iterator<E> elements);

    /**
     * Inserts the specified element at the specified index within the
     * formatter.
     *
     * @param i Index to insert at
     * @param element Element to insert
     */
    void insert(int i, E element);

    /**
     * Inserts the specified elements at the specified index within the
     * formatter.
     *
     * @param i Index to insert at
     * @param elements Elements to insert
     */
    void insert(int i, Collection<E> elements);

    /**
     * Inserts the specified elements at the specified index within the
     * formatter.
     *
     * @param i Index to insert at
     * @param elements Elements to insert
     */
    void insert(int i, Iterable<E> elements);

    /**
     * Inserts the specified elements at the specified index within the
     * formatter.
     *
     * @param i Index to insert at
     * @param elements Elements to insert
     */
    void insert(int i, Iterator<E> elements);

    /**
     * Removes the specified element from the formatter.
     *
     * @param element Element to remove
     * @return True if this formatter contained the Element
     */
    boolean remove(E element);

    /**
     * Removes the specified elements from the formatter.
     *
     * @param elements Elements to remove
     * @return True if the formatter changed as a result of this call
     */
    boolean remove(Collection<E> elements);

    /**
     * Removes the specified elements from the formatter.
     *
     * @param elements Elements to remove
     * @return True if the formatter changed as a result of this call
     */
    boolean remove(Iterable<E> elements);

    /**
     * Removes the specified elements from the formatter.
     *
     * @param elements Elements to remove
     * @return True if the formatter changed as a result of this call
     */
    boolean remove(Iterator<E> elements);

    /**
     * Removes all elements from the formatter except for these specified
     * elements.
     *
     * @param elements Elements to retain
     * @return True if the formatter changed as a result of this call
     */
    boolean retain(Collection<E> elements);

    /**
     * Removes all elements from the formatter except for these specified
     * elements.
     *
     * @param elements Elements to retain
     * @return True if the formatter changed as a result of this call
     */
    boolean retain(Iterable<E> elements);

    /**
     * Removes all elements from the formatter except for these specified
     * elements.
     *
     * @param elements Elements to retain
     * @return True if the formatter changed as a result of this call
     */
    boolean retain(Iterator<E> elements);

    /**
     * Builds the result {@link Text} for this formatter using the current
     * configuration of each element. The result of each element is
     * concatenated to an empty {@link Text} to yield the result.
     *
     * @return Text result of formatter
     */
    default Text format() {
        Text text = Text.EMPTY;
        for (E e : this) {
            text = text.concat(e.toText());
        }
        return text;
    }

    @Override
    default Iterator<E> iterator() {
        return getAll().iterator();
    }

    @Override
    default Text toText() {
        return format();
    }

}
