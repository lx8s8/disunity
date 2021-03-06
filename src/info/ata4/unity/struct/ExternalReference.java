/*
 ** 2013 July 12
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package info.ata4.unity.struct;

import info.ata4.util.io.DataInputReader;
import info.ata4.util.io.DataOutputWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class ExternalReference implements Struct {
    
    private static final Logger L = Logger.getLogger(ExternalReference.class.getName());
    
    public byte[] guid = new byte[16];
    public String filePath;
    public String assetPath;
    public int type;

    @Override
    public void read(DataInputReader in) throws IOException {
        in.readFully(guid);
        L.log(Level.FINEST, "guid = {0}", DatatypeConverter.printHexBinary(guid));

        type = in.readInt();
        L.log(Level.FINEST, "type = {0}", type);

        filePath = in.readStringNull();
        L.log(Level.FINEST, "filePath = {0}", filePath);

        assetPath = in.readStringNull();
        L.log(Level.FINEST, "assetPath = {0}", assetPath);
    }

    @Override
    public void write(DataOutputWriter out) throws IOException {
        out.write(guid);
        out.writeInt(type);
        out.writeStringNull(filePath);
        out.writeStringNull(assetPath);
    }
}
