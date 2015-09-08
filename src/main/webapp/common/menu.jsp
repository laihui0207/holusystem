<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="collapse navbar-collapse" id="navbar">
<ul class="nav navbar-nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>
    </c:if>
    <menu:displayMenu name="Home"/>
    <menu:displayMenu name="NewsMenu"/>
    <menu:displayMenu name="NoteMenu"/>
    <menu:displayMenu name="PostBarMenu"/>
    <menu:displayMenu name="MessageMenu"/>
    <menu:displayMenu name="ProjectMenu"/>
    <menu:displayMenu name="DocumentationMenu"/>
    <menu:displayMenu name="CustomGroupMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    <!--MessageReceiver-START-->
<%--    <menu:displayMenu name="MessageReceiverMenu"/>--%>
    <!--MessageReceiver-END-->
    <!--MessageReply-START-->
<%--    <menu:displayMenu name="MessageReplyMenu"/>--%>
    <!--MessageReply-END-->
    <!--Summary-START-->
<%--    <menu:displayMenu name="SummaryMenu"/>--%>
    <!--Summary-END-->
    <!--UserTrack-START-->
<%--    <menu:displayMenu name="UserTrackMenu"/>--%>
    <!--UserTrack-END-->
    <!--ClientVersion-START-->
    <menu:displayMenu name="ClientVersionMenu"/>
    <!--ClientVersion-END-->
</ul>
</div>
</menu:useMenuDisplayer>
