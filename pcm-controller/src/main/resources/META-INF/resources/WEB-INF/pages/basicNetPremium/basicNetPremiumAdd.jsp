<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="basicNetPremium.add.title" text="基础净含保费新增" /></title>
<!-- 引入css样式和部分js -->
<%@ include file="/common/head.jsp"%>
</head>
<!-- 整体皮肤样式 -->
<body class="${param.skin}">
	<!-- data-confirm=true 提交前需要确认 -->
	<form:form cssClass="form-horizontal" id="basicNetPremiumAddForm" cssStyle="padding-top: 40px" modelAttribute="basicNetPremium" method="post" action="${ctx}/basicNetPremium/addBasicNetPremium.in" data-confirm="true">
		<div class="form-group row">
			<!-- 流量服务费 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<span class="span-icon">*&nbsp;</span>
				<spring:message code="basicNetPremium.code" text="流量服务费" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="code"  data-rule-required="true" data-rule-maxlength="15" />
			</div>
			<!-- 流量服务费描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.code.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 流量服务描述 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<span class="span-icon">*&nbsp;</span>
				<spring:message code="basicNetPremium.desc" text="流量服务描述" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="desc"  data-rule-required="true" data-rule-maxlength="50" />
			</div>
			<!-- 流量服务描述描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.desc.desc" />
			</div>
		</div>
		
		<div class="form-group row">
			<!-- 费用收取方式 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<span class="span-icon">*&nbsp;</span>
				<spring:message code="basicNetPremium.feeCollectionMethod" text="费用收取方式" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:select cssClass="form-control" path="feeCollectionMethod"  data-rule-required="true">
					<form:options items="${feeCollectionMethod}" />
				</form:select>
			</div>
			<!-- 费用收取方式描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.feeCollectionMethod.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 费用收取基础 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.feeBasis" text="费用收取基础" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:select cssClass="form-control" path="feeBasis">
					<form:options items="${feeBasis}" />
				</form:select>
			</div>
			<!-- 费用收取基础描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.feeBasis.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 费用收取频次 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.frequencyOfCharge" text="费用收取频次" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:select cssClass="form-control" path="frequencyOfCharge">
					<form:options items="${frequencyOfCharge}" />
				</form:select>
			</div>
			<!-- 费用收取频次描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.frequencyOfCharge.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 收取比例 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.chargeRatio" text="收取比例" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="number" path="chargeRatio" data-rule-digits="true" data-rule-min="0" data-rule-max="999999999999" />
			</div>
			
			<!-- 收取比例描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.chargeRatio.desc" />
			</div>
		</div>
		
		<div class="form-group row">
            <!-- 费用累计月天数 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.expenses" text="费用累计月天数" />
				:
			</label>
            <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
                <form:select cssClass="form-control" path="expenses">
                	<option value=""><spring:message code="kite.web.common.pleaseChoose" text="--请选择--" /></option>
                    <form:options items="${expenses}" />
                </form:select>
            </div>
            <!-- 资产方描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.expenses.desc" />
			</div>
        </div>
		
		
		
		<div class="form-group row">
            <!-- 所属机构 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.orgCode" text="机构编号" />
				:
			</label>
              <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
                <form:select cssClass="form-control" path="orgCode">
                	<option value=""><spring:message code="kite.web.common.pleaseChoose" text="--请选择--" /></option>
                    <form:options items="${orgCode}" />
                </form:select>
            </div>
            <!-- 所属机构 描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.orgCode.desc" />
			</div>
        </div>
		
		<div class="form-group row">
            <!-- 资产方 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.assetSideCode" text="资产方" />
				:
			</label>
            <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
                <form:select cssClass="form-control" path="assetSideCode">
                	<option value=""><spring:message code="kite.web.common.pleaseChoose" text="--请选择--" /></option>
                    <form:options items="${assetFundSideCode}" />
                </form:select>
            </div>
            <!-- 资产方描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.assetSideCode.desc" />
			</div>
        </div>
		
		<div class="form-group row">
			<!-- 转出账号 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.transferAccount" text="转出账号" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="transferAccount" />
			</div>
			<!-- 转出账号描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.transferAccount.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 转入账号 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.transferToAccount" text="转入账号" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="transferToAccount"/>
			</div>
			<!-- 转入账号描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.transferToAccount.desc" />
			</div>
		</div>

		<div class="form-group row">
			<!-- 上限控制方式 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.upperLimitControlMethod" text="上限控制方式" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:select cssClass="form-control" path="upperLimitControlMethod">
					<form:options items="${upperLimitControlMethod}" />
				</form:select>
			</div>
			<!-- 上限控制方式描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.upperLimitControlMethod.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 上限控制比例 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.upperLimitControlRatio" text="上限控制比例" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="upperLimitControlRatio" data-rule-digits="true" data-rule-min="0" data-rule-max="999999999999" />
			</div>
			<!-- 上限控制比例描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.upperLimitControlRatio.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 上限控制金额 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.upperControlAmount" text="上限控制金额" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:input cssClass="form-control" type="text" path="upperControlAmount" data-rule-digits="true" data-rule-min="0" data-rule-max="999999999999" />
			</div>
			<!-- 上限控制金额描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.upperControlAmount.desc" />
			</div>
		</div>
		<div class="form-group row">
			<!-- 结算周期 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<span class="span-icon">*&nbsp;</span>
				<spring:message code="basicNetPremium.balanceDate" text="结算周期" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<form:select cssClass="form-control" path="billingCycle"  data-rule-required="true">
					<form:options items="${billingCycle}" />
				</form:select>
			</div>
			<!-- 结算周期描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.billingCycle.desc" />
			</div>
		</div>
		
		
		<div class="form-group row">
		<!-- 结算日期 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.balanceDate" text="结算日期" />
				:
			</label>
			<div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
				<input id="balanceDate" name="balanceDate" class='form-control customize-datetime' type="text" data-picker-position="top-right" data-role-formate="yyyy-mm-dd"  onfocus="this.blur()" disabled/>
			</div>
			 <!-- 结算日期描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.balanceDate.desc" />
			</div>
		</div>

		
		<div class="form-group row">
            <!-- 放款当天提前结清费用收取方式 -->
			<label class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label">
				<spring:message code="basicNetPremium.settlement" text="提前结清费用收取方式" />
				:
			</label>
            <div class="col-lg-7 col-md-7 col-sm-6 col-xs-6">
                <form:select cssClass="form-control" path="settlement">
                	<option value=""><spring:message code="kite.web.common.pleaseChoose" text="--请选择--" /></option>
                   <form:options items="${settlement}" />
                </form:select>
            </div>
            <!-- 资产方描述 -->
			<div class="col-lg-3 col-md-3 col-sm-4 col-xs-4 remark">
				<spring:message code="basicNetPremium.settlement.desc" />
			</div>
        </div>
		<div class="form-controls auto-float">
			<div class="btn-group-md">
				<!-- 确定 -->
				<input type="submit" class="btn-primary btn" value="<spring:message code='kite.web.common.btnSure' text='确定' />" />
				<!-- 返回 -->
				<input type="button" class="btn-default btn only-slide-out" value="<spring:message code='kite.web.common.btnBack' text='返回' />" />
			</div>
		</div>
	</form:form>
	<!-- 引入js文件-->
	<%@ include file="/common/foot.jsp"%>
	<script type="text/javascript">
		//开启表单验证
		$("#basicNetPremiumAddForm").validate();
		
		$('#billingCycle').change(function(){
			var value = $(this).val();
			if(value=='A'){
				var zzqParent = $('#balanceDate').parent('div');
				zzqParent.empty();
				zzqParent.append('<input id="balanceDate" name="balanceDate"  class="form-control" type="text"   />')
				$('#balanceDate').attr("disabled",true); 
			}else if(value=='Z'){
				var zzqParent = $('#balanceDate').parent('div');
				zzqParent.empty();
				zzqParent.append('<select id="balanceDate" name="balanceDate"  class="form-control">'
						<c:forEach items="${banceDate}" var="item"> 
	  					+'<option value="${item.value}">${item.value}</option>'
	  				</c:forEach>
				+'<select>');
			}else if(value=='C'){
				var zzqParent = $('#balanceDate').parent('div');
				zzqParent.empty();
				zzqParent.append('<input id="balanceDate" name="balanceDate"  class="form-control customize-datetime" type="text" data-picker-position="top-right" data-role-formate="dd"  onfocus="this.blur()" />')
				$('#balanceDate').datepicker({
					autoclose : true,
					format: 'dd',
				})
			}else if(value=='I'){
				$('#balanceDate').datepicker('destroy')
				var zzqParent = $('#balanceDate').parent('div');
				zzqParent.empty();
				zzqParent.append('<input id="balanceDate"  name="balanceDate" class="form-control" type="text"   />')
				$('#balanceDate').datepicker({
					autoclose : true,
					format: 'mm-dd',
				})
			}else{
				$('#balanceDate').attr("disabled",false); 
			}
		})
	</script>
</body>
</html>