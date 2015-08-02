<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="processMidDetail.title"/></title>
    <meta name="menu" content="ProcessMidMenu"/>
    <meta name="heading" content="<fmt:message key='processMidDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="processMidList.processMid"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="processMidDetail.heading"/></h2>
    <fmt:message key="processMidDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="processMid" method="post" action="processMidform" cssClass="well"
           id="processMidForm" onsubmit="return validateProcessMid(this)">
<form:hidden path="id"/>
    <spring:bind path="processMid.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.endDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.endDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="endDate" id="endDate" size="11" title="date" datepicker="true"/>
        <form:errors path="endDate" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.positionGPS">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.positionGPS" styleClass="control-label"/>
        <form:input cssClass="form-control" path="positionGPS" id="positionGPS"  maxlength="255"/>
        <form:errors path="positionGPS" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.processNote">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.processNote" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processNote" id="processNote"  maxlength="255"/>
        <form:errors path="processNote" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.startDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.startDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="startDate" id="startDate" size="11" title="date" datepicker="true"/>
        <form:errors path="startDate" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.styleProcessID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.styleProcessID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="styleProcessID" id="styleProcessID"  maxlength="255"/>
        <form:errors path="styleProcessID" cssClass="help-block"/>
    </div>
    <spring:bind path="processMid.subComponentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="processMid.subComponentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="subComponentID" id="subComponentID"  maxlength="255"/>
        <form:errors path="subComponentID" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty processMid.id}">
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

<v:javascript formName="processMid" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['processMidForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
