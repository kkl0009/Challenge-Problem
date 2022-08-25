public class Cipher {
	private static final byte[] FEEDBACK = {(byte)0x87, (byte)0x65, (byte)0x43, (byte)0x21};
	private static final int STEP_COUNT = 8;

	public static void main(String[] args) {
		System.out.println(FEEDBACK[0]);
		printBytes(FEEDBACK);

		byte[] result = Crypt("apple".getBytes(), 0x12345678);
	}

	public static byte[] Crypt(byte[] data, long initialValue) {
		int dataLength = data.length;

		System.out.println("data: " + byteArrayToString(data));
		System.out.println("dataLength: " + dataLength);
		System.out.println("initialValue: " + initialValue);

		for(int i = 0; i < STEP_COUNT; i++) {
			if(data & 0x01 == 0x01) {
				data = data >> 0x01;
			}
			else {
				data = (data >> 0x01) ^ FEEDBACK;
			}
		}

		return null;
	}

	private static String byteArrayToString(byte[] bytes) {
		String result = "";
		for(int i = 0; i < bytes.length; i++) {
			result += (char)bytes[i];
		}
		return result;
	}

	private static void printBytes(byte[] bytes) {
		System.out.println();
		for(int i = 0; i < bytes.length; i++) {
			int byteToAdd = bytes[i];
			if(byteToAdd < 0) {
				byteToAdd += 0x100;
			}
			System.out.print("\\x" + Integer.toHexString(byteToAdd));
		}
		System.out.println();
	}
}