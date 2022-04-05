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

package org.przybyl.ddj18.foreign;

import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;

/**
 * Please don't forget to --add-modules jdk.incubator.foreign
 * and --enable-native-access=ALL-UNNAMED
 * <p>
 * Based on https://openjdk.java.net/jeps/419
 */
public class ForeignLinkerAPIDemo {

    public static void main(String[] args) throws Throwable {

        // calling https://man7.org/linux/man-pages/man2/geteuid.2.html
        var sysLinker = CLinker.systemCLinker();
        var getuid = sysLinker.lookup("getuid")
            .orElseThrow(() -> new IllegalStateException("Cannot find `getuid`"));
        var funcDesc = FunctionDescriptor.of(JAVA_SHORT);
        var getuidHandle = sysLinker.downcallHandle(getuid, funcDesc);
        var uid = getuidHandle.invoke();
        System.out.println("Your user ID: " + uid);
    }


}
