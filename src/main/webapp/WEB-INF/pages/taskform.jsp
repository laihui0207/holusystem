<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="taskDetail.title"/></title>
    <meta name="menu" content="TaskMenu"/>
    <meta name="heading" content="<fmt:message key='taskDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="taskList.task"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="taskDetail.heading"/></h2>
    <fmt:message key="taskDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="task" method="post" action="taskform" cssClass="well"
           id="taskForm" onsubmit="return validateTask(this)">
<form:hidden path="id"/>
    <spring:bind path="task.acceptPush">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.acceptPush" styleClass="control-label"/>
        <form:input cssClass="form-control" path="acceptPush" id="acceptPush"  maxlength="255"/>
        <form:errors path="acceptPush" cssClass="help-block"/>
    </div>
    <spring:bind path="task.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="createUser" items="createUserList" itemLabel="label" itemValue="value"/>
    <spring:bind path="task.endCount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.endCount" styleClass="control-label"/>
        <form:input cssClass="form-control" path="endCount" id="endCount"  maxlength="255"/>
        <form:errors path="endCount" cssClass="help-block"/>
    </div>
    <spring:bind path="task.endDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.endDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date" datepicker="true"/>
        <form:errors path="endDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="processDictionary" items="processDictionaryList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="project" items="projectList" itemLabel="label" itemValue="value"/>
    <spring:bind path="task.recordId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.recordId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="recordId" id="recordId"  maxlength="255"/>
        <form:errors path="recordId" cssClass="help-block"/>
    </div>
    <spring:bind path="task.startCount">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.startCount" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startCount" id="startCount"  maxlength="255"/>
        <form:errors path="startCount" cssClass="help-block"/>
    </div>
    <spring:bind path="task.startDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.startDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date" datepicker="true"/>
        <form:errors path="startDate" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="subComponentList" items="subComponentListList" itemLabel="label" itemValue="value"/>
    <spring:bind path="task.taskId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.taskId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="taskId" id="taskId"  maxlength="255"/>
        <form:errors path="taskId" cssClass="help-block"/>
    </div>
    <spring:bind path="task.taskName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.taskName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="taskName" id="taskName"  maxlength="255"/>
        <form:errors path="taskName" cssClass="help-block"/>
    </div>
    <spring:bind path="task.taskStyle">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="task.taskStyle" styleClass="control-label"/>
        <form:input cssClass="form-control" path="taskStyle" id="taskStyle"  maxlength="255"/>
        <form:errors path="taskStyle" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty task.id}">
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

<v:javascript formName="task" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['taskForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
