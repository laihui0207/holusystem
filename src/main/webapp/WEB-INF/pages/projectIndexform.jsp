<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="projectIndexDetail.title"/></title>
    <meta name="menu" content="ProjectIndexMenu"/>
    <meta name="heading" content="<fmt:message key='projectIndexDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="projectIndexList.projectIndex"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="projectIndexDetail.heading"/></h2>
    <fmt:message key="projectIndexDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="projectIndex" method="post" action="projectIndexform" cssClass="well"
           id="projectIndexForm" onsubmit="return validateProjectIndex(this)">
<form:hidden path="id"/>
    <spring:bind path="projectIndex.midTableName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="projectIndex.midTableName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="midTableName" id="midTableName"  maxlength="255"/>
        <form:errors path="midTableName" cssClass="help-block"/>
    </div>
    <spring:bind path="projectIndex.partListTableName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="projectIndex.partListTableName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="partListTableName" id="partListTableName"  maxlength="255"/>
        <form:errors path="partListTableName" cssClass="help-block"/>
    </div>
    <spring:bind path="projectIndex.itemID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="projectIndex.itemID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="itemID" id="itemID"  maxlength="255"/>
        <form:errors path="itemID" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty projectIndex.id}">
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

<v:javascript formName="projectIndex" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['projectIndexForm']).focus();
    });
</script>
