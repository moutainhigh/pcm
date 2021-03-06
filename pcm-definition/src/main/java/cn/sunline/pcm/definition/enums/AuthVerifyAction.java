package cn.sunline.pcm.definition.enums;

import cn.sunline.common.annotation.enums.EnumInfo;

/**
 * 授权验证行动
* @author fanghj
 *
 */
@EnumInfo({
	"Must|必有必验",
	"Exist|有则验" 
	 
})	
public enum AuthVerifyAction {
	 
	/**
	 * 必有必验
	 */
	Must, 
	/**
	 * 有则验
	 */
	Exist 
}
