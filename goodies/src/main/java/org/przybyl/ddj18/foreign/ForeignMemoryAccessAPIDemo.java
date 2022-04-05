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

import java.lang.invoke.*;

/**
 * Demo of Foreign Memory Access API incubator feature of Java 18, 5th preview,
 * based on https://openjdk.java.net/jeps/419
 * <p>
 * Please don't forget to --add-modules jdk.incubator.foreign
 */
public class ForeignMemoryAccessAPIDemo {

    public static void main(String[] args) throws InterruptedException {
        sameThreadDemo();
        twoThreadsDemo();
    }

    private static void sameThreadDemo() {
        SequenceLayout intArrayLayout = MemoryLayout.sequenceLayout(10, JAVA_INT);
        MemorySegment segment = MemorySegment.allocateNative(intArrayLayout, ResourceScope.newImplicitScope());
        populateNativeMem(intArrayLayout, segment);
        examineNativeMem(intArrayLayout, segment);
    }

    private static void twoThreadsDemo() throws InterruptedException {
        SequenceLayout intArrayLayout = MemoryLayout.sequenceLayout(10, JAVA_INT);
        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            MemorySegment segment = MemorySegment.allocateNative(intArrayLayout, scope);
            populateNativeMem(intArrayLayout, segment);
            examineNativeMem(intArrayLayout, segment);
            var readerThread = new Thread(() -> examineNativeMem(intArrayLayout, segment), "readerThread");
            readerThread.start();
            readerThread.join(100L);
//            scope.close();
//            examineNativeMem(intArrayLayout, segment);
        }
    }

    private static void populateNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        VarHandle intHandle = MemoryHandles.varHandle(JAVA_INT);
        VarHandle intElemHandle = intArrayLayout.varHandle(MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            segment.setAtIndex(JAVA_INT, i, i);
//                intHandle.set(segment, i * 4L, i);
//                intElemHandle.set(segment, (long) i, i);
        }
    }

    private static void examineNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        MemoryAddress segmentBaseAddress = segment.address();
        System.out.println("Examining memory at: " + segmentBaseAddress.toRawLongValue());
        System.out.println("Segment owned by: " + segment.scope().ownerThread());
        VarHandle intElemHandle = intArrayLayout.varHandle(MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            System.out.print(segment.getAtIndex(JAVA_INT, i));
            System.out.print(" ");
            System.out.println(intElemHandle.get(segment, (long) i));
        }
    }
}
