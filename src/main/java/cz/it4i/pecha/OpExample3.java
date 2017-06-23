package cz.it4i.pecha;

import net.imagej.ops.AbstractOp;
import net.imagej.ops.Op;
import net.imagej.ops.OpEnvironment;
import net.imglib2.Cursor;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccess;
import net.imglib2.img.Img;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type = Op.class, name="OpExample3")
public class OpExample3<T extends RealType<T> & NativeType<T>> extends AbstractOp
{
    @Parameter(type= ItemIO.INPUT)
    private IterableInterval<T> inImg;

    @Parameter(type=ItemIO.INPUT)
    private float inVal;

    @Parameter(type=ItemIO.OUTPUT)
    private Img<T> outImg;

    /*
     * Add value to copied image by using Cursor, RandomAccess (generic approach)
     */
    @SuppressWarnings({"unchecked"})
    public void run()
    {
        //shortcut to reach the ops functionalities
        final OpEnvironment ops = this.ops();

        //create the output image
        outImg = ops.create().img(inImg);

        //populate the output image by adding my_double
        final T val = inImg.firstElement().createVariable();
        final T tmp = inImg.firstElement().createVariable();
        tmp.setReal(inVal);

        Cursor<T> cIn = ((Img<T>) inImg).cursor();
        RandomAccess<T> raOut = outImg.randomAccess();

        while (cIn.hasNext()) {
            val.setReal(cIn.next().getRealFloat());
            raOut.setPosition(cIn);
            raOut.get().setReal(val.getRealFloat());
        }

        outImg = (Img<T>) ops.run("math.add", outImg, inVal);
    }
}
