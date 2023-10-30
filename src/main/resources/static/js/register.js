
var emailControl = document.getElementById("emailControl")
var usernameControl = document.getElementById("usernameControl")
var passwordControl = document.getElementById("passwordControl")
var nameControl = document.getElementById("nameControl")
var surnameControl = document.getElementById("surnameControl")
var resetButton = document.getElementById("resetButton")

var emailErrorContainer = document.getElementById("emailErrorContainer")
var usernameErrorContainer = document.getElementById("usernameErrorContainer")
var passwordErrorContainer = document.getElementById("passwordErrorContainer")
var nameErrorContainer = document.getElementById("nameErrorContainer")
var surnameErrorContainer = document.getElementById("surnameErrorContainer")

var emailErrors = []
var usernameErrors = []
var passwordErrors = []
var nameErrors = []
var surnameErrors = []

resetButton.addEventListener('click',(event) => {
    reset()
})
emailControl.addEventListener('input',(event) => {
    let email = event.target.value
    emailErrors = []
    emailErrorContainer.innerHTML = ""
})
usernameControl.addEventListener('input',(event) => {
    let username = event.target.value
    usernameErrors = []
    usernameErrorContainer.innerHTML = ""
    if(username.length < 3)
        usernameErrors.push("Username must be longer than 3 characters")
    if(username.length > 20)
        usernameErrors.push("Username must be shorter than 20 characters")
    for(let i = 0;i < usernameErrors.length;i++) {
        let error = usernameErrors[i]
        usernameErrorContainer.innerHTML += "<small class = d-block>" + error + "</small>"
    }
})
passwordControl.addEventListener('input',(event) => {
    let password = event.target.value
    passwordErrors = []
    passwordErrorContainer.innerHTML = ""
    if(password.length < 5)
        passwordErrors.push("Password must be longer than 5 characters");
    for(let i = 0;i < passwordErrors.length;i++) {
        let error = passwordErrors[i]
        passwordErrorContainer.innerHTML += "<small class = d-block>" + error + "</small>"
    }
});
nameControl.addEventListener('input',(event) => {
    let name = event.target.value
    nameErrors = []
    nameErrorContainer.innerHTML = ""
    if(name.length < 3)
        nameErrors.push("Name must be longer than 3 characters")
    if(name.length > 10)
        nameErrors.push("Name must be shorter than 20 characters")
    for(let i = 0;i < nameErrors.length;i++) {
        let error = nameErrors[i]
        nameErrorContainer.innerHTML += "<small class = d-block>" + error + "</small>"
    }
});
surnameControl.addEventListener('input',(event) => {
    let surname = event.target.value
    surnameErrors = []
    surnameErrorContainer.innerHTML = ""
    if(surname.length < 3)
        surnameErrors.push("Surname must be longer than 3 characters")
    if(surname.length > 10)
        surnameErrors.push("Surname must be shorter than 10 characters")
    for(let i = 0;i < surnameErrors.length;i++) {
        let error = surnameErrors[i]
        surnameErrorContainer.innerHTML += "<small class = d-block>" + error + "</small>"
    }
});
function reset() {
    emailControl.value = ""
    surnameControl.value = ""
    passwordControl.value = ""
    nameControl.value = ""
    surnameControl.value = ""
}
function valid() {
    return !(emailErrors.value.length != 0 && passwordErrors.value.length != 0 && usernameErrors.value.length != 0 && nameControl.value.length != 0
    && surnameControl.value.length != 0)
}