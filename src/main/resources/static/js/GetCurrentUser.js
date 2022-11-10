async function getCurrentUser() {

    const response = await fetch("getUser");

    if (response.ok) {
    let json = await response.json()
        .then(data => {fillHeader(data); fillUserInfo(data);});
    } else {
        alert("Îøèáêà HTTP: " + response.status);
    }

    function listRoles(roles) {
        let rolesString = [];
        roles.forEach((role) =>
            rolesString.push(role["roleName"].substring(role["roleName"].indexOf('_') + 1)))
        return rolesString.join(", ");
    }

    function fillHeader(data) {
        const placement = document.getElementById("headerText");
        placement.innerHTML = `<b>${data.email}</b> with roles: ${listRoles(data.roles)}`
    }


    function fillUserInfo(data) {

        const placement = document.getElementById("userInfoPlacement")
        placement.innerHTML = "";
        const element = document.createElement("tr");

        element.innerHTML = `
        <th scope="row">${data.id}</th>
        <td>${data.name}</td>
        <td>${data.lastName}</td>
        <td>${data.age}</td>
        <td>${data.email}</td>
        <td>${listRoles(data.roles)}</td>`

        placement.append(element);

    }
}