package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:606</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class device_group_gate_attr extends Structure {
	/** C type : char[32] */
	public byte[] gate_name = new byte[32];
	public int cam_num;
	/** C type : device_group_cam_attr[4] */
	public device_group_cam_attr[] attr = new device_group_cam_attr[4];
	public device_group_gate_attr() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("gate_name", "cam_num", "attr");
	}
	/**
	 * @param gate_name C type : char[32]<br>
	 * @param attr C type : device_group_cam_attr[4]
	 */
	public device_group_gate_attr(byte gate_name[], int cam_num, device_group_cam_attr attr[]) {
		super();
		if ((gate_name.length != this.gate_name.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.gate_name = gate_name;
		this.cam_num = cam_num;
		if ((attr.length != this.attr.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.attr = attr;
	}
	public static class ByReference extends device_group_gate_attr implements Structure.ByReference {
		
	};
	public static class ByValue extends device_group_gate_attr implements Structure.ByValue {
		
	};
}
