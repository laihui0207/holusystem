<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="companyDetail.title"/></title>
    <meta name="menu" content="CompanyMenu"/>
    <meta name="heading" content="<fmt:message key='companyDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="companyList.company"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="companyDetail.heading"/></h2>
    <fmt:message key="companyDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="company" method="post" action="companyform" cssClass="well"
           id="companyForm" onsubmit="return validateCompany(this)">
<form:hidden path="id"/>
    <spring:bind path="company.comapnyAddress">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.comapnyAddress" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comapnyAddress" id="comapnyAddress"  maxlength="255"/>
        <form:errors path="comapnyAddress" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyCode">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyCode" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyCode" id="companyCode"  maxlength="255"/>
        <form:errors path="companyCode" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyFax">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyFax" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyFax" id="companyFax"  maxlength="255"/>
        <form:errors path="companyFax" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyFullName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyFullName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyFullName" id="companyFullName"  maxlength="255"/>
        <form:errors path="companyFullName" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyId" id="companyId"  maxlength="255"/>
        <form:errors path="companyId" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyMaster">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyMaster" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyMaster" id="companyMaster"  maxlength="255"/>
        <form:errors path="companyMaster" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyNature">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyNature" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyNature" id="companyNature"  maxlength="255"/>
        <form:errors path="companyNature" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyNote">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyNote" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyNote" id="companyNote"  maxlength="255"/>
        <form:errors path="companyNote" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyPositionGPS">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyPositionGPS" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyPositionGPS" id="companyPositionGPS"  maxlength="255"/>
        <form:errors path="companyPositionGPS" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyShortNameCN">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyShortNameCN" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyShortNameCN" id="companyShortNameCN"  maxlength="255"/>
        <form:errors path="companyShortNameCN" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyShortNameEN">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyShortNameEN" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyShortNameEN" id="companyShortNameEN"  maxlength="255"/>
        <form:errors path="companyShortNameEN" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyTel">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyTel" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyTel" id="companyTel"  maxlength="255"/>
        <form:errors path="companyTel" cssClass="help-block"/>
    </div>
    <spring:bind path="company.companyWebSite">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="company.companyWebSite" styleClass="control-label"/>
        <form:input cssClass="form-control" path="companyWebSite" id="companyWebSite"  maxlength="255"/>
        <form:errors path="companyWebSite" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty company.id}">
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

<v:javascript formName="company" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['companyForm']).focus();
    });
</script>
