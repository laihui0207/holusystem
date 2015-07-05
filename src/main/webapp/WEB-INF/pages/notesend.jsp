<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="noteDetail.title"/></title>
    <meta name="menu" content="NoteMenu"/>
    <meta name="heading" content="<fmt:message key='noteDetail.heading'/>"/>
    <link rel="stylesheet" href="<c:url value='/styles/bootstrap-multiselect.css'/>" type="text/css"/>
</head>

<spring:htmlEscape defaultHtmlEscape="true"/>
<c:set var="delObject" scope="request"><fmt:message key="noteList.note"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="col-sm-3">
    <h2><fmt:message key="noteDetail.heading"/></h2>
    <fmt:message key="noteDetail.message"/>
</div>

<div class="col-sm-8">
<form:errors path="*" cssClass="alert alert-danger alert-dismissable" element="div"/>
<form:form commandName="note" method="post" action="${pageContext.request.contextPath}/noteform/${note.id}/Send" cssClass="well"
           id="noteForm" onsubmit="return validateNote(this)">
<form:hidden path="id"/>
    <spring:bind path="note.title">
        <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
    <appfuse:label key="note.title" styleClass="control-label"/>
    <form:input cssClass="form-control" path="title" id="title"  maxlength="255" readonly="true"/>
    <form:errors path="title" cssClass="help-block"/>
    </div>
    <spring:bind path="note.content">
    <div class="form-group${(not empty status.errorMessage) ? ' has-error' : ''}">
    </spring:bind>
        <appfuse:label key="note.content" styleClass="control-label"/>
        <form:input cssClass="form-control" path="content" id="contentinput"  maxlength="255" readonly="true"/>
        <form:errors path="content" cssClass="help-block"/>
    </div>

    <div class="form-group"  id="viewUserdiv">
        <appfuse:label key="messageSend.toUser" styleClass="control-label"/>
        <form:select cssClass="form-control" path="sendToUserList" items="${userList}" itemLabel="fullName"
                     itemValue="id"/>
    </div>
    <div class="form-group"  id="viewGroupdiv">
        <appfuse:label key="messageSend.toGrout" styleClass="control-label"/>
        <form:select cssClass="form-control" path="sendToUserGroupList" items="${userGroupList}" itemLabel="name"
                     itemValue="id"/>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary" id="save" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>

        <button type="submit" class="btn btn-default" id="cancel" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>

<v:javascript formName="note" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value='/scripts/editor/kindeditor.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/multieselect/bootstrap-multiselect.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['noteForm']).focus();
        $("#sendToUserList").multiselect();
        $("#sendToUserGroupList").multiselect();
        KindEditor.ready(function (K) {
            window.editor = K.create('#contentinput', {
                uploadJson: "<c:url value='/editeruploadattachement'/>",
                fileManagerJson: "<c:url value='/editeruploadattachement'/>",
                allowFileManager: true, filterMode: false/*,readonlyMode:true*/
            });
        });
    });
</script>
