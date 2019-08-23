package cn.sunline.pcm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Basic;

import cn.sunline.common.KC;
import cn.sunline.common.exception.ProcessException;
import cn.sunline.common.shared.query.FetchRequest;
import cn.sunline.common.shared.query.FetchResponse;
import cn.sunline.pcm.definition.AssetSideCtrlInfo;
import cn.sunline.pcm.definition.AssetSideInfo;
import cn.sunline.pcm.definition.AssetSideRiskCtrl;
import cn.sunline.pcm.definition.BasicNetPremium;
import cn.sunline.pcm.definition.PcmOrgParameter;
import cn.sunline.pcm.definition.enums.BanceDate;
import cn.sunline.pcm.definition.enums.Expenses;
import cn.sunline.pcm.definition.enums.Settlement;
import cn.sunline.pcm.surface.api.ParameterSurface;
import cn.sunline.web.common.exception.FlatException;
import cn.sunline.web.common.utils.KW;

/** 
 * <p>
 * 基础净含保费 Controller 层
 * </p>
 * @author zzq
 * @version 1.0 2019-07-15 修改内容:初版
 */ 
@Controller
@RequestMapping("basicNetPremium")
public class BasicNetPremiumController {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ParameterSurface parameterSurface;
	

	/** 
	 * <p>
	 * 基础净含保费 列表页面
	 * </p>
	 * @param request
	 * @return
	 * @throws FlatException
	 */
	@RequestMapping("BasicNetPremiumQueryPage.in")
	public ModelAndView BasicNetPremiumQueryPage(HttpServletRequest request) throws FlatException{
		try {
			ModelAndView view = KW.mvc.forwardView("basicNetPremium/basicNetPremiumQuery");
			
			view.addObject("expensesJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.Expenses.class));
			view.addObject("settlementJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.Settlement.class));
			view.addObject("feeCollectionMethodJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.FeeCollectionMethod.class));
			view.addObject("feeBasisJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.FeeBasis.class));
			view.addObject("frequencyOfChargeJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.FrequencyTimes.class));
			view.addObject("billingCycleJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.BillingCycle.class));
			view.addObject("upperLimitControlMethodJson", KC.Enum.getI18nLabelMapJson(cn.sunline.pcm.definition.enums.LimitControlMethod.class));
			view.addObject("basicNetPremium", new BasicNetPremium());
			return view;
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e,"BasicNetPremium.BasicNetPremiumQueryPageFail","加载基础净含保费列表页面失败");
		}
	}
	
	/** 
	 * <p>
	 * 查询基础净含保费列表
	 * </p>
	 * @param request
	 * @return
	 * @throws FlatException
	 */
	@ResponseBody
	@RequestMapping(value="queryBasicNetPremiumList.in", method = { RequestMethod.POST })
	public FetchResponse<?> queryBasicNetPremiumList(FetchRequest request) throws FlatException{
		try {
			return parameterSurface.getFetchResponse(request, BasicNetPremium.class);
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e,"BasicNetPremium.queryBasicNetPremiumListFail","查询基础净含保费列表失败");
		}
	}

	/** 
	 * <p>
	 * 加载新增基础净含保费页面
	 * </p>
	 * @param request
	 * @return
	 * @throws FlatException
	 */
	@RequestMapping("BasicNetPremiumAddPage.in")
	public ModelAndView BasicNetPremiumAddPage(HttpServletRequest request) throws FlatException {
		try {
			ModelAndView view = KW.mvc.forwardView("basicNetPremium/basicNetPremiumAdd");
			view.addObject("feeCollectionMethod", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FeeCollectionMethod.class));				
			view.addObject("feeBasis", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FeeBasis.class));				
			view.addObject("frequencyOfCharge", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FrequencyTimes.class));				
			view.addObject("billingCycle", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.BillingCycle.class));				
			view.addObject("upperLimitControlMethod", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.LimitControlMethod.class));	
			view.addObject("banceDate", KC.Enum.getI18nLabelMap(BanceDate.class));
			view.addObject("settlement",KC.Enum.getI18nLabelMap(Settlement.class));
			view.addObject("expenses",KC.Enum.getI18nLabelMap(Expenses.class));
			//资产方
            view.addObject("assetFundSideCode", parameterSurface.getParameterObject(AssetSideInfo.class)
            		.stream().collect(Collectors.toMap(AssetSideInfo::getAssetSideCode,AssetSideInfo::getAssetSideDesc)));
            //所属机构
            view.addObject("orgCode", parameterSurface.getParameterObject(PcmOrgParameter.class)
            		.stream().collect(Collectors.toMap(PcmOrgParameter::getOrgCode,PcmOrgParameter::getValue)));
			view.addObject("basicNetPremium", new BasicNetPremium());
			return view;
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e,"BasicNetPremium.BasicNetPremiumAddPageFail","加载新增基础净含保费页面异常");
		}
	}

	/** 
	 * <p>
	 * 新增基础净含保费
	 * </p>
	 * @param BasicNetPremium
	 * @throws FlatException
	 */
	@ResponseBody
	@RequestMapping(value = "addBasicNetPremium.in", method = { RequestMethod.POST })
	public void addBasicNetPremium(BasicNetPremium basicNetPremium) throws FlatException {
		try {
			parameterSurface.addNewParameter(basicNetPremium.getCode(), basicNetPremium);
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e, "BasicNetPremium.addBasicNetPremiumFail", "新增基础净含保费失败");
		}
	}

	/** 
	 * <p>
	 * 加载修改基础净含保费页面
	 * </p>
	 * @param id
	 * @param request
	 * @return
	 * @throws FlatException
	 */
	@RequestMapping("BasicNetPremiumEditPage.in")
	public ModelAndView BasicNetPremiumEditPage(String code, HttpServletRequest request) throws FlatException {
		try {
			ModelAndView view = KW.mvc.forwardView("basicNetPremium/basicNetPremiumEdit");
			view.addObject("feeCollectionMethod", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FeeCollectionMethod.class));				
			view.addObject("feeBasis", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FeeBasis.class));				
			view.addObject("frequencyOfCharge", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.FrequencyTimes.class));				
			view.addObject("billingCycle", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.BillingCycle.class));				
			view.addObject("upperLimitControlMethod", KC.Enum.getI18nLabelMap(cn.sunline.pcm.definition.enums.LimitControlMethod.class));				
			BasicNetPremium basicNetPremium = parameterSurface.getParameterObject(code, BasicNetPremium.class);
			view.addObject("basicNetPremium",basicNetPremium);
			view.addObject("assetFundSideCode", parameterSurface.getParameterObject(AssetSideInfo.class)
            		.stream().collect(Collectors.toMap(AssetSideInfo::getAssetSideCode,AssetSideInfo::getAssetSideDesc)));
			view.addObject("banceDate", KC.Enum.getI18nLabelMap(BanceDate.class));
			view.addObject("settlement",KC.Enum.getI18nLabelMap(Settlement.class));
			view.addObject("expenses",KC.Enum.getI18nLabelMap(Expenses.class));
            //所属机构
            view.addObject("orgCode", parameterSurface.getParameterObject(PcmOrgParameter.class)
            		.stream().collect(Collectors.toMap(PcmOrgParameter::getOrgCode,PcmOrgParameter::getValue)));
			return view;
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e,"BasicNetPremium.BasicNetPremiumEditPageFail","加载修改基础净含保费页面失败");
		}
	}

	/** 
	 * <p>
	 * 修改基础净含保费
	 * </p>
	 * @param BasicNetPremium
	 * @throws FlatException
	 */
	@ResponseBody
	@RequestMapping(value = "updBasicNetPremium.in", method = { RequestMethod.POST })
	public void updBasicNetPremium(BasicNetPremium BasicNetPremium) throws FlatException {
		try {
			parameterSurface.updateParameterObject(BasicNetPremium.getCode(), BasicNetPremium);
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage(), BasicNetPremium.getCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e, "BasicNetPremium.updBasicNetPremiumFail", "修改基础净含保费失败");
		}
	}
	
	/** 
	 * <p>
	 * 删除基础净含保费
	 * </p>
	 * @param ids
	 */
	@ResponseBody
	@RequestMapping(value = "delBasicNetPremium.in", method = { RequestMethod.POST })
	public void delBasicNetPremiums(@RequestBody List<String> codes) throws FlatException {
		try {
			parameterSurface.deleteParameterObjectList(codes, BasicNetPremium.class);
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e, "BasicNetPremium.delBasicNetPremiumFail", "删除基础净含保费失败");
		}
	}
	
	/** 
	 * <p>
	 * 加载基础净含保费明细页面
	 * </p>
	 * @param id
	 * @return
	 * @throws FlatException
	 */
	@RequestMapping("basicNetPremiumDetailPage.in")
	public ModelAndView BasicNetPremiumDetailPage(String dcode,String code,ModelAndView view) throws FlatException {
		try {
			 view = KW.mvc.forwardView("basicNetPremium/basicNetPremiumDetail");
			 view.addObject("factory",dcode==null);
			BasicNetPremium BasicNetPremium = parameterSurface.getParameterObject(dcode==null?code:dcode, BasicNetPremium.class);
			view.addObject("basicNetPremium", BasicNetPremium);
			String orgCode = BasicNetPremium.getOrgCode();
			String newOrgCode = parameterSurface.getParameterObject(PcmOrgParameter.class).stream()
			.collect(Collectors.toMap(PcmOrgParameter::getOrgCode, PcmOrgParameter::getValue)).get(orgCode);
			BasicNetPremium.setOrgCode(newOrgCode);
			view.addObject("feeCollectionMethod", KC.Enum.getI18nLabel(BasicNetPremium.getFeeCollectionMethod()));
			view.addObject("feeBasis", KC.Enum.getI18nLabel(BasicNetPremium.getFeeBasis()));
			
			view.addObject("expenses", KC.Enum.getI18nLabel(BasicNetPremium.getExpenses()));
			view.addObject("settlement", KC.Enum.getI18nLabel(BasicNetPremium.getSettlement()));
			view.addObject("frequencyOfCharge", KC.Enum.getI18nLabel(BasicNetPremium.getFrequencyOfCharge()));
			view.addObject("billingCycle", KC.Enum.getI18nLabel(BasicNetPremium.getBillingCycle()));
			view.addObject("upperLimitControlMethod", KC.Enum.getI18nLabel(BasicNetPremium.getUpperLimitControlMethod()));
			view.addObject("downLimitControlMethod", KC.Enum.getI18nLabel(BasicNetPremium.getDownLimitControlMethod()));
			return view;
		} catch (ProcessException e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e.getErrorCode(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new FlatException(e, "BasicNetPremium.BasicNetPremiumDetailPageFail", "加载基础净含保费详情页面失败");
		}
	}
	
    /**
    *
    * @Description:    TODO(所有组件的简略信息)
    * @param:  @return
    * @param:  @throws FlatException
    * @throws
    */
   @ResponseBody
   @PostMapping(value="/unitConfig.in",produces={"application/json"})
   public Map<String,String> unitConfig() throws FlatException{
           try{
                   HashMap<String, String> resMap = new HashMap<>();
                    parameterSurface.getParameterObject(BasicNetPremium.class).forEach(
                                    item->resMap.put(item.getCode(),item.getDesc()));
                   return resMap;
           } catch (ProcessException e) {
                   logger.error(e.getMessage(), e);
                   throw new FlatException(e.getErrorCode(), e.getMessage());
           } catch (Exception e) {
                   logger.error(e.getMessage(), e);
                   throw new FlatException(e, "branch.branchunitConfigFail", "加载数据失败！");
           }

   }
  

   @ResponseBody
   @PostMapping(value="/unitBase.in")
   public List<String> unitBase(@RequestParam("code") String code){
   BasicNetPremium parameterObject = parameterSurface.getParameterObject(code,BasicNetPremium.class);
                   List<String> list = new ArrayList<>();
                   list.add(parameterObject.getCode());
                   list.add(parameterObject.getDesc());
                   list.add(KC.Enum.getI18nLabel(parameterObject.getBillingCycle()));
                   return list;
   }

	
	
	
	
	
	
	
	
}