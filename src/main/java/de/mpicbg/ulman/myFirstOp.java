/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the CC0 1.0 Universal license for details:
 *     http://creativecommons.org/publicdomain/zero/1.0/
 */
package de.mpicbg.ulman;

import org.scijava.plugin.Plugin;
import net.imagej.ops.special.function.AbstractUnaryFunctionOp;
import net.imagej.ops.Op;
import net.imagej.ops.OpEnvironment;
import net.imglib2.IterableInterval;
import net.imglib2.type.NativeType;
import net.imglib2.type.numeric.RealType;

@Plugin(type = Op.class, name="Ulman-firstOp")
public class myFirstOp<T extends RealType<T> & NativeType<T>>
extends AbstractUnaryFunctionOp<IterableInterval<T>, IterableInterval<T>>
{
	@Override
	public IterableInterval<T> calculate(IterableInterval<T> inImg) {
		//shortcut to reach the ops functionalities
		final OpEnvironment ops = this.ops();
		
		//create the output image
		IterableInterval<T> outImg = ops.create().img(inImg);
		
		//populate the output image by adding 5.0 
		final T val = inImg.firstElement().createVariable();
		val.setReal(5.0);
		outImg = ops.math().add(outImg, val);
		
		return outImg;
	}
}
