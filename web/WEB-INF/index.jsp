<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="../assets/style.css">
    <title>${title}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${assets}bootstrap.min.css">
    <link rel="stylesheet" href="${assets}style.css">
    <script src="${assets}jquery-3.1.1.min.js"></script>
    <script src="${assets}bootstrap.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
</head>
<body>
<% if (request.getAttribute("alerts") != null) {%>
${alerts}
<% } %>
<div class="container bgContainer">
    <jsp:include page="/WEB-INF/pages/${template}.jsp"/>
</div>
<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>
