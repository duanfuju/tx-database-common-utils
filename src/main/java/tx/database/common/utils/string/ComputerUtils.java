package tx.database.common.utils.string;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ComputerUtils {
	/**
	 * 获取当前电脑的mac地址
	 * @return
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getLocalMac() throws SocketException, UnknownHostException {
		byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		return sb.toString();
	}
}
