package sdk.facecamera.sdk.sdk;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : HASdkDef.h:162</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class FeeInfo extends Structure {
	/**
	 * \u8f66\u724c<br>
	 * C type : char[16]
	 */
	public byte[] plate = new byte[16];
	/** \u5165\u573a\u65f6\u95f4 */
	public short in_year;
	public short in_month;
	public short in_day;
	public short in_hour;
	public short in_min;
	public short in_sec;
	/** \u51fa\u573a\u65f6\u95f4 */
	public short out_year;
	public short out_month;
	public short out_day;
	public short out_hour;
	public short out_min;
	public short out_sec;
	/** \u505c\u8f66\u8d39\u7528 */
	public float park_fee;
	/** \u6536\u8d39\u7c7b\u578b  0 [\u6309\u65f6\u8ba1\u8d39] 1[\u6309\u6b21\u8ba1\u8d39] 2[\u5206\u6bb5\u8ba1\u8d39] 3[\u67e5\u8868\u8ba1\u8d39] */
	public short fee_mode;
	/** \u8f66\u724c\u989c\u8272  0 \u84dd\u8272 1 \u9ed1\u8272 2 \u9ec4\u8272 3 \u767d\u8272  4\u65b0\u80fd\u6e90\u8f66\u724c\u7eff\u8272 */
	public byte platecolor;
	/** \u6708\u79df\u8f66\u8fd8\u662f\u4e34\u65f6\u8f66  0[\u672a\u7ecf\u8fc7\u5904\u7406\uff0c\u672a\u77e5] 1[\u6708\u79df\u8f66\uff0c\u4e14\u5728\u6709\u6548\u671f] 2[\u4e34\u65f6\u8f66] 3[\u65e0\u724c\u8f66] 4[\u9ed1\u540d\u5355\u8f66] 7[\u6708\u79df\u8f66\u5df2\u8fc7\u671f] */
	public byte InOut_type;
	public FeeInfo() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("plate", "in_year", "in_month", "in_day", "in_hour", "in_min", "in_sec", "out_year", "out_month", "out_day", "out_hour", "out_min", "out_sec", "park_fee", "fee_mode", "platecolor", "InOut_type");
	}
	public static class ByReference extends FeeInfo implements Structure.ByReference {
		
	};
	public static class ByValue extends FeeInfo implements Structure.ByValue {
		
	};
}
