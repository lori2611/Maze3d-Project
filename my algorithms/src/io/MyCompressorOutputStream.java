package io;

import java.io.IOException;
import java.io.OutputStream;

 /**
  * MyCompressorOutputStream will compress to save bandwidth while communicate
  * with our application server
  * @author lori
  *
  */
public class MyCompressorOutputStream extends OutputStream {

	private OutputStream out;
	private int counter;
	private int prevByte;
	
	/**
	 * C'tor
	 * @param out
	 * @throws Exception
	 */
	public MyCompressorOutputStream(OutputStream out) throws Exception{
		try{
			this.out = out;
			this.counter = 0;
			this.prevByte = 0;
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	/**
	 * The 'write' method will commit our data into the file only if
	 * the current Byte is different from the previous one. 
	 */
	public void write(int b) throws IOException{

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
				this.counter = 1;
				this.prevByte = b;
			} catch (Exception e) {
				throw new IOException("Specified file doesn't found.");
			}
		}
		
	}
	
	/**
	 * This 'write' method will call the superclass 'write' method and then it will
	 * add the last byte to the file (it won't be saved in our file because the 'write' method
	 * commit the data only when the current byte is different then the previous one).
	 */
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
				out.write((byte)this.prevByte);
				out.write((byte)this.counter);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
