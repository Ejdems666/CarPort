<%@ page import="static org.cba.Path.ASSETS" %>
<%@ page import="static org.cba.Path.ROOT" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<%=ASSETS%>style.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="<%=ASSETS%>favicon.ico"/>
    <link rel="shortcut icon" type="image/png" href="<%=ASSETS%>favicon.ico"/>

    <link rel="stylesheet" href="<%=ASSETS%>bootstrap.min.css">
    <link rel="stylesheet" href="<%=ASSETS%>style.css">
    <link rel="stylesheet" href="<%=ASSETS%>font-awesome/css/font-awesome.min.css">
    <script src="<%=ASSETS%>jquery-3.1.1.min.js"></script>
    <script src="<%=ASSETS%>bootstrap.min.js"></script>

    <script src="<%=ASSETS%>jquery.slides.min.js"></script>

    <link rel="stylesheet" href="<%=ASSETS%>bootstrap-select/bootstrap-select.min.css">
    <script src="<%=ASSETS%>bootstrap-select/bootstrap-select.min.js"></script>

    <link rel="stylesheet" href="<%=ASSETS%>bower_components/seiyria-bootstrap-slider/dist/css/bootstrap-slider.min.css">
    <script src="<%=ASSETS%>bower_components/seiyria-bootstrap-slider/dist/bootstrap-slider.min.js"></script>

    <script>
        var root = '<%=ROOT%>';
    </script>
    <script src="<%=ASSETS%>js/functions.js"></script>

    <title>${title}</title>
</head>
<body class="bg">
<jsp:include page="/WEB-INF/menu.jsp"/>
<div class="container bgContainer">

    <jsp:include page="/WEB-INF/pages/${template}.jsp"/>
</div>
<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>
