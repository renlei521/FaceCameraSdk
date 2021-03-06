package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:596</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class device_group_cam_attr extends Structure {
	/** C type : char[20] */
	public byte[] ip = new byte[20];
	/** C type : char[32] */
	public byte[] cam_name = new byte[32];
	public byte assistFlag;
	public byte inFlag;
	/** C type : char[2] */
	public byte[] resv = new byte[2];
	/** C type : int[4] */
	public int[] r = new int[4];
	public device_group_cam_attr() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("ip", "cam_name", "assistFlag", "inFlag", "resv", "r");
	}
	/**
	 * @param ip C type : char[20]<br>
	 * @param cam_name C type : char[32]<br>
	 * @param resv C type : char[2]<br>
	 * @param r C type : int[4]
	 */
	public device_group_cam_attr(byte ip[], byte cam_name[], byte assistFlag, byte inFlag, byte resv[], int r[]) {
		super();
		if ((ip.length != this.ip.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.ip = ip;
		if ((cam_name.length != this.cam_name.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.cam_name = cam_name;
		this.assistFlag = assistFlag;
		this.inFlag = inFlag;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
		if ((r.length != this.r.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.r = r;
	}
	public static class ByReference extends device_group_cam_attr implements Structure.ByReference {
		
	};
	public static class ByValue extends device_group_cam_attr implements Structure.ByValue {
		
	};
}
