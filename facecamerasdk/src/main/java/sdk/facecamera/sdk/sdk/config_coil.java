package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:219</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class config_coil extends Structure {
	public int enable;
	/** \u4e24\u7ebf\u5708\u4e4b\u95f4\u8ddd\u79bb,\u5355\u4f4dcm \u7528\u6765\u8ba1\u7b97\u4e24\u4e2aIO\u4e4b\u95f4\u65f6\u95f4\u5dee */
	public int distance;
	/** C type : coil_io_attr[(8)] */
	public coil_io_attr[] io_attr = new coil_io_attr[8];
	/** C type : char[64] */
	public byte[] resv = new byte[64];
	public config_coil() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("enable", "distance", "io_attr", "resv");
	}
	/**
	 * @param distance \u4e24\u7ebf\u5708\u4e4b\u95f4\u8ddd\u79bb,\u5355\u4f4dcm \u7528\u6765\u8ba1\u7b97\u4e24\u4e2aIO\u4e4b\u95f4\u65f6\u95f4\u5dee<br>
	 * @param io_attr C type : coil_io_attr[(8)]<br>
	 * @param resv C type : char[64]
	 */
	public config_coil(int enable, int distance, coil_io_attr io_attr[], byte resv[]) {
		super();
		this.enable = enable;
		this.distance = distance;
		if ((io_attr.length != this.io_attr.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.io_attr = io_attr;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends config_coil implements Structure.ByReference {
		
	};
	public static class ByValue extends config_coil implements Structure.ByValue {
		
	};
}
