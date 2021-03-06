package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * \u670d\u52a1\u8ba4\u8bc1\u53c2\u6570\uff0848\u5b57\u8282\uff09<br>
 * <i>native declaration : config_gw.h:824</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class AuthParam extends Structure {
	/** \u670d\u52a1\u8ba4\u8bc1\u5f00\u5173 */
	public byte enable;
	/**
	 * \u767b\u5f55\u9a8c\u8bc1\u7528\u6237\u540d\u3002<br>
	 * C type : char[16]
	 */
	public byte[] user_name = new byte[16];
	/**
	 * \u767b\u5f55\u9a8c\u8bc1\u5bc6\u7801\u3002<br>
	 * C type : char[17]
	 */
	public byte[] passwd = new byte[17];
	/**
	 * \u4fdd\u7559\u5b57\u6bb5\u3002<br>
	 * C type : char[14]
	 */
	public byte[] resv = new byte[14];
	public AuthParam() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("enable", "user_name", "passwd", "resv");
	}
	/**
	 * @param enable \u670d\u52a1\u8ba4\u8bc1\u5f00\u5173<br>
	 * @param user_name \u767b\u5f55\u9a8c\u8bc1\u7528\u6237\u540d\u3002<br>
	 * C type : char[16]<br>
	 * @param passwd \u767b\u5f55\u9a8c\u8bc1\u5bc6\u7801\u3002<br>
	 * C type : char[17]<br>
	 * @param resv \u4fdd\u7559\u5b57\u6bb5\u3002<br>
	 * C type : char[14]
	 */
	public AuthParam(byte enable, byte user_name[], byte passwd[], byte resv[]) {
		super();
		this.enable = enable;
		if ((user_name.length != this.user_name.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.user_name = user_name;
		if ((passwd.length != this.passwd.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.passwd = passwd;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends AuthParam implements Structure.ByReference {
		
	};
	public static class ByValue extends AuthParam implements Structure.ByValue {
		
	};
}
