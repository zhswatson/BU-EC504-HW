package bu.ec504.hw3Q4;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class hash {

	/**
	 * Converts an array of bytes into a readable set of characters in the range
	 * ! through ~
	 * 
	 * @param bytes
	 *            The array of bytes
	 * @return A string with characters in the range ! through ~
	 */
	public static String makeReadable(byte[] bytes) {
		for (int ii = 0; ii < bytes.length; ii++) {
			bytes[ii] = (byte) ((bytes[ii] & 0x5E) + 32); // Convert to
															// character !
															// through ~
		}
		return new String(bytes);
	}

	/**
	 * produce a hash of a given string
	 * 
	 * @param str
	 *            The string to hash
	 * @return Returns a collection of sixteen "readable" characters (! through
	 *         ~) corresponding to this string.
	 */
	public static String compact(String str) {
		// setup the digest
		MessageDigest md = null;
		str += "foo"; // random text added to the string
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Hash digest format not known!");
			System.exit(-1);
		}
		//System.out.println(md.digest());
		return makeReadable(md.digest());
	}

	public static String plain(String hash) {
		String result = null;
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 256; j++) {
				for (int k = 0; k < 256; k++) {
					String tmp = "" + (char) i + (char) j + (char) k;
					if (compact(tmp).equals(hash)) {
						result = tmp;
					}
				}
			}
		}
		return result;
	}

	public static String plain2(String hash) {
		String result = null;
		for (int h = 0; h < 128; h++) {
			for (int i = 0; i < 128; i++) {
				for (int j = 0; j < 128; j++) {
					for (int k = 0; k < 128; k++) {
						String tmp = "" + (char) h + (char) i + (char) j + (char) k;
						if (compact(tmp).equals(hash)) {
							result = tmp;
						}
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(compact("5678"));
		System.out.println(compact("123456"));
		System.out.println(compact("ec504"));
		System.out.println(compact("abcdef"));
		System.out.println(compact("passwd"));
		System.out.println(compact("admin"));
		System.out.println(compact("c0De"));
		
		System.out.println(plain2("pv~bz(j, $`z :vp"));
/*		System.out.println(plain2("rx6|f.0 l8.hjt<:"));
		System.out.println(plain2("4$x\".,xd\" <\"j 0j")); 
		System.out.println(plain2(":&x`>``0$lln$:8<"));
		System.out.println(plain2("*nb>8p$v.<jtvv0>"));
		System.out.println(plain2(" *np&n8hz*x\"\">~n"));
		System.out.println(plain2("fh6$rnnb ~v>v4j("));
		System.out.println(plain2("8 `2(&*,d`&l>xx "));
		System.out.println(plain2("4$n6nz&*6vt$bhv6"));
		System.out.println(plain2("**$*0:b:j8b8f&l\""));
		System.out.println(plain2("z,~~vb40`df&vhlp"));
*/
/*		System.out.println(plain("|:86(`l2>($:<8x0"));
		System.out.println(plain("64*,>jh,(\"|:xxl("));
		System.out.println(plain("`8(4(<<`|nr *,d4"));
		System.out.println(plain("|<0bt<j~fb62r>p*"));
		System.out.println(plain("$p2$~f0plr,x\"8.$"));
		System.out.println(plain("hx~j~bd. |f6|$`:"));
		System.out.println(plain("0lv>n$4  &8nn*br"));
		System.out.println(plain("0x08&fdj|zhx$h6 "));
		System.out.println(plain("zp>jn`~>0.nj&*t\""));
		System.out.println(plain("6t,d..,&~2bv,\" n"));
*/
	}
}