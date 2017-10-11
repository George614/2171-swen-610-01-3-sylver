<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
<div class="page">

    <h1>Web Checkers</h1>

    <div class="navigation">
        <a href="/game">my home</a>
        <a href="/signin">sign in</a>
    </div>

    <div class="body">
        <p>Welcome to the world of online Checkers.</p>
    </div>

    <#--#if removeform-->
        <form action="/signin" method="GET">
            Name: <input type="text" name="playername" required />	<br/><br/>
            <input type="submit" value="Enter" />
        </form>
    <#--/#if-->
    <#if userName??>
        <#if correctName>
            <div name="correctUsername"> Hello,${userName}!</div>
        <#else>
            <p> ENTER VALID USERNAME </p>
        </#if>
    </#if>


    <table class="datatable">
        <br>
        <p><b>Players online</b></p>

        <#if opponentList??>
            <form action="/game" method="GET">
                <#list opponentList as opps>

                    <#--tr>
                        <td>${opps}</td>
                    </tr-->

                        <input type="radio" name="opponentRadio" value="${opps}" required/> ${opps}
                    <br>

                </#list>
                <br>
                <input type="submit" value="Let's play!" />
            </form>
            <#--tr>
                <td>1</td>

            </tr>

            <tr>

                <td>2</td>

            </tr>

            <tr>

                <td>3</td>
            </tr-->

        </#if>

    </table>









</body>
</html>