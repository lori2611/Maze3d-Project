package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	InputStream in;
	
	public MyDecompressorInputStream(InputStream in) throws IOException {
		try{
			this.in = in;
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public int read() throws IOException {
		int i,counter=0;
		byte[] b;
		
		
		return 0;
	}

}
