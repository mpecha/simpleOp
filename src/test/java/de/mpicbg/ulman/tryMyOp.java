/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */
package de.mpicbg.ulman;

import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.FinalDimensions;
import net.imglib2.type.numeric.real.FloatType;

public class tryMyOp
{
	public static void main(final String... args)
	{
		//start up a new ImageJ2 instance
		final ImageJ ij = new net.imagej.ImageJ();
		ij.ui().showUI();

		//prepare the output image
		Img<FloatType> img = ij.op().create().img(new FinalDimensions(100,100), new FloatType());

		//calls out new op
		@SuppressWarnings("unchecked")
		Img<FloatType> res = (Img<FloatType>)ij.op().run("Ulman-firstOp", img);

		//show the result image
		ij.ui().show(res);
	}
}
