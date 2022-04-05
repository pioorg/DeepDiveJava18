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


import org.przybyl.ddj18.javadocSnippets.*;

public class SampleDemo {
    public static void main(String[] args) {
        // @start region="usage"
        var sample = new JavadocSample(1, 3, 5); // @highlight regex='new JavadocSample\(.*\)'
        /* super sophisticated printing here */
        System.out.println(sample.toString()); // @link substring="toString" target="JavadocSample#toString()"
        // @end
    }
}
