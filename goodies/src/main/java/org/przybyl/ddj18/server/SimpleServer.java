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
package org.przybyl.ddj18.server;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class SimpleServer {
    public static void main(String[] args) throws URISyntaxException, IOException {

       var server =  SimpleFileServer.createFileServer(
           new InetSocketAddress(9000),
           Paths.get(ClassLoader.getSystemResource("server").toURI()),
           SimpleFileServer.OutputLevel.INFO );

       server.createContext("/style.css", exchange -> {
           var response = new String(
               ClassLoader.getSystemResourceAsStream("server/style.css").readAllBytes()
           ).replace("indianred", "darkblue").getBytes();
           exchange.sendResponseHeaders(200, response.length);
           exchange.getResponseBody().write(response);
           exchange.close();
       });

        server.start();
    }
}
