/**
 * Copyright (c) 2009-2021 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Aes {
	private SecretKeySpec spec;
	private Cipher decipher;
	private Cipher encipher;

	public Aes(byte[] key) {
		spec = new SecretKeySpec(key, "AES");
		try {
			decipher = Cipher.getInstance("AES/ECB/NoPadding");
			decipher.init(Cipher.DECRYPT_MODE, spec);
			encipher = Cipher.getInstance("AES/ECB/NoPadding");
			encipher.init(Cipher.ENCRYPT_MODE, spec);
		} catch (Exception ignored) {
		}
	}

	public byte[] decrypt(byte[] data) throws IOException {
		try {
			return decipher.doFinal(data);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	public int decrypt(byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset)
			throws IOException {
		try {
			return decipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}

	public byte[] encrypt(byte[] data) throws IOException {
		try {
			return encipher.doFinal(data);
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}
}
