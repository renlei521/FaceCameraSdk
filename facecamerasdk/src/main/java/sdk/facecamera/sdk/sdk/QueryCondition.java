package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:1156</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class QueryCondition extends Structure {
	/**
	 * \u4eba\u5458ID<br>
	 * C type : char[20]
	 */
	public byte[] faceID = new byte[20];
	/**
	 * \u4eba\u5458\u59d3\u540d<br>
	 * C type : char[16]
	 */
	public byte[] faceName = new byte[16];
	/** \u97e6\u6839\u534f\u8bae\u95e8\u7981\u5361\u53f7 */
	public int wgCardNO;
	/** \u6709\u6548\u671f\u622a\u6b62\u65f6\u95f4\u7684\u67e5\u8be2\u8d77\u70b9 */
	public int timeStart;
	/** \u6709\u6548\u671f\u622a\u6b62d\u65f6\u95f4\u7684\u67e5\u8be2\u7ec8\u70b9 */
	public int timeEnd;
	/** \u6709\u6548\u671f\u8d77\u59cb\u65f6\u95f4\u7684\u67e5\u8be2\u8d77\u70b9 */
	public int time1Start;
	/** \u6709\u6548\u671f\u8d77\u59cb\u65f6\u95f4\u7684\u67e5\u8be2\u7ec8\u70b9 */
	public int time1End;
	/** C type : unsigned char[256] */
	public byte[] resv = new byte[256];
	public QueryCondition() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("faceID", "faceName", "wgCardNO", "timeStart", "timeEnd", "time1Start", "time1End", "resv");
	}
	/**
	 * @param faceID \u4eba\u5458ID<br>
	 * C type : char[20]<br>
	 * @param faceName \u4eba\u5458\u59d3\u540d<br>
	 * C type : char[16]<br>
	 * @param wgCardNO \u97e6\u6839\u534f\u8bae\u95e8\u7981\u5361\u53f7<br>
	 * @param timeStart \u6709\u6548\u671f\u622a\u6b62\u65f6\u95f4\u7684\u67e5\u8be2\u8d77\u70b9<br>
	 * @param timeEnd \u6709\u6548\u671f\u622a\u6b62d\u65f6\u95f4\u7684\u67e5\u8be2\u7ec8\u70b9<br>
	 * @param time1Start \u6709\u6548\u671f\u8d77\u59cb\u65f6\u95f4\u7684\u67e5\u8be2\u8d77\u70b9<br>
	 * @param time1End \u6709\u6548\u671f\u8d77\u59cb\u65f6\u95f4\u7684\u67e5\u8be2\u7ec8\u70b9<br>
	 * @param resv C type : unsigned char[256]
	 */
	public QueryCondition(byte faceID[], byte faceName[], int wgCardNO, int timeStart, int timeEnd, int time1Start, int time1End, byte resv[]) {
		super();
		if ((faceID.length != this.faceID.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.faceID = faceID;
		if ((faceName.length != this.faceName.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.faceName = faceName;
		this.wgCardNO = wgCardNO;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
		this.time1Start = time1Start;
		this.time1End = time1End;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends QueryCondition implements Structure.ByReference {
		
	};
	public static class ByValue extends QueryCondition implements Structure.ByValue {
		
	};
}
