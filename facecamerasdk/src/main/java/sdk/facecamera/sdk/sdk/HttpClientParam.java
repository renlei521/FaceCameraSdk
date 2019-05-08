package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * HTTP\u4e0a\u4f20\u53c2\u6570\u3002<br>
 * <i>native declaration : config_gw.h:923</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class HttpClientParam extends Structure {
	/**
	 * \u670d\u52a1\u5668IP\u5730\u5740\u3002<br>
	 * C type : char[16]
	 */
	public byte[] ip = new byte[16];
	/** \u670d\u52a1\u5668\u7aef\u53e3\u3002 */
	public short port;
	/**
	 * \u670d\u52a1URL\u3002<br>
	 * C type : char[102]
	 */
	public byte[] url = new byte[102];
	public HttpClientParam() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("ip", "port", "url");
	}
	/**
	 * @param ip \u670d\u52a1\u5668IP\u5730\u5740\u3002<br>
	 * C type : char[16]<br>
	 * @param port \u670d\u52a1\u5668\u7aef\u53e3\u3002<br>
	 * @param url \u670d\u52a1URL\u3002<br>
	 * C type : char[102]
	 */
	public HttpClientParam(byte ip[], short port, byte url[]) {
		super();
		if ((ip.length != this.ip.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.ip = ip;
		this.port = port;
		if ((url.length != this.url.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.url = url;
	}
	public static class ByReference extends HttpClientParam implements Structure.ByReference {
		
	};
	public static class ByValue extends HttpClientParam implements Structure.ByValue {
		
	};
}
