<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="groupDetail.title"/></title>
    <meta name="menu" content="GroupMenu"/>
    <meta name="heading" content="<fmt:message key='groupDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="groupList.group"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="groupDetail.heading"/></h2>
    <fmt:message key="groupDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="group" method="post" action="groupform" cssClass="well"
           id="groupForm" onsubmit="return validateGroup(this)">
<form:hidden path="id"/>
    <spring:bind path="group.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
<%--    <form:select cssClass="form-control" path="company" items="companyList" itemLabel="label" itemValue="value"/>--%>
    <spring:bind path="group.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
<%--    <form:select cssClass="form-control" path="creater" items="createrList" itemLabel="label" itemValue="value"/>--%>
    <!-- todo: change this to read the identifier field from the other pojo -->
<%--    <form:select cssClass="form-control" path="department" items="departmentList" itemLabel="label" itemValue="value"/>--%>
    <spring:bind path="group.groupID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.groupID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="groupID" id="groupID"  maxlength="255"/>
        <form:errors path="groupID" cssClass="help-block"/>
    </div>
    <spring:bind path="group.name">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="group.groupID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.groupID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="groupID" id="groupID"  maxlength="255"/>
        <form:errors path="groupID" cssClass="help-block"/>
    </div>
    <spring:bind path="group.groupID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="group.groupID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="groupID" id="groupID"  maxlength="255"/>
        <form:errors path="groupID" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty group.id}">
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

<v:javascript formName="group" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['groupForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
