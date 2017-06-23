package cz.it4i.pecha;

import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;
import net.imglib2.type.numeric.real.FloatType;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class tryOps
{
	public static void main(final String... args) throws IOException
	{
		//start up a new ImageJ2 instance
		final ImageJ ij = new net.imagej.ImageJ();
		ij.ui().showUI();

		String narfResult = (String) ij.op().run("OpNarf", "Put some trousers on");
		System.out.println(narfResult + "\n");

		@SuppressWarnings("unchecked")
		ArrayList<String> narf2Result = (ArrayList<String>) ij.op().run("OpNarf2", "Put some trousers on");
		for (String r : narf2Result) System.out.println(r);

		//working with resources
		URL url = tryOps.class.getResource("/narf.jpg");

		@SuppressWarnings("unchecked")
		Img<FloatType> img = (Img<FloatType>) ij.io().open(url.getPath());
		ij.ui().show(img);

		@SuppressWarnings("unchecked")
		Img<RealType> resultOp1 = (Img<RealType>) ij.op().run("OpExample1", img, 16);
		ij.ui().show(resultOp1);

		@SuppressWarnings("unchecked")
		Img<RealType> resultOp2 = (Img<RealType>) ij.op().run("OpExample2", img);
		ij.ui().show(resultOp2);

		@SuppressWarnings("unchecked")
		Img<RealType> resultOp3 = (Img<RealType>) ij.op().run("OpExample3", img, -36);
		ij.ui().show(resultOp3);

		@SuppressWarnings("unchecked")
		Img<RealType> resultOp4 = (Img<RealType>) ij.op().run("OpExample4", img, -36);
		ij.ui().show(resultOp4);
	}
}
