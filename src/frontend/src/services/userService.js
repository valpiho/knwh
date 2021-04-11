// Fetch users from server
export const fetchUsers = async () => {
    const response = await fetch("http://localhost:8080/api/users");
    return await response.json();
}

// Add user to server
export const addUser = async (user) => {
    const response = await fetch("http://localhost:8080/api/users",
        {
            method: 'POST',
            headers: {'Content-type': 'application/json'},
            body: JSON.stringify(user)
        });

}

// Delete user from server
export const deleteUser = async (id) => {
    await fetch(`http://localhost:8080/api/users/${id}`, {method: 'DELETE'})
}
