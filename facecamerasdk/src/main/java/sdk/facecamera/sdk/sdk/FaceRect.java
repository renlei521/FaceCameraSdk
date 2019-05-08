package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : FaceRecoDef.h:175</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class FaceRect extends Structure {
	/** \u4eba\u8138\u8f6e\u5ed3id */
	public int faceId;
	/**
	 * \u4eba\u8138\u8f6e\u5ed3\u5750\u6807<br>
	 * C type : ha_rect
	 */
	public ha_rect faceRect;
	/** C type : char[4] */
	public byte[] resv = new byte[4];
	public FaceRect() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("faceId", "faceRect", "resv");
	}
	/**
	 * @param faceId \u4eba\u8138\u8f6e\u5ed3id<br>
	 * @param faceRect \u4eba\u8138\u8f6e\u5ed3\u5750\u6807<br>
	 * C type : ha_rect<br>
	 * @param resv C type : char[4]
	 */
	public FaceRect(int faceId, ha_rect faceRect, byte resv[]) {
		super();
		this.faceId = faceId;
		this.faceRect = faceRect;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends FaceRect implements Structure.ByReference {
		
	};
	public static class ByValue extends FaceRect implements Structure.ByValue {
		
	};
}
