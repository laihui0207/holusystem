<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="subComponentListDetail.title"/></title>
    <meta name="menu" content="SubComponentListMenu"/>
    <meta name="heading" content="<fmt:message key='subComponentListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="subComponentListList.subComponentList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="subComponentListDetail.heading"/></h2>
    <fmt:message key="subComponentListDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="subComponentList" method="post" action="subComponentListform" cssClass="well"
           id="subComponentListForm" onsubmit="return validateSubComponentList(this)">
<form:hidden path="id"/>
    <spring:bind path="subComponentList.componentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="subComponentList.componentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="componentID" id="componentID"  maxlength="255"/>
        <form:errors path="componentID" cssClass="help-block"/>
    </div>
    <spring:bind path="subComponentList.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="subComponentList.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <spring:bind path="subComponentList.subComponentID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="subComponentList.subComponentID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="subComponentID" id="subComponentID"  maxlength="255"/>
        <form:errors path="subComponentID" cssClass="help-block"/>
    </div>
    <spring:bind path="subComponentList.subComponentName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="subComponentList.subComponentName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="subComponentName" id="subComponentName"  maxlength="255"/>
        <form:errors path="subComponentName" cssClass="help-block"/>
    </div>
    <spring:bind path="subComponentList.subQuantity">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="subComponentList.subQuantity" styleClass="control-label"/>
        <form:input cssClass="form-control" path="subQuantity" id="subQuantity"  maxlength="255"/>
        <form:errors path="subQuantity" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="user" items="userList" itemLabel="label" itemValue="value"/>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty subComponentList.id}">
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

<v:javascript formName="subComponentList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['subComponentListForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
