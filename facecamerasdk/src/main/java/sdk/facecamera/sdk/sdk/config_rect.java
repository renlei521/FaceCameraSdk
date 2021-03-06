package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:162</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class config_rect extends Structure {
	public short x;
	public short y;
	public short w;
	public short h;
	public config_rect() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("x", "y", "w", "h");
	}
	public config_rect(short x, short y, short w, short h) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public static class ByReference extends config_rect implements Structure.ByReference {
		
	};
	public static class ByValue extends config_rect implements Structure.ByValue {
		
	};
}
