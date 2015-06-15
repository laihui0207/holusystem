<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="postDetail.title"/></title>
    <meta name="menu" content="PostMenu"/>
    <meta name="heading" content="<fmt:message key='postDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="postList.post"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="postDetail.heading"/></h2>
    <fmt:message key="postDetail.message"/>
</div>

<div class="col-sm-6">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="post" method="post" action="postform" cssClass="well"
           id="postForm" onsubmit="return validatePost(this)">
<form:hidden path="id"/>
    <spring:bind path="post.company">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.company" styleClass="control-label"/>
        <form:input cssClass="form-control" path="company" id="company"  maxlength="255"/>
        <form:errors path="company" cssClass="help-block"/>
    </div>
    <spring:bind path="post.createDate">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.createDate" styleClass="control-label"/>
        <form:input cssClass="form-control" path="createDate" id="createDate" size="11" title="date" datepicker="true"/>
        <form:errors path="createDate" cssClass="help-block"/>
    </div>
    <spring:bind path="post.postID">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.postID" styleClass="control-label"/>
        <form:input cssClass="form-control" path="postID" id="postID"  maxlength="255"/>
        <form:errors path="postID" cssClass="help-block"/>
    </div>
    <spring:bind path="post.postName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.postName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="postName" id="postName"  maxlength="255"/>
        <form:errors path="postName" cssClass="help-block"/>
    </div>
    <spring:bind path="post.postNote">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.postNote" styleClass="control-label"/>
        <form:input cssClass="form-control" path="postNote" id="postNote"  maxlength="255"/>
        <form:errors path="postNote" cssClass="help-block"/>
    </div>
    <spring:bind path="post.processName">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="post.processName" styleClass="control-label"/>
        <form:input cssClass="form-control" path="processName" id="processName"  maxlength="255"/>
        <form:errors path="processName" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty post.id}">
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

<v:javascript formName="post" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/webjars/bootstrap-datepicker/1.3.1/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['postForm']).focus();
        $('.text-right.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
