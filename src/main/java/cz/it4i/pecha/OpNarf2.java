package cz.it4i.pecha;

import net.imagej.ops.AbstractOp;
import net.imagej.ops.Op;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type=Op.class, name="OpNarf2")
public class OpNarf2 extends AbstractOp
{
    // INPUT/Output, declared using @Parameter notation
    @Parameter(type=ItemIO.INPUT)
    private String inputOutput;

    // OUTPUTS, declared using @Parameter notation
    @Parameter(type = ItemIO.OUTPUT)
    private String output1;

    @Parameter(type = ItemIO.OUTPUT)
    private String output2;

    @Override
    public void run() {
        // The job of the run method is to populate any outputs using the inputs
        output1 = "Egads! " + inputOutput.toUpperCase();
        output2 = "Yeah! I will";
    }
}
