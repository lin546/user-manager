import axios from 'axios'

// Use relative API path; Vite dev server proxies /api to backend
const api = axios.create({ baseURL: '/api' })

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
