<!doctype html>

<HTML>
<head>
    <script language='javascript'>
        localStorage.setItem('oauthstate', '${oauthstate}')
        <#if name??>localStorage.setItem('name','${name}');
        <#else>localStorage.removeItem('name');
        </#if>
        window.close();
    </script>
    <link rel="stylesheet" href="css/demo.css"/>
</head>
<body>
</body>
</html>
