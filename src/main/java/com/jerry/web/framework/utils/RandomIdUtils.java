package com.jerry.web.framework.utils;

import java.security.SecureRandom;


/**
 *  UserId生成类。
 *  在5-8字节放置时间戳，单位为秒。其他字节为随机。
 */
public class RandomIdUtils {

	private static byte[] int2bytes(int i) {
		//bigendian mode
		byte[] b = new byte[4];
		b[3] = (byte) (0xff & i);
		b[2] = (byte) ((0xff00 & i) >> 8);
		b[1] = (byte) ((0xff0000 & i) >> 16);
		b[0] = (byte) ((0xff000000 & i) >> 24);
		return b;
	}

	private static class Holder {
		static final SecureRandom numberGenerator = new SecureRandom();
	}

	public static String getRandomUserId() {

		return Base64.encode(getUUIDBytes());
	}

	public static String getRandomHexId() {

		return bytesToHexString(getUUIDBytes());
	}

	private static byte[] getUUIDBytes() {

		SecureRandom ng = Holder.numberGenerator;
		byte[] randomBytes = new byte[16];
		ng.nextBytes(randomBytes);

		int now = (int) (System.currentTimeMillis() / 1000);
		byte[] btime = int2bytes(now);
		System.arraycopy(btime, 0, randomBytes, 4, 4);

		return randomBytes;
	}

	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

}
