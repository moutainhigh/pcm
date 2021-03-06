package cn.sunline.pcm.definition.enums;

import cn.sunline.common.annotation.enums.EnumInfo;

/**
 * 全额还款容忍度标志：
 */
@EnumInfo({
	"R|按比例计算",
	"A|按金额计算",
	"B|比例和金额同时考虑"
})
public enum DownpmtTolInd{
	/**
	 * 按比例计算
	 */
	R,
	/**
	 * 按金额计算
	 */
	A,
	/**
	 * 比例和金额同时考虑
	 */
	B}
