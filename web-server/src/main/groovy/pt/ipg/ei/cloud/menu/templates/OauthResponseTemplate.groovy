package pt.ipg.ei.cloud.menu.templates

class OauthResponseTemplate {
    String oauthStateKey, oauthStateValue

    public GString render() {
        """\
<!DOCTYPE html>

<HTML>
<head>
<script language='javascript'>
        localStorage.setItem('${oauthStateKey}', '${oauthStateValue}')
        ${applyAction()}
        window.close();
</script>
    <link rel="stylesheet" href="css/demo.css"/>
</head>
<body>
</body>
</html>"""
    }


}
