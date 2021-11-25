package com.ogray.djs.core.task;

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
