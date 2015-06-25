<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="projectDetail.title"/></title>
    <meta name="menu" content="ProjectMenu"/>
    <meta name="heading" content="<fmt:message key='projectDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="projectList.project"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="projectDetail.heading"/></h2>
    <fmt:message key="projectDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="project" method="post" action="projectform" cssClass="well"
           id="projectForm" onsubmit="return validateProject(this)">
<form:hidden path="id"/>
    <spring:bind path="project.batchFullName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.batchFullName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="batchFullName" id="batchFullName"  maxlength="255"/>
        <form:errors path="batchFullName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.batchShortName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.batchShortName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="batchShortName" id="batchShortName"  maxlength="255"/>
        <form:errors path="batchShortName" cssClass="help-block"/>
    </div>
    <div class="form-group">
        <appfuse:label key="companyList.title" styleClass="control-label"/>
        <form:select cssClass="form-control" path="company.id" items="${companyList}" itemLabel="companyShortNameCN" itemValue="id"/>
    </div>
    <div class="form-group">
        <appfuse:label key="userList.title" styleClass="control-label"/>
        <form:select cssClass="form-control" path="owner.id" items="${userList}" itemLabel="fullName" itemValue="id"/>
        </div>
    <spring:bind path="project.projectFullName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.projectFullName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectFullName" id="projectFullName"  maxlength="255"/>
        <form:errors path="projectFullName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.projectID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.projectID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectID" id="projectID"  maxlength="255"/>
        <form:errors path="projectID" cssClass="help-block"/>
    </div>
    <spring:bind path="project.projectShortName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.projectShortName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectShortName" id="projectShortName"  maxlength="255"/>
        <form:errors path="projectShortName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.unitFullName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.unitFullName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="unitFullName" id="unitFullName"  maxlength="255"/>
        <form:errors path="unitFullName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.unitShortName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.unitShortName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="unitShortName" id="unitShortName"  maxlength="255"/>
        <form:errors path="unitShortName" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty project.id}">
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

<v:javascript formName="project" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['projectForm']).focus();
    });
</script>
