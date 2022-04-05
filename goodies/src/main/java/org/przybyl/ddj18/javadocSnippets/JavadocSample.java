/*
 *  Copyright (C) 2022 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package org.przybyl.ddj18.javadocSnippets;

import java.util.*;

/**
 * This is a doc for {@code JavadocSample}.
 * It can be created as:
 * {@snippet :
 * var js = new JavadocSample(1,2,3);
 *}
 * or if someone is happy with defaults, as:
 * {@snippet :
 * var js = new JavadocSample();
 *}
 * Then one can try to use it:
 * {@snippet file=SampleDemo.java }
 * Or use just a region
 * {@snippet file=SampleDemo.java region=usage }
 *
 * <a href="https://docs.oracle.com/en/java/javase/18/code-snippet/index.html">Programmer's Guide to Snippets</a>
 */
public class JavadocSample {

    private final int a;
    private final int b;
    private final int c;

    /**
     * Full constructor.
     * @param a the a
     * @param b the b
     * @param c the c
     */
    // @start region="fullConstructor"
    public JavadocSample(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    // @end

    /**
     * Default constructor, if you're happy with 1, 2 and 3.
     */
    // @start region="defaultConstructor"
    public JavadocSample() {
        this(1, 2, 3);
    }
    // @end


    @Override
    public String toString() {
        return new StringJoiner(", ", JavadocSample.class.getSimpleName() + "[", "]")
            .add("a=" + a)
            .add("b=" + b)
            .add("c=" + c)
            .toString();
    }
}
