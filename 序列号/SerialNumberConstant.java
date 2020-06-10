package com.smart.site.common.utils.serialNumber.constant;

import java.util.HashMap;

/**
 * @author zhilei.wang
 *
 */
public class SerialNumberConstant {
	public static final String CATEGORY_DRAWING = "TZAQJZ"; // 图纸
	public static final String CATEGORY_DRAWINGPOINT = "WXAQ"; // 危险点
	public static final String CATEGORY_SAFECHECK = "GXAQJC"; // 安全检查
	public static final String CATEGORY_SAFERECIFY = "ZGGXAQ"; // 整改闭环
	public static final String CATEGORY_SAFEINSPECTION = "WXAQXC"; // 安全巡查
	
	public static final String CATEGORY_COMPANY = "JG"; // 企业机构
	public static final String CATEGORY_USER = "RYXX"; // 人员信息
	public static final String CATEGORY_PROJECT = "XMXX"; // 项目信息
	public static final String CATEGORY_SAFEDISCLOSURE_CONTENT = "MBJDJZ"; // 安全交底模板
	public static final String CATEGORY_SAFEDISCLOSURE = "BGJDJZ"; // 安全交底
	public static final String CATEGORY_EQU_REGISTER = "SBAQDJ"; // 安全设备登记
	public static final String CATEGORY_EQU_CLAIM = "SBAQSY"; // 安全设备使用
	public static final String CATEGORY_BANZU = "BZ"; // 班主
	public static final String CATEGORY_PROJECT_COMPANY = "JGXM"; // 项目机构

	public static final String CATEGORY_FILE_IN = "ispm"; // 来件
	public static final String CATEGORY_TEAM = "BZ"; // 来件
	

	public static final HashMap<String, String> drawingPointType;
	public static final HashMap<String, String> companyType;
	public static final HashMap<String, String> worksType;
	public static final HashMap<String, HashMap<String, String>> TYPEGENERATOR;


	// 材料点验前缀
	public static final String CLDY = "CLDY";


	static {
		drawingPointType = new HashMap<String, String>();
		/*
		 * sign_type_1:危险点 sign_type_2:围栏 sign_type_3:危大工程
		 */
		drawingPointType.put("sign_type_1", "WX");
		drawingPointType.put("sign_type_2", "WL");
		drawingPointType.put("sign_type_3", "WD");

		/*
		 * 企业类型
		 */
		companyType = new HashMap<>();
		companyType.put("sys_office_type_1", "QY");
		companyType.put("sys_office_type_2", "FZ");
		companyType.put("sys_office_type_3", "FZ");
		
		/*
		 * 安全交底模板类型,交底,班组
		 * **/
		worksType=new HashMap<>();
		
		worksType.put("works_type_1", "QY");
		worksType.put("works_type_2", "AQ");
		worksType.put("works_type_3", "JC");
		worksType.put("works_type_4", "MP");
		worksType.put("works_type_5", "JJ");
		worksType.put("works_type_6", "GH");
		worksType.put("works_type_7", "GJ");
		worksType.put("works_type_8", "JX");
		worksType.put("works_type_9", "ZX");
		worksType.put("works_type_10", "GG");
		worksType.put("works_type_11", "QT");
		
		TYPEGENERATOR = new HashMap<String, HashMap<String, String>>();
		TYPEGENERATOR.put(CATEGORY_DRAWINGPOINT, drawingPointType);
		TYPEGENERATOR.put(CATEGORY_COMPANY, companyType);
		TYPEGENERATOR.put(CATEGORY_SAFEDISCLOSURE_CONTENT, worksType);
		TYPEGENERATOR.put(CATEGORY_SAFEDISCLOSURE, worksType);
		TYPEGENERATOR.put(CATEGORY_BANZU, worksType);
	}
}
