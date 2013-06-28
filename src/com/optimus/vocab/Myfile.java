package com.optimus.vocab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.util.Log;

public class Myfile {
	
	public String readfile(Context context,String filename)
	 {

		   	try {
		   		
			   	FileInputStream fIn = context.openFileInput(filename);
			   	InputStreamReader isr = new InputStreamReader(fIn);
			   	char[] inputBuffer = new char[30];
			   	isr.read(inputBuffer);
			   	String	readString= new String(inputBuffer);
			    readString=readString.trim();
			    Log.v("Test", "Data from file "+ filename+"  is =  "+readString);
			    return readString;
		   	} 
		   	catch (FileNotFoundException ioe) 
		   	{
			   	
			  		   	return "0";
			}
		   	catch (IOException ioe) 
		   	{
		       	ioe.printStackTrace();
		    }
	 	return "readString";
	 }	
	
	public void writefile(Context context,String filename,String value)
	 {
	   try { 
	 	  
		   		Log.v(value, "data written in file "+ filename+" is = "+value);
		   		FileOutputStream fOut = context.openFileOutput(filename,context.MODE_PRIVATE);
		   		OutputStreamWriter osw = new OutputStreamWriter(fOut);
		   		
		   		osw.write(value);
		   		osw.flush();
		   		osw.close();
	   }catch (IOException ioe) 
		         {
		   		ioe.printStackTrace();
		          }
	 }
	
	

}
