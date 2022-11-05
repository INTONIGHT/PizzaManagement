function login(){
    //follow this structure for the urls.
    let url = "http://localhost:3306/PizzaManagementBackend/PizzaServlet/login";
    let xhttp = new XMLHttpRequest();
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    let login = {username:username,password:password};
    var jsonLogin = JSON.stringify(login);
    xhttp.open('POST',url);
    xhttp.send(jsonLogin);
    let output = document.getElementById('data');
    output.innerHTML = '';
    xhttp.onreadystatechange = () => {
        if(xhttp.status == 200 && xhttp.readyState == 4){
            let response = JSON.parse(xhttp.responseText);
            setCookie("Login",response);
            let role = response.role;
            if(role == "owner"){
                window.location = "PizzaManager.html";
            } if(role == "chef"){
                window.location = "PizzaToppingManager.html";
            }
        }
    };
}
function setCookie(name,value){
    document.cookie = name + "=" + value + ";" + ";path=/";
}