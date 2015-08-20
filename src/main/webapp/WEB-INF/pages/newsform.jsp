<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="newsDetail.title"/></title>
    <meta name="menu" content="NewsMenu"/>
    <meta name="heading" content="<fmt:message key='newsDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="newsList.news"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<%--<div class="col-sm-3">
    <h2><fmt:message key="newsDetail.heading"/></h2>
    <fmt:message key="newsDetail.message"/>
</div>--%>

<div class="col-sm-12">
    <h2><fmt:message key="newsDetail.heading"/></h2>
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="news" method="post" action="newsform" cssClass="well"
           id="newsForm" onsubmit="return validateNews(this)">
<form:hidden path="id"/>
    <spring:bind path="news.title">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="news.title" styleClass="control-label"/>
    <form:input cssClass="form-control" path="title" id="title"  maxlength="255"/>
    <form:errors path="title" cssClass="help-block"/>
    </div>
    <div class="form-group">
        <appfuse:label key="news.level"  styleClass="control-label"/>:
        <label class="checkbox-inline">
            <form:checkbox path="level" id="level"/>
            <fmt:message key="news.levelName"/>
        </label>
    </div>
    <div class="form-group">
        <appfuse:label key="news.newsType"  styleClass="control-label"/>
        <form:select cssClass="form-control" path="newsType.id" items="${newsTypeList}" itemLabel="name" itemValue="id"/>
    </div>
    <spring:bind path="news.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="news.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="contentinput"  maxlength="255"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>
    <spring:bind path="news.expiredTime">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="news.expiredTime" styleClass="control-label"/>
        <form:input cssClass="form-control" path="expiredTime" id="expiredTime"  maxlength="255"/>
        <form:errors path="expiredTime" cssClass="help-block"/>
    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty news.id}">
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

<v:javascript formName="news" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/editor/kindeditor.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
    <script type="text/javascript"
            src="<c:url value='/webjars/bootstrap-datepicker/1.3.1/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['newsForm']).focus();
        $("#expiredTime").datepicker({autoclose: true});
        KindEditor.ready(function (K) {
            window.editor = K.create('#contentinput', {
                uploadJson: "<c:url value='/editeruploadattachement'/>",
                fileManagerJson: "<c:url value='/editeruploadattachement'/>",
                allowFileManager: true, filterMode: false
            });
        });
    });
</script>
