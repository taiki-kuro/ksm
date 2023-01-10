function signUp(){
​
  var username = document.getElementById("username-field").value;//ユーザー名の取得
  var user = KiiUser.userWithUsername(username);//ユーザーを作成
  user.register({
    success:function(theUser){
      console.log("User registerd:" + JSON.stringify(theUser));
​
      openListPage();
    },
    failure: function(theUser, errorString){
      alert("Unable to register user:" + errorString);
      console.log("Unable to register user: " + errorString);
    }
  });　
}
​
function logIn() {
  // Get the username and password from the UI.
  var username = document.getElementById("username-field").value;
​
  // Authenticate the user asynchronously.
  KiiUser.authenticate(username,  {
    // If the user was authenticated
    success: function(theUser) {
      console.log("User authenticated: " + JSON.stringify(theUser));
​
      // Go to the main screen.
      openListPage();
    },
    // If the user was not authenticated
    failure: function(theUser, errorString) {
      console.log("Unable to authenticate user: " + errorString);
      alert("Unable to authenticate user: " + errorString);
    }
  });
}
