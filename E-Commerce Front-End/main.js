let divReg = document.querySelector(".register");
let userName = document.querySelector(".userName");
let password = document.querySelector(".password");
let email = document.querySelector(".email");
let firstName = document.querySelector(".firstName");
let lastName = document.querySelector(".lastName");

let divUsers = document.querySelector(".users");
let submit = document.querySelector(".submit");

//let table = document.querySelector(".table");

submit.onclick = function () {
  if (
    userName.value != "" &&
    password.value != "" &&
    email.value != "" &&
    firstName.value != "" &&
    lastName.value != ""
  ) {
    addUserToDataBase();
  } else {
    console.log(`faild`);
  }
};

async function addUserToDataBase() {
  const info = {
    userName: userName.value,
    password: password.value,
    email: email.value,
    firstName: firstName.value,
    lastName: lastName.value,
  };
  console.log(JSON.stringify(info));

  let data = await fetch("http://localhost:8080/auth/register", {
    method: "POST",
    body: JSON.stringify(info),
    headers: { "content-type": "application/json" },
  });
  await data.json();
}

async function getUsers() {
  let data = (await fetch("http://localhost:8080/getUsers/getU")).json();
  console.log(await data);
  addUsersToPage(await data);
}
getUsers();

function addUsersToPage(users) {
  let table = document.createElement("table");
  table.classList = "users";
  let tr = document.createElement("tr");
  tr.innerHTML = `
  <th>User Name</th>
  <th>Password</th>
  <th>Email</th>
  <th>First Name</th>
  <th>Last Name</th>
  `;
  table.appendChild(tr);

  users.forEach((e) => {
    let tr = document.createElement("tr");

    tr.innerHTML = `
    <tr>
        <td>${e.userName}</td>
        <td>${e.password}</td>
        <td>${e.email}</td>
        <td>${e.firstName}</td>
        <td>${e.lastName}</td>
    </tr>
    `;
    table.appendChild(tr);
  });
  divUsers.appendChild(table);
}
/* document.createTextNode(`
    User Name : ${e.userName} 
    PassWord : ${e.password}
    Email : ${e.email}
    First Name : ${e.firstName}
    Last Name : ${e.lastName}
    `)*/
