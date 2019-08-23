package cn.sunline.pcm.definition.enums;

import cn.sunline.common.annotation.enums.EnumInfo;

/**
 * 密码锁定释放标识：
 */
@EnumInfo({
	"M|只允许人工解锁",
	"D|每日自动解锁"
})
public enum PinBlockResetInd{
	/**
	 * 人工解锁
	 */
	M,
	/**
	 * 自动解锁
	 */
	D
}
