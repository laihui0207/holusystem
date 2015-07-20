<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="departmentDetail.title"/></title>
    <meta name="menu" content="DepartmentMenu"/>
    <meta name="heading" content="<fmt:message key='departmentDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="departmentList.department"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="departmentDetail.heading"/></h2>
    <fmt:message key="departmentDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="department" method="post" action="departmentform" cssClass="well"
           id="departmentForm" onsubmit="return validateDepartment(this)">
<form:hidden path="id"/>
    <spring:bind path="department.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="company" items="companyList" itemLabel="label" itemValue="value"/>
    <spring:bind path="department.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <spring:bind path="department.departmentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.departmentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentID" id="departmentID"  maxlength="255"/>
        <form:errors path="departmentID" cssClass="help-block"/>
    </div>
    <spring:bind path="department.level">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.level" styleClass="control-label"/>
        <form:input cssClass="form-control" path="level" id="level"  maxlength="255"/>
        <form:errors path="level" cssClass="help-block"/>
    </div>
    <spring:bind path="department.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="parent" items="parentList" itemLabel="label" itemValue="value"/>
    <spring:bind path="department.positionGPS">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.positionGPS" styleClass="control-label"/>
        <form:input cssClass="form-control" path="positionGPS" id="positionGPS"  maxlength="255"/>
        <form:errors path="positionGPS" cssClass="help-block"/>
    </div>
    <spring:bind path="department.departmentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.departmentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentID" id="departmentID"  maxlength="255"/>
        <form:errors path="departmentID" cssClass="help-block"/>
    </div>
    <spring:bind path="department.departmentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.departmentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentID" id="departmentID"  maxlength="255"/>
        <form:errors path="departmentID" cssClass="help-block"/>
    </div>
    <spring:bind path="department.departmentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="department.departmentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="departmentID" id="departmentID"  maxlength="255"/>
        <form:errors path="departmentID" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty department.id}">
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

<v:javascript formName="department" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['departmentForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
