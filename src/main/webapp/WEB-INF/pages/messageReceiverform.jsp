<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="messageReceiverDetail.title"/></title>
    <meta name="menu" content="MessageReceiverMenu"/>
    <meta name="heading" content="<fmt:message key='messageReceiverDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="messageReceiverList.messageReceiver"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="messageReceiverDetail.heading"/></h2>
    <fmt:message key="messageReceiverDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="messageReceiver" method="post" action="messageReceiverform" cssClass="well"
           id="messageReceiverForm" onsubmit="return validateMessageReceiver(this)">
<form:hidden path="id"/>
    <spring:bind path="messageReceiver.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="messageReceiver.createTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createTime" id="createTime"  maxlength="255"/>
        <form:errors path="createTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="message" items="messageList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="receiver" items="receiverList" itemLabel="label" itemValue="value"/>
    <spring:bind path="messageReceiver.status">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="messageReceiver.status" styleClass="control-label"/>
        <form:input cssClass="form-control" path="status" id="status"  maxlength="255"/>
        <form:errors path="status" cssClass="help-block"/>
    </div>
    <spring:bind path="messageReceiver.updateTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="messageReceiver.updateTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updateTime" id="updateTime"  maxlength="255"/>
        <form:errors path="updateTime" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty messageReceiver.id}">
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

<v:javascript formName="messageReceiver" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['messageReceiverForm']).focus();
    });
</script>
