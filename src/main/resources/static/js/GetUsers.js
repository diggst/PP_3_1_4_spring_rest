async function getUsers() {


    const response = await fetch("api");

    // if (response.ok) {
        let json = await response.json()
            .then(data => replaceTable(data));
    // } else {
    //     alert("Îøèáêà HTTP: " + response.status);
    // }

    function listRoles(roles) {
        let rolesString = [];
        roles.forEach((role) => rolesString.push(role["roleName"].substring(role["roleName"].indexOf('_') + 1)))
        return rolesString.join(", ");
    }

    function replaceTable(data) {
        const placement = document.getElementById("usersTablePlacement")
        placement.innerHTML = "";
        data.forEach(({id, name, lastName, password, age, email, roles}) => {
            const element = document.createElement("tr");
            element.innerHTML = `
            <th scope="row">${id}</th>
            <td>${name}</td>
            <td>${lastName}</td>
            <td>${age}</td>
            <td>${email}</td>
            <td>${listRoles(roles)}</td>
            <td>
                <button type="button" class="btn btn-info text-white" data-bs-userId=${id}
                    data-bs-userFirstName=${name} data-bs-userLastName=${lastName} data-bs-userPassword=${password} 
                    data-bs-userEmail=${email} data-bs-userAge=${age} data-bs-toggle="modal"
                    data-bs-target="#ModalEdit">Edit</button>
            </td>
            <td>
                <button type="button" class="btn btn-danger" data-bs-userId=${id}
                    data-bs-userFirstName=${name} data-bs-userLastName=${lastName} data-bs-userPassword=${password} 
                    data-bs-userEmail=${email} data-bs-userAge=${age} data-bs-toggle="modal"
                    data-bs-target="#ModalDelete">Delete</button>
            </td>`

            placement.append(element);
        })
    }
}