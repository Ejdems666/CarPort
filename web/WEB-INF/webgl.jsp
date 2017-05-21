<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${assets}TemplateData/style.css">
    <script src="${assets}TemplateData/UnityProgress.js"></script>
    <script src="${assets}Build/UnityLoader.js"></script>
    <link rel="stylesheet" href="${assets}style.css">
    <title>${title}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${assets}bootstrap.min.css">
    <link rel="stylesheet" href="${assets}custom.css">
    <link rel="stylesheet" href="${assets}style.css">
    <link rel="stylesheet" href="${assets}font-awesome/css/font-awesome.min.css">
    <script src="${assets}jquery-3.1.1.min.js"></script>
    <script src="${assets}bootstrap.min.js"></script>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
    <link rel="shortcut icon" type="image/png" href="${assets}favicon.ico"/>
    <script src="${assets}jquery.slides.min.js"></script>
</head>
<body class="bg">
<jsp:include page="/WEB-INF/menu.jsp"/>

<jsp:include page="/WEB-INF/pages/${template}.jsp"/>

<footer class="navbar navbar-default container-fluid text-center footer">
    <p>Copyright <b>Hygge team</b></p>
</footer>
</body>
</html>
