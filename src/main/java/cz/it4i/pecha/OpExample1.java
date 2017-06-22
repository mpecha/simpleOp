package cz.it4i.pecha;

import net.imagej.ops.AbstractOp;
import net.imglib2.img.Img;
import org.scijava.ItemIO;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import net.imagej.ops.Op;
import net.imagej.ops.OpEnvironment;
import net.imglib2.IterableInterval;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

@Plugin(type = Op.class, name="OpExample1")
public class OpExample1<T extends RealType<T> & NativeType<T>> extends AbstractOp
{
    @Parameter(type=ItemIO.INPUT)
    private IterableInterval<T> inImg;

    @Parameter(type=ItemIO.INPUT)
    private float inVal;

    @Parameter(type=ItemIO.OUTPUT)
    private Img<T> outImg;

    @Override
    public void run()
    {
        final OpEnvironment ops = this.ops();

        //create the output image
        outImg = ops.create().img(inImg);

        //populate the output image by adding my_double
        final T val = inImg.firstElement().createVariable();
        val.setReal(inVal);

        //TODO solve unchecked cast?
        outImg = (Img<T>) ops.run("math.add", outImg, val);
    }
}
