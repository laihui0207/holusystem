<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="summaryTotalDetail.title"/></title>
    <meta name="menu" content="SummaryTotalMenu"/>
    <meta name="heading" content="<fmt:message key='summaryTotalDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="summaryTotalList.summaryTotal"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="summaryTotalDetail.heading"/></h2>
    <fmt:message key="summaryTotalDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="summaryTotal" method="post" action="summaryTotalform" cssClass="well"
           id="summaryTotalForm" onsubmit="return validateSummaryTotal(this)">
<form:hidden path="id"/>
    <spring:bind path="summaryTotal.actualPlanPredict">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.actualPlanPredict" styleClass="control-label"/>
        <form:input cssClass="form-control" path="actualPlanPredict" id="actualPlanPredict"  maxlength="255"/>
        <form:errors path="actualPlanPredict" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.createUserID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.createUserID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createUserID" id="createUserID"  maxlength="255"/>
        <form:errors path="createUserID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.curDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.curDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="curDate" id="curDate" size="11" title="date" datepicker="true"/>
        <form:errors path="curDate" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.departmentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.departmentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentID" id="departmentID"  maxlength="255"/>
        <form:errors path="departmentID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.departmentPathName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.departmentPathName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentPathName" id="departmentPathName"  maxlength="255"/>
        <form:errors path="departmentPathName" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.departmentRootID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.departmentRootID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentRootID" id="departmentRootID"  maxlength="255"/>
        <form:errors path="departmentRootID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.itemName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.itemName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemName" id="itemName"  maxlength="255"/>
        <form:errors path="itemName" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.itemStyle">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.itemStyle" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemStyle" id="itemStyle"  maxlength="255"/>
        <form:errors path="itemStyle" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.processID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.processID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processID" id="processID"  maxlength="255"/>
        <form:errors path="processID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.processName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.processName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processName" id="processName"  maxlength="255"/>
        <form:errors path="processName" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.projectLevel">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.projectLevel" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectLevel" id="projectLevel"  maxlength="255"/>
        <form:errors path="projectLevel" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.itemName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.itemName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemName" id="itemName"  maxlength="255"/>
        <form:errors path="itemName" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.projectRootID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.projectRootID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectRootID" id="projectRootID"  maxlength="255"/>
        <form:errors path="projectRootID" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.startOrEnd">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.startOrEnd" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startOrEnd" id="startOrEnd"  maxlength="255"/>
        <form:errors path="startOrEnd" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumCount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumCount" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumCount" id="sumCount"  maxlength="255"/>
        <form:errors path="sumCount" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumDate" id="sumDate"  maxlength="255"/>
        <form:errors path="sumDate" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumMonth">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumMonth" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumMonth" id="sumMonth"  maxlength="255"/>
        <form:errors path="sumMonth" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumRight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumRight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumRight" id="sumRight"  maxlength="255"/>
        <form:errors path="sumRight" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumWeek">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumWeek" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumWeek" id="sumWeek"  maxlength="255"/>
        <form:errors path="sumWeek" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumWeight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumWeight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumWeight" id="sumWeight"  maxlength="255"/>
        <form:errors path="sumWeight" cssClass="help-block"/>
    </div>
    <spring:bind path="summaryTotal.sumYear">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summaryTotal.sumYear" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumYear" id="sumYear"  maxlength="255"/>
        <form:errors path="sumYear" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty summaryTotal.id}">
            <button type="submit" class="btn btn-danger" id="delete" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="summaryTotal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['summaryTotalForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
