var is_logged_in = false;

function toggle_login() {
    is_logged_in = !is_logged_in;
    updateButton();
}

function updateButton() {
    if (is_logged_in === false) {
        document.getElementById("login_btn").innerHTML =
            '<button onclick="toggle_login()">Se connecter</button>';
        console.log("User is disconnected");
    } else {
        document.getElementById("login_btn").innerHTML =
            '<button onclick="toggle_login()">Se déconnecter</button>';
        console.log("User is connected");
    }
}

updateButton();