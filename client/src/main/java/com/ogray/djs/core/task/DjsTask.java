package com.ogray.djs.core.task;

/**
 * 	Copyright (C) 2021 Sergey A. Salata. All rights reserved.
 *
 *	This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import java.io.*;

public abstract class DjsTask implements Serializable {

    /**
     * Base class for distributed task to run on remote nodes
     */
    public abstract Object execute();

    public byte[] serialize() throws IOException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(this);
        out.flush();

        return byteOut.toByteArray();
    }

    public static DjsTask deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        ObjectInputStream in = new ObjectInputStream(byteIn);

        Object obj = in.readObject();
        return (DjsTask) obj;
    }
}
