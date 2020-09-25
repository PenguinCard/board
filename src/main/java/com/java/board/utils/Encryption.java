package com.java.board.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	// 회원정보(pw)를 SHA-256으로 암호화하여 저장하기 위한 method
	public static String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest md=MessageDigest.getInstance("SHA-256");
		md.update(msg.getBytes());
		
		StringBuilder strBuilder=new StringBuilder();
		for(byte b : md.digest()) {
			strBuilder.append(String.format("%02x", b));
		}
		return strBuilder.toString();
	}
}
