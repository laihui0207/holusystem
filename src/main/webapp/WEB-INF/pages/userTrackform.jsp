<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="userTrackDetail.title"/></title>
    <meta name="menu" content="UserTrackMenu"/>
    <meta name="heading" content="<fmt:message key='userTrackDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="userTrackList.userTrack"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="userTrackDetail.heading"/></h2>
    <fmt:message key="userTrackDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="userTrack" method="post" action="userTrackform" cssClass="well"
           id="userTrackForm" onsubmit="return validateUserTrack(this)">
<form:hidden path="id"/>
    <spring:bind path="userTrack.inOrOut">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userTrack.inOrOut" styleClass="control-label"/>
        <form:input cssClass="form-control" path="inOrOut" id="inOrOut"  maxlength="255"/>
        <form:errors path="inOrOut" cssClass="help-block"/>
    </div>
    <spring:bind path="userTrack.ip">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userTrack.ip" styleClass="control-label"/>
        <form:input cssClass="form-control" path="ip" id="ip"  maxlength="255"/>
        <form:errors path="ip" cssClass="help-block"/>
    </div>
    <spring:bind path="userTrack.loginDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userTrack.loginDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="loginDate" id="loginDate" size="11" title="date" datepicker="true"/>
        <form:errors path="loginDate" cssClass="help-block"/>
    </div>
    <spring:bind path="userTrack.optionTable">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userTrack.optionTable" styleClass="control-label"/>
        <form:input cssClass="form-control" path="optionTable" id="optionTable"  maxlength="255"/>
        <form:errors path="optionTable" cssClass="help-block"/>
    </div>
    <spring:bind path="userTrack.userId">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="userTrack.userId" styleClass="control-label"/>
        <form:input cssClass="form-control" path="userId" id="userId"  maxlength="255"/>
        <form:errors path="userId" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty userTrack.id}">
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

<v:javascript formName="userTrack" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['userTrackForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
