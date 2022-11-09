const deleteModal = document.getElementById('ModalDelete')
deleteModal.addEventListener('show.bs.modal', event => {
    // Button that triggered the modal
    const button = event.relatedTarget
    // Extract info from data-bs-* attributes
    const userId = button.getAttribute('data-bs-userId')
    const userFirstName = button.getAttribute('data-bs-userFirstName')
    const userLastName = button.getAttribute('data-bs-userLastName')
    const userPassword = button.getAttribute('data-bs-userPassword')
    const userEmail = button.getAttribute('data-bs-userEmail')
    const userAge = button.getAttribute('data-bs-userAge')

    // Update the modal's content.
    const modalUserId = deleteModal.querySelector('#userIdD')
    const modalUserFirstName = deleteModal.querySelector('#userFirstNameD')
    const modalUserLastName = deleteModal.querySelector('#userLastNameD')
    const modalUserPassword = deleteModal.querySelector('#userPasswordD')
    const modalUserEmail = deleteModal.querySelector('#userEmailD')
    const modalUserAge = deleteModal.querySelector('#userAgeD')

    modalUserId.value = userId
    modalUserFirstName.value = userFirstName
    modalUserLastName.value = userLastName
    modalUserPassword.value = userPassword
    modalUserEmail.value = userEmail
    modalUserAge.value = userAge
})