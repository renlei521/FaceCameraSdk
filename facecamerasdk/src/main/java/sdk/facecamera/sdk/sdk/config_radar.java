package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : config_gw.h:234</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class config_radar extends Structure {
	public int enable;
	/**
	 * @see RADAR_TYPE<br>
	 * C type : RADAR_TYPE
	 */
	public int type;
	/** \u603b\u5171\u6709\u591a\u5c11\u4e2a\u96f7\u8fbe */
	public int count;
	/**
	 * \u96f7\u8fbe\u4e0e\u8f66\u9053\u5339\u914d\uff0c\u5982match_lane[0] = 2\uff0c\u5c31\u8868\u793a\u7b2c\u4e00\u4e2a\u96f7\u8fbe\u5bf9\u5e94\u8f66\u90532<br>
	 * C type : unsigned char[(4)]
	 */
	public byte[] match_lane = new byte[4];
	/**
	 * \u96f7\u8fbe\u4e0e\u95ea\u5149\u706f\u5339\u914d\uff0cmatch_light\u7684\u4e0b\u6807n\u52a0\u4e0a1\u8868\u793a\u96f7\u8fbe\u5e8f\u53f7\uff08\u4ece1\u5f00\u59cb\uff09<br>
	 * match_light[n]\u6240\u5bf9\u5e94\u7684\u503c\u8868\u793a\u6240\u5bf9\u5e94\u7684\u95ea\u5149\u706f<br>
	 * 0x1 0x2 0x4 0x8 \u5206\u522b\u8868\u793a 1\u30012\u30013\u30014\u8def\u95ea\u5149\u706f 0x3\u5219\u8868\u793a1\u30012\u8def\u540c\u65f6\u95ea<br>
	 * 0x0\u8868\u793a\u4e0d\u95ea\u706f<br>
	 * C type : unsigned char[(4)]
	 */
	public byte[] match_light = new byte[4];
	/** \u662f\u5426\u5c06\u96f7\u8fbe\u6570\u636e\u8bb0\u5f55\u5230\u65e5\u5fd7(\u901a\u8fc7\u7f51\u7edc\u53d1\u9001) */
	public int enable_log;
	/** C type : char[64] */
	public byte[] resv = new byte[64];
	public config_radar() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("enable", "type", "count", "match_lane", "match_light", "enable_log", "resv");
	}
	/**
	 * @param type @see RADAR_TYPE<br>
	 * C type : RADAR_TYPE<br>
	 * @param count \u603b\u5171\u6709\u591a\u5c11\u4e2a\u96f7\u8fbe<br>
	 * @param match_lane \u96f7\u8fbe\u4e0e\u8f66\u9053\u5339\u914d\uff0c\u5982match_lane[0] = 2\uff0c\u5c31\u8868\u793a\u7b2c\u4e00\u4e2a\u96f7\u8fbe\u5bf9\u5e94\u8f66\u90532<br>
	 * C type : unsigned char[(4)]<br>
	 * @param match_light \u96f7\u8fbe\u4e0e\u95ea\u5149\u706f\u5339\u914d\uff0cmatch_light\u7684\u4e0b\u6807n\u52a0\u4e0a1\u8868\u793a\u96f7\u8fbe\u5e8f\u53f7\uff08\u4ece1\u5f00\u59cb\uff09<br>
	 * match_light[n]\u6240\u5bf9\u5e94\u7684\u503c\u8868\u793a\u6240\u5bf9\u5e94\u7684\u95ea\u5149\u706f<br>
	 * 0x1 0x2 0x4 0x8 \u5206\u522b\u8868\u793a 1\u30012\u30013\u30014\u8def\u95ea\u5149\u706f 0x3\u5219\u8868\u793a1\u30012\u8def\u540c\u65f6\u95ea<br>
	 * 0x0\u8868\u793a\u4e0d\u95ea\u706f<br>
	 * C type : unsigned char[(4)]<br>
	 * @param enable_log \u662f\u5426\u5c06\u96f7\u8fbe\u6570\u636e\u8bb0\u5f55\u5230\u65e5\u5fd7(\u901a\u8fc7\u7f51\u7edc\u53d1\u9001)<br>
	 * @param resv C type : char[64]
	 */
	public config_radar(int enable, int type, int count, byte match_lane[], byte match_light[], int enable_log, byte resv[]) {
		super();
		this.enable = enable;
		this.type = type;
		this.count = count;
		if ((match_lane.length != this.match_lane.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.match_lane = match_lane;
		if ((match_light.length != this.match_light.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.match_light = match_light;
		this.enable_log = enable_log;
		if ((resv.length != this.resv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.resv = resv;
	}
	public static class ByReference extends config_radar implements Structure.ByReference {
		
	};
	public static class ByValue extends config_radar implements Structure.ByValue {
		
	};
}
