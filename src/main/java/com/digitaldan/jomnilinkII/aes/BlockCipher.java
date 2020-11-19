/**
 * Copyright (c) 2009-2020 Dan Cunningham
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package com.digitaldan.jomnilinkII.aes;
//BlockCipher - a block encryption template

//
// Copyright (C) 1996 by Jef Poskanzer <jef@mail.acme.com>.  All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
// 1. Redistributions of source code must retain the above copyright
//    notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above copyright
//    notice, this list of conditions and the following disclaimer in the
//    documentation and/or other materials provided with the distribution.
//
// THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
// OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
// HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
// LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
// OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
// SUCH DAMAGE.
//
// Visit the ACME Labs Java page for up-to-date versions of this and other
// fine Java utilities: http://www.acme.com/java/

/// A block encryption template.
// <P>
// <A HREF="/resources/classes/Acme/Crypto/BlockCipher.java">Fetch the software.</A><BR>
// <A HREF="/resources/classes/Acme.tar.gz">Fetch the entire Acme package.</A>
// <P>
// @see Cipher
// @see StreamCipher
// @see EncryptedOutputStream
// @see EncryptedInputStream
// @see DesCipher
// @see CbcBlockCipher

public abstract class BlockCipher extends Cipher {

	/// Constructor.
	public BlockCipher(int keySize, int blockSize) {
		super(keySize);
		this.blockSize = blockSize;
	}

	/// How big a block is.
	public int blockSize;

	/// Return how big a block is.
	public int blockSize() {
		return blockSize;
	}

	/// Encrypt a block of bytes.
	public abstract void encrypt(byte[] clearText, int clearOff, byte[] cipherText, int cipherOff);

	/// Decrypt a block of bytes.
	public abstract void decrypt(byte[] cipherText, int cipherOff, byte[] clearText, int clearOff);

	/// Encrypt a block of bytes.
	public void encrypt(byte[] clearText, byte[] cipherText) {
		encrypt(clearText, 0, cipherText, 0);
	}

	/// Decrypt a block of bytes.
	public void decrypt(byte[] cipherText, byte[] clearText) {
		decrypt(cipherText, 0, clearText, 0);
	}

}
