<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="summaryDetail.title"/></title>
    <meta name="menu" content="SummaryMenu"/>
    <meta name="heading" content="<fmt:message key='summaryDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="summaryList.summary"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="summaryDetail.heading"/></h2>
    <fmt:message key="summaryDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="summary" method="post" action="summaryform" cssClass="well"
           id="summaryForm" onsubmit="return validateSummary(this)">
<form:hidden path="id"/>
    <spring:bind path="summary.actualEnd">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.actualEnd" styleClass="control-label"/>
        <form:input cssClass="form-control" path="actualEnd" id="actualEnd"  maxlength="255"/>
        <form:errors path="actualEnd" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.actualStart">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.actualStart" styleClass="control-label"/>
        <form:input cssClass="form-control" path="actualStart" id="actualStart"  maxlength="255"/>
        <form:errors path="actualStart" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate"  maxlength="255"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="creater" items="createrList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="department" items="departmentList" itemLabel="label" itemValue="value"/>
    <spring:bind path="summary.planEnd">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.planEnd" styleClass="control-label"/>
        <form:input cssClass="form-control" path="planEnd" id="planEnd"  maxlength="255"/>
        <form:errors path="planEnd" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.planStart">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.planStart" styleClass="control-label"/>
        <form:input cssClass="form-control" path="planStart" id="planStart"  maxlength="255"/>
        <form:errors path="planStart" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.predictEnd">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.predictEnd" styleClass="control-label"/>
        <form:input cssClass="form-control" path="predictEnd" id="predictEnd"  maxlength="255"/>
        <form:errors path="predictEnd" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.predictStart">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.predictStart" styleClass="control-label"/>
        <form:input cssClass="form-control" path="predictStart" id="predictStart"  maxlength="255"/>
        <form:errors path="predictStart" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.processId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.processId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processId" id="processId"  maxlength="255"/>
        <form:errors path="processId" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.processName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.processName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processName" id="processName"  maxlength="255"/>
        <form:errors path="processName" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="project" items="projectList" itemLabel="label" itemValue="value"/>
    <spring:bind path="summary.styleId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.styleId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleId" id="styleId"  maxlength="255"/>
        <form:errors path="styleId" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.styleName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.styleName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleName" id="styleName"  maxlength="255"/>
        <form:errors path="styleName" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumDate" id="sumDate"  maxlength="255"/>
        <form:errors path="sumDate" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumMonth">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumMonth" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumMonth" id="sumMonth"  maxlength="255"/>
        <form:errors path="sumMonth" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumQuantity">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumQuantity" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumQuantity" id="sumQuantity"  maxlength="255"/>
        <form:errors path="sumQuantity" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumRight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumRight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumRight" id="sumRight"  maxlength="255"/>
        <form:errors path="sumRight" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumWeek">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumWeek" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumWeek" id="sumWeek"  maxlength="255"/>
        <form:errors path="sumWeek" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumWeight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumWeight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumWeight" id="sumWeight"  maxlength="255"/>
        <form:errors path="sumWeight" cssClass="help-block"/>
    </div>
    <spring:bind path="summary.sumYear">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="summary.sumYear" styleClass="control-label"/>
        <form:input cssClass="form-control" path="sumYear" id="sumYear"  maxlength="255"/>
        <form:errors path="sumYear" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty summary.id}">
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

<v:javascript formName="summary" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['summaryForm']).focus();
    });
</script>
