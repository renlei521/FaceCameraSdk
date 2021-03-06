package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * \u8bbe\u5907\u63cf\u8ff0\u4fe1\u606f\u53c2\u6570\uff08224\u5b57\u8282\uff09\u3002<br>
 * <i>native declaration : config_gw.h:766</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class DescriptionParam extends Structure {
	/**
	 * \u70b9\u4f4d\u7f16\u53f7\u3002<br>
	 * C type : char[32]
	 */
	public byte[] addr_id = new byte[32];
	/**
	 * \u70b9\u4f4d\u540d\u79f0\u3002<br>
	 * C type : char[96]
	 */
	public byte[] addr_name = new byte[96];
	/**
	 * \u8bbe\u5907\u7f16\u53f7\u3002<br>
	 * C type : char[32]
	 */
	public byte[] device_id = new byte[32];
	/**
	 * \u4fdd\u7559\u5b57\u6bb5\u3002<br>
	 * C type : char[64]
	 */
	public byte[] resv = new byte[64];
	public DescriptionParam() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("addr_id", "addr_name", "device_id", "resv");
	}
	/**
	 * @param addr_id \u70b9\u4f4d\u7f16\u53f7\u3002<br>
	 * C type : char[32]<br>
	 * @param addr_name \u70b9\u4f4d\u540d\u79f0\u3002<br>
	 * C type : char[96]<br>
	 * @param device_id \u8bbe\u5907\u7f16\u53f7\u3002<br>
	 * C type : char[32]<br>
	 * @param resv \u4fdd\u7559\u5b57\u6bb5\u3002<br>
	 * C type : char[64]
	 */
	public DescriptionParam(byte addr_id[], byte addr_name[], byte device_id[], byte resv[]) {
		super();
		if ((addr_id.length != this.addr_id.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.addr_id = addr_id;
		if ((addr_name.length != this.addr_name.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.addr_name = addr_name;
		if ((device_id.length != this.device_id.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.device_id = device_id;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends DescriptionParam implements Structure.ByReference {
		
	};
	public static class ByValue extends DescriptionParam implements Structure.ByValue {
		
	};
}
