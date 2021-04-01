import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}
export const getAllPeople = () =>
    fetch("api/v1/people")
        .then(checkStatus);

export const addNewPerson = person =>
    fetch("api/v1/people", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(person)
    });

export const deletePerson = personId =>
    fetch(`api/v1/people/${personId}`, {
        method: 'DELETE'
    })
        .then(checkStatus)
