<template>
  <div>
    <router-link to="/create">Create New User</router-link>
    <h2>Users</h2>
    <table border="1" cellpadding="8">
      <tr><th>ID</th><th>Name</th><th>Email</th><th>Actions</th></tr>
      <tr v-for="u in users" :key="u.id">
        <td>{{ u.id }}</td>
        <td>{{ u.name }}</td>
        <td>{{ u.email }}</td>
        <td>
          <router-link :to="`/edit/${u.id}`">Edit</router-link>
          <button @click="remove(u.id)">Delete</button>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
import { fetchUsers, deleteUser } from '../api/user'

export default {
  name: 'UserList',
  data() {
    return { users: [] }
  },
  methods: {
    load() {
      fetchUsers().then(r => { this.users = r.data }).catch(e => alert('Load failed'))
    },
    remove(id) {
      if (!confirm('Delete?')) return
      deleteUser(id).then(() => this.load()).catch(() => alert('Delete failed'))
    }
  },
  mounted() {
    this.load()
  }
}
</script>
