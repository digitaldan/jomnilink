package com.digitaldan.jomnilinkII;

/**
*  Copyright (C) 2009  Dan Cunningham                                         
*                                                                             
* This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU General Public License
* as published by the Free Software Foundation, version 2
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class Aes {

	private SecretKeySpec spec;
	private Cipher decipher;
	private Cipher encipher;
	
	public Aes(byte [] key){
		spec = new SecretKeySpec(key,"AES");
		try{
			decipher = Cipher.getInstance("AES/ECB/NoPadding");
			decipher.init(Cipher.DECRYPT_MODE, spec);
			encipher = Cipher.getInstance("AES/ECB/NoPadding");
			encipher.init(Cipher.ENCRYPT_MODE, spec);
		}catch(Exception ignored){
		}
	}
	
	public byte[] decrypt(byte[] data) throws IOException{
		try {
		    return decipher.doFinal(data);
		}catch(Exception e){
			throw new IOException(e.getMessage());
		}
	}
	
	public int decrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset) throws IOException{
		try {
		    return decipher.doFinal(input,inputOffset,inputLen, output,outputOffset);
		}catch(Exception e){
			throw new IOException(e.getMessage());
		}
	}
	
	public byte[] encrypt(byte[] data) throws IOException{
		try{
		    return encipher.doFinal(data);
		}catch(Exception e){
			throw new IOException(e.getMessage());
		}
	}
}
