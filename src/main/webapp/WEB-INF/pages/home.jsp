<%@ include file="/common/taglibs.jsp" %>

<head>
    <title><fmt:message key="home.title"/></title>
    <meta name="menu" content="Home"/>
</head>
<body class="home">

<h2><fmt:message key="home.heading"/></h2>

<p><fmt:message key="home.message"/></p>

<ul class="glassList">
    <li>
        <a href="<c:url value='/userform'/>"><fmt:message key="menu.user"/></a>
    </li>

    <li id="downloadClient" style="display: none">
        <fmt:message key="home.downloadAndroidClient"/>:
        <p>
            <img src="<c:url value='attached/client/lasted.png'/>">
        </p>
    </li>
    <%-- <li>
         <a href="<c:url value='/fileupload'/>"><fmt:message key="menu.selectFile"/></a>
     </li>--%>
</ul>
<script type='text/javascript' src="<c:url value='/dwr/interface/clientVersionManager.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/engine.js'/>"></script>
<script type='text/javascript' src="<c:url value='/dwr/util.js'/>"></script>
<script type="text/javascript">
    $(document).ready(function () {
       clientVersionManager.getLastQRFile(function(data){
           if(data){
               console.log("data:"+data)
               $("#downloadClient").show();
           }
       })
    })
</script>
</body>
