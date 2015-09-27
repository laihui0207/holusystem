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
    <spring:bind path="project.brokerName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.brokerName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="brokerName" id="brokerName"  maxlength="255"/>
        <form:errors path="brokerName" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="company" items="companyList" itemLabel="label" itemValue="value"/>
    <spring:bind path="project.endDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.endDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date" datepicker="true"/>
        <form:errors path="endDate" cssClass="help-block"/>
    </div>
    <spring:bind path="project.fullName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.fullName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="fullName" id="fullName"  maxlength="255"/>
        <form:errors path="fullName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.note">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.note" styleClass="control-label"/>
        <form:input cssClass="form-control" path="note" id="note"  maxlength="255"/>
        <form:errors path="note" cssClass="help-block"/>
    </div>
    <spring:bind path="project.ownerName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.ownerName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="ownerName" id="ownerName"  maxlength="255"/>
        <form:errors path="ownerName" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="parent" items="parentList" itemLabel="label" itemValue="value"/>
    <spring:bind path="project.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>
    <spring:bind path="project.projectLevel">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.projectLevel" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectLevel" id="projectLevel"  maxlength="255"/>
        <form:errors path="projectLevel" cssClass="help-block"/>
    </div>
    <spring:bind path="project.projectName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.projectName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="projectName" id="projectName"  maxlength="255"/>
        <form:errors path="projectName" cssClass="help-block"/>
    </div>
    <spring:bind path="project.startDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.startDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date" datepicker="true"/>
        <form:errors path="startDate" cssClass="help-block"/>
    </div>
    <spring:bind path="project.totalCost">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.totalCost" styleClass="control-label"/>
        <form:input cssClass="form-control" path="totalCost" id="totalCost"  maxlength="255"/>
        <form:errors path="totalCost" cssClass="help-block"/>
    </div>
    <spring:bind path="project.totalWeight">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.totalWeight" styleClass="control-label"/>
        <form:input cssClass="form-control" path="totalWeight" id="totalWeight"  maxlength="255"/>
        <form:errors path="totalWeight" cssClass="help-block"/>
    </div>
    <spring:bind path="project.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>
    <spring:bind path="project.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>
    <spring:bind path="project.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="project.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
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

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['projectForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
