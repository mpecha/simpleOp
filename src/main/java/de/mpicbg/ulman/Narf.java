package de.mpicbg.ulman;

import net.imagej.ops.AbstractOp;
import net.imagej.ops.Op;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.plugin.PluginInfo;

// The @Plugin annotation allows this Op to be discovered by the OpService.
// We declare the type of op, the name of the op, and any optional aliases...
@Plugin(type = Op.class, name = "narf")
public class Narf extends AbstractOp
{

    // INPUTS, declared using @Parameter notation
    @Parameter
    private String input;

    // OUTPUTS, declared using @Parameter notation
    @Parameter(type = ItemIO.OUTPUT)
    private String output;

    @Parameter(type = ItemIO.OUTPUT)
    private int my_int;

    @Override
    public void run() {
        // The job of the run method is to populate any outputs using the inputs
        output = "Egads ! " + input.toUpperCase();
        my_int = 5;
    }
}
