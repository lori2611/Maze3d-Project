package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private int counter;
	private int prevByte;
	
	public MyCompressorOutputStream(OutputStream out) {
		try{
			this.out = out;
			this.counter = 0;
			this.prevByte = 0;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void write(int b) throws IOException{

		// Check that 'b' is valid
		if(b==0 || b==1)
		{
			// First case - check if it's out first byte
			if(this.counter == 0)
			{
				++this.counter;
				prevByte = b;
				return;
			}
			
			// Second case - check if the current byte is equal to the previous one
			if(this.prevByte == b)
			{
				++this.counter;
			}
			
			// Third case - if the next byte is different - commit data and set the next parameters details.
			else
			{
				try{
					out.write(prevByte);
					out.write(counter);
					this.counter = 0;
					this.prevByte = b;
				} catch (Exception e) {
					throw new IOException("Specified file doesn't found.");
				}
			}
		}
	}
	
	public void write(byte[] b) {
		
		// Call super class to write 'b' into 
		try {
			super.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// super.write won't write the last byte to the file (it will commit our data only when we will
		// get other byte) - so now we will make sure that everything is written
		
		if(this.counter > 0)
		{
			try {
				out.write(this.prevByte);
				out.write(this.counter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
