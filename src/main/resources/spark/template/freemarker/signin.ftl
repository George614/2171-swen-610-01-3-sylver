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
      <a href="/">my home</a>
    </div>
    
    <div class="body">
         <h4>Sign in to play a game:</h4>

        <form action="./validate" method="POST">
            Enter your name (names available on first-come-first-served basis)
            <br/>
            <input name="myName" />
            <br/><br/>
            <button type="submit">Submit</button>
        </form>
    </div>
    
  </div>
</body>
</html>
