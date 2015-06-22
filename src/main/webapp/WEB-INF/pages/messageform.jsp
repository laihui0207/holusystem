<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageDetail.title"/></title>
    <meta name="menu" content="MessageMenu"/>
    <meta name="heading" content="<fmt:message key='messageDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="messageList.message"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="messageDetail.heading"/></h2>
    <fmt:message key="messageDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="message" method="post" action="messageform" cssClass="well"
           id="messageForm" onsubmit="return validateMessage(this)">
<form:hidden path="id"/>
    <spring:bind path="message.title">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="message.title" styleClass="control-label"/>
    <form:input cssClass="form-control" path="title" id="title"  maxlength="255"/>
    <form:errors path="title" cssClass="help-block"/>
    </div>
    <spring:bind path="message.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="message.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty message.id}">
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

<v:javascript formName="message" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['messageForm']).focus();
    });
</script>
