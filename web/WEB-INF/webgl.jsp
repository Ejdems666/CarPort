<%@ page import="static org.cba.Path.ASSETS" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="<%=ASSETS%>TemplateData/style.css">
    <script src="<%=ASSETS%>TemplateData/UnityProgress.js"></script>
    <script src="<%=ASSETS%>Build/UnityLoader.js"></script>
    <link rel="stylesheet" href="<%=ASSETS%>style.css">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%=ASSETS%>bootstrap.min.css">
    <link rel="stylesheet" href="<%=ASSETS%>custom.css">
    <link rel="stylesheet" href="<%=ASSETS%>style.css">
    <link rel="stylesheet" href="<%=ASSETS%>font-awesome/css/font-awesome.min.css">
    <script src="<%=ASSETS%>jquery-3.1.1.min.js"></script>
    <script src="<%=ASSETS%>bootstrap.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="<%=ASSETS%>favicon.ico"/>
    <link rel="shortcut icon" type="image/png" href="<%=ASSETS%>favicon.ico"/>
    <script src="<%=ASSETS%>jquery.slides.min.js"></script>
</head>
<body class="bg">
<jsp:include page="/WEB-INF/menu.jsp"/>

<jsp:include page="/WEB-INF/pages/${template}.jsp"/>

<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>
