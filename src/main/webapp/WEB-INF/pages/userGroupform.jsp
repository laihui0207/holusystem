<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userGroupDetail.title"/></title>
    <meta name="menu" content="UserGroupMenu"/>
    <meta name="heading" content="<fmt:message key='userGroupDetail.heading'/>"/>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap-multiselect.css'/>" type="text/css"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userGroupList.userGroup"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="userGroupDetail.heading"/></h2>
    <fmt:message key="userGroupDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="userGroup" method="post" action="userGroupform" cssClass="well"
           id="userGroupForm" onsubmit="return validateUserGroup(this)">
<form:hidden path="id"/>
    <spring:bind path="userGroup.name">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="userGroup.name" styleClass="control-label"/>
    <form:input cssClass="form-control" path="name" id="name"  maxlength="255"/>
    <form:errors path="name" cssClass="help-block"/>
    </div>
    <spring:bind path="userGroup.comment">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userGroup.comment" styleClass="control-label"/>
        <form:input cssClass="form-control" path="comment" id="comment"  maxlength="255"/>
        <form:errors path="comment" cssClass="help-block"/>
    </div>
    <div class="form-group">
        <appfuse:label key="userGroup.members" styleClass="control-label"/>:<br>
        <form:select cssClass="form-control" id="members" multiple="true" path="members" items="${usersList}" itemLabel="fullName" itemValue="id"/>
    </div>


    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty userGroup.id}">
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

<v:javascript formName="userGroup" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/multieselect/bootstrap-multiselect.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['userGroupForm']).focus();
        $("#members").multiselect({numberDisplayed:20});
    });
</script>
