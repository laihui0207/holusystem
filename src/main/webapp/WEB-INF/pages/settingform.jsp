<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="settingDetail.title"/></title>
    <meta name="menu" content="SettingMenu"/>
    <meta name="heading" content="<fmt:message key='settingDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="settingList.setting"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="settingDetail.heading"/></h2>
    <fmt:message key="settingDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="setting" method="post" action="settingform" cssClass="well"
           id="settingForm" onsubmit="return validateSetting(this)">
<form:hidden path="id"/>
    <spring:bind path="setting.companyID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.companyID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyID" id="companyID"  maxlength="255"/>
        <form:errors path="companyID" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyArea">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyArea" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyArea" id="keyArea"  maxlength="255"/>
        <form:errors path="keyArea" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyName" id="keyName"  maxlength="255"/>
        <form:errors path="keyName" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyValue">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyValue" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyValue" id="keyValue"  maxlength="255"/>
        <form:errors path="keyValue" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyValue1">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyValue1" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyValue1" id="keyValue1"  maxlength="255"/>
        <form:errors path="keyValue1" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyValue2">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyValue2" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyValue2" id="keyValue2"  maxlength="255"/>
        <form:errors path="keyValue2" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.keyValue3">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.keyValue3" styleClass="control-label"/>
        <form:input cssClass="form-control" path="keyValue3" id="keyValue3"  maxlength="255"/>
        <form:errors path="keyValue3" cssClass="help-block"/>
    </div>
    <spring:bind path="setting.userID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="setting.userID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="userID" id="userID"  maxlength="255"/>
        <form:errors path="userID" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty setting.id}">
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

<v:javascript formName="setting" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['settingForm']).focus();
    });
</script>
