package com.smart.site.common.utils.serialNumber;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smart.site.common.utils.JedisClusterUtils;
import com.smart.site.common.utils.serialNumber.constant.SerialNumberConstant;
import com.smart.site.modules.sys.service.SysSeqService;

/**
 * 序列号工具类
 * 根据项目+type作为区分
 * @author qiang.zhang
 *
 */

@Component
public class SerialNumberUtils {
	
	@Autowired
	SysSeqService sysSeqService;
	
	public static final int defaultLength = 8;
	public static final String PREFIX = "REDIS_NO:NO_";
	
	private static SerialNumberUtils instance;
	
	@PostConstruct
	private void postConstruct() {
		instance = this;
	}
	
	public static String get(String projectId, String category) {
		return get(projectId, category, null);
	}
	
	public static String get(String projectId, String category, String type) {
		StringBuilder numberBuilder = new StringBuilder();
		numberBuilder.append(category);
		String prefix = makePrefixDB(projectId, category, type);
		// 处理type
		if (StringUtils.isNoneBlank(type)) {
			numberBuilder.append(typeGenerate(category, type));
		}
		// 根据前缀从数据库中获取流水递增 by yangxiaohua on 20200418
		int nextNum = instance.sysSeqService.getNextNum(prefix, projectId);
		numberBuilder.append(lengthGrow(String.valueOf(nextNum)));
		//修改禅道bug：353  之前的数据不做处理，新生成的code去掉null
		return numberBuilder.toString().replace("null", "");
	}
	
	/**
	 * 前缀规则：当前项目+类型
	 * 其中类型传业务类型，走mapping映射，序列号相关都走一个地方，改也好改
	 * @param projectId
	 * @param type
	 * @return
	 */
	public static String makePrefix(String projectId, String category, String type) {
		StringBuilder prefixBuilder = new StringBuilder(PREFIX);
		prefixBuilder.append(projectId).append("_").append(category);
		// 处理type
		if (StringUtils.isNoneBlank(type)) {
			prefixBuilder.append("_").append(typeGenerate(category, type));
		}
		return prefixBuilder.toString();
	}
	
	public static String makePrefixDB(String projectId, String category, String type) {
		StringBuilder prefixBuilder = new StringBuilder(128)
				.append(category).append("_").append(projectId);
		// 处理type
		if (StringUtils.isNoneBlank(type)) {
			prefixBuilder.append("_").append(typeGenerate(category, type));
		}
		return prefixBuilder.toString();
	}

	/**
	 * 转业务类型
	 * @param category
	 * @param type
	 * @return
	 */
	public static String typeGenerate(String category, String type) {
		return SerialNumberConstant.TYPEGENERATOR.get(category).get(type);
	}
	
	/**
	 * 按默认长度左补零
	 * @param str
	 * @return
	 */
	public static String lengthGrow(String str) {
		return lengthGrow(str, defaultLength);
	}
	
	/**
	 * 左补零
	 * @param str
	 * @param len
	 * @return
	 */
	public static String lengthGrow(String str, int len) {
		//StringBuilder sb = new StringBuilder(str);
		//while (sb.length() < len) {
		//	sb.insert(0, "0");
		//}
		//return sb.toString();
		
		// by yangxiaohua on 20200416
		if (str.length() >= len) {
			return str;
		} else {
			StringBuilder sb = new StringBuilder("0000000000000000");
			int padLen = len - str.length();
			while(sb.length() < padLen) {
				sb.append('0');
			}
			sb.replace(padLen, len, str);
			sb.setLength(len);
			return sb.toString();
		}
	}
	
	public static void _main(String[] args) throws Exception {
		System.out.println(SerialNumberUtils.lengthGrow("1", 8));
		System.out.println(SerialNumberUtils.lengthGrow("12", 8));
		System.out.println(SerialNumberUtils.lengthGrow("123", 12));
		System.out.println(SerialNumberUtils.lengthGrow("1234", 32));
	}
}
