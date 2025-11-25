import axios from 'axios'

// Use relative API path; Vite dev server proxies /api to backend
const api = axios.create({ baseURL: '/api' })

// export function fetchUsers() {
//   return api.get('/users')
// }

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

// TODO：新增查询和更新
export function fetchUsers(params) {
  return api.get('/users', { params }) // params: { keyword, tag }
}

export function updateUserTags(id, tags) {
  return api.put(`/users/${id}/tags`, { tags }) // tags 为逗号分隔字符串
}
