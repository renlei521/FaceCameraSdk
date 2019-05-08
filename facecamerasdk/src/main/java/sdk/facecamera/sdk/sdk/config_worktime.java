package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:172</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class config_worktime extends Structure {
	public byte start_hour;
	public byte start_min;
	public byte end_hour;
	public byte end_min;
	public config_worktime() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("start_hour", "start_min", "end_hour", "end_min");
	}
	public config_worktime(byte start_hour, byte start_min, byte end_hour, byte end_min) {
		super();
		this.start_hour = start_hour;
		this.start_min = start_min;
		this.end_hour = end_hour;
		this.end_min = end_min;
	}
	public static class ByReference extends config_worktime implements Structure.ByReference {
		
	};
	public static class ByValue extends config_worktime implements Structure.ByValue {
		
	};
}
