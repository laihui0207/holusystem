<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="customGroupDetail.title"/></title>
    <meta name="menu" content="CustomGroupMenu"/>
    <meta name="heading" content="<fmt:message key='customGroupDetail.heading'/>"/>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap-multiselect.css'/>" type="text/css"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="customGroupList.customGroup"/></c:set>
<script type="text/javascript">var msgDelConfirm =
        "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="customGroupDetail.heading"/></h2>
    <fmt:message key="customGroupDetail.message"/>
</div>

<div class="col-sm-6">
    <form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
    <form:form commandName="customGroup" method="post" action="customGroupform" cssClass="well"
               id="customGroupForm" onsubmit="return validateCustomGroup(this)">
        <form:hidden path="id"/>
        <spring:bind path="customGroup.groupID">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="customGroup.groupID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="groupID" id="groupID" maxlength="255"/>
        <form:errors path="groupID" cssClass="help-block"/>
        </div>
        <spring:bind path="customGroup.name">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="customGroup.name" styleClass="control-label"/>
        <form:input cssClass="form-control" path="name" id="name" maxlength="255"/>
        <form:errors path="name" cssClass="help-block"/>
        </div>
        <spring:bind path="customGroup.comment">
            <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
        </spring:bind>
        <appfuse:label key="customGroup.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment" maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
        </div>

        <div class="form-group">
            <appfuse:label key="customGroup.company" styleClass="control-label"/>
            <form:select cssClass="form-control" path="company.companyId" items="${companyList}"
                         itemLabel="companyFullName" itemValue="companyId"/>
        </div>
        <div class="form-group">
            <appfuse:label key="customGroup.department" styleClass="control-label"/>
            <form:select cssClass="form-control" path="department.departmentID" items="${departmentList}" itemLabel="name"
                         itemValue="departmentID"/>
        </div>
        <div class="form-group">
            <appfuse:label key="userGroup.members" styleClass="control-label"/>:<br>
            <form:select cssClass="form-control" id="members" multiple="true" path="members" items="${userList}"
                         itemLabel="username" itemValue="userID"/>
        </div>
        <div class="form-group">
            <c:if test="${user.allowCreateGroup}">
            <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
                <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
            </button>
            </c:if>
            <c:if test="${not empty customGroup.id}">
                <button type="submit" class="btn btn-danger" id="delete" name="delete"
                        onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                    <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
                </button>
            </c:if>

            <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
                <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
            </button>
        </div>
    </form:form>
</div>

<v:javascript formName="customGroup" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/multieselect/bootstrap-multiselect.js'/>"></script>

<link rel="stylesheet" type="text/css" media="all"
      href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>"/>
<script type="text/javascript"
        src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function () {
        $("input[type='text']:visible:enabled:first", document.forms['customGroupForm']).focus();
        $('.text-right.date').datepicker({
            format: "<fmt:message key='calendar.format'/>",
            weekStart: "<fmt:message key='calendar.weekstart'/>",
            language: '${pageContext.request.locale.language}'
        });
        $("#members").multiselect({numberDisplayed:20});
    });
</script>
