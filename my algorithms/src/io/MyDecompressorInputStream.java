package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {

	private InputStream in;
	private int counter;
	private int currByte;
	
	public MyDecompressorInputStream(InputStream in) throws IOException {
		this.in = in;
		this.currByte = 0;
		this.counter = 0;
	}
	
	@Override
	public int read() throws IOException {

		// Check if it's the start of the file or the current byte sequence 
		if(this.counter <= 0)
		{
			if((this.currByte=in.read()) == -1)
			{
				// Reached to the end of the file
				return -1;
			}
			if((this.counter = in.read()) == -1)
			{
				throw new IOException("File reached to the end,missing counter parameter.");
			}
		}
		
		// Otherwise,the counter is bigger than 0 so we still in the current byte sequence
		--counter;
		return currByte;
	}

}
