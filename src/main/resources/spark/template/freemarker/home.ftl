<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="7">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="page">

    <h1>Web Checkers</h1>

    <div class="navigation">
        <a href="/home">my home</a>

    </div>

    <div class="body">
        <p>Welcome to the world of online Checkers.</p>
    </div>

<#--#if removeform-->
    <form action="/" method="POST">
        Name: <input type="text" name="playername" required />	<br/><br/>
        <input type="submit" value="Enter" />
    </form>
    <#--/#if-->
    <#if message??>

        <div>${message}!</div>

    </#if>


    <table class="datatable">
        <br>
        <p><b>Players online</b></p>

    <#if names??>
        <form action="/game" method="GET">
            <#list names as oN>

                <#if oN != playerName>
                    <input type="radio" name="opponentRadio" value="${oN}" required/> ${oN}
                    <br>
                </#if>

            </#list>
            <br>
            <input type="submit" value="Let's play!" />
        </form>

    </#if>

    </table>









</body>
</html>