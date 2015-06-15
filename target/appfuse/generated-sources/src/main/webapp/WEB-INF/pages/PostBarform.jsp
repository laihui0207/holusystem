<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postBarDetail.title"/></title>
    <meta name="menu" content="PostBarMenu"/>
    <meta name="heading" content="<fmt:message key='postBarDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postBarList.postBar"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="postBarDetail.heading"/></h2>
    <fmt:message key="postBarDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="postBar" method="post" action="postBarform" cssClass="well"
           id="postBarForm" onsubmit="return validatePostBar(this)">
<form:hidden path="id"/>
    <spring:bind path="postBar.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="content"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>
    <spring:bind path="postBar.createTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.createTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
        <form:errors path="createTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="creater" items="createrList" itemLabel="label" itemValue="value"/>
    <spring:bind path="postBar.ifAccessAllReply">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.ifAccessAllReply" styleClass="control-label"/>
        <form:checkbox path="ifAccessAllReply" id="ifAccessAllReply" cssClass="checkbox"/>
        <form:errors path="ifAccessAllReply" cssClass="help-block"/>
    </div>
    <spring:bind path="postBar.lastReplyTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.lastReplyTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="lastReplyTime" id="lastReplyTime"  maxlength="255"/>
        <form:errors path="lastReplyTime" cssClass="help-block"/>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="lastReplyUser" items="lastReplyUserList" itemLabel="label" itemValue="value"/>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select cssClass="form-control" path="postSubject" items="postSubjectList" itemLabel="label" itemValue="value"/>
    <spring:bind path="postBar.thumbnailUrl">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.thumbnailUrl" styleClass="control-label"/>
        <form:input cssClass="form-control" path="thumbnailUrl" id="thumbnailUrl"  maxlength="255"/>
        <form:errors path="thumbnailUrl" cssClass="help-block"/>
    </div>
    <spring:bind path="postBar.title">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.title" styleClass="control-label"/>
        <form:input cssClass="form-control" path="title" id="title"  maxlength="255"/>
        <form:errors path="title" cssClass="help-block"/>
    </div>
    <spring:bind path="postBar.updateTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="postBar.updateTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="updateTime" id="updateTime" size="11" title="date" datepicker="true"/>
        <form:errors path="updateTime" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty postBar.id}">
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

<v:javascript formName="postBar" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['postBarForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
