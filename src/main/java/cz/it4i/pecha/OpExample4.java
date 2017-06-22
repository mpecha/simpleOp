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
import net.imglib2.type.numeric.real.FloatType;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin(type=Op.class,name="OpExample4")
public class OpExample4<T extends RealType<T> & NativeType<T>> extends AbstractOp
{
    @Parameter(type= ItemIO.INPUT)
    private IterableInterval<T> inImg;

    @Parameter(type=ItemIO.INPUT)
    private double inVal;

    @Parameter(type=ItemIO.OUTPUT)
    private Img<T> outImg;

    @Override
    public void run()
    {
        //shortcut to reach the ops functionalities
        final OpEnvironment ops = this.ops();

        //create the output image
        outImg = ops.create().img(inImg);

        //populate the output image by adding my_double
        final T val = inImg.firstElement().createVariable();
        RealType tmp = new FloatType();

        Cursor<T> cIn = ((Img<T>) inImg).cursor();
        RandomAccess<T> raOut = outImg.randomAccess();

        while (cIn.hasNext())
        {
            cIn.next();
            tmp.setReal(cIn.get().getRealDouble() + inVal);

            if (tmp.getRealDouble() > 255.) val.setReal(255.);
            else if (tmp.getRealDouble() < 0) val.setReal(0.);
            else val.setReal(tmp.getRealDouble());

            raOut.setPosition(cIn);
            raOut.get().setReal(val.getRealFloat());
        }
    }
}
