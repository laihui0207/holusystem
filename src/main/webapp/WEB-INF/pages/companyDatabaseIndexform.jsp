<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="companyDatabaseIndexDetail.title"/></title>
    <meta name="menu" content="CompanyDatabaseIndexMenu"/>
    <meta name="heading" content="<fmt:message key='companyDatabaseIndexDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="companyDatabaseIndexList.companyDatabaseIndex"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="companyDatabaseIndexDetail.heading"/></h2>
    <fmt:message key="companyDatabaseIndexDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="companyDatabaseIndex" method="post" action="companyDatabaseIndexform" cssClass="well"
           id="companyDatabaseIndexForm" onsubmit="return validateCompanyDatabaseIndex(this)">
<form:hidden path="id"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="company" items="companyList" itemLabel="label" itemValue="value"/>
    <spring:bind path="companyDatabaseIndex.tableName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="companyDatabaseIndex.tableName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="tableName" id="tableName"  maxlength="255"/>
        <form:errors path="tableName" cssClass="help-block"/>
    </div>
    <spring:bind path="companyDatabaseIndex.tableStyle">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="companyDatabaseIndex.tableStyle" styleClass="control-label"/>
        <form:input cssClass="form-control" path="tableStyle" id="tableStyle"  maxlength="255"/>
        <form:errors path="tableStyle" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty companyDatabaseIndex.id}">
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

<v:javascript formName="companyDatabaseIndex" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['companyDatabaseIndexForm']).focus();
    });
</script>
