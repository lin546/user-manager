import axios from 'axios'

const api = axios.create({ baseURL: 'http://localhost:8080/api' })

export function fetchUsers() {
  return api.get('/users')
}

export function fetchUser(id) {
  return api.get(`/users/${id}`)
}

export function createUser(payload) {
  return api.post('/users', payload)
}

export function updateUser(id, payload) {
  return api.put(`/users/${id}`, payload)
}

export function deleteUser(id) {
  return api.delete(`/users/${id}`)
}
