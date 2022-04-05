package org.przybyl.ddj18.utf8;/*
 *  Copyright (C) 2022 Piotr Przyby?
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

import java.io.*;
import java.util.*;

public class EncodingCheck {

    // Check your encoding with
    // java -XshowSettings:properties -version 2>&1 | grep file.encoding

    public static void main(String[] args) throws IOException {

        System.out.printf("Running Java %d, file.encoding=%s, native.encoding=%s%n",
            Runtime.version().feature(),
            System.getProperty("file.encoding"),
            System.getProperty("native.encoding")
        );

        var utf8Stream = ClassLoader.getSystemResourceAsStream("encoding/testUTF8.txt");
        var win1250Stream = ClassLoader.getSystemResourceAsStream("encoding/testWin1250.txt");

        for (var stream : Arrays.asList(utf8Stream, win1250Stream)) {
            try (var defReader = new BufferedReader(new InputStreamReader(stream))) {
                String line = defReader.readLine();
                System.out.println(line + " with "+line.length() + " characters.");
            }
        }
    }
}
