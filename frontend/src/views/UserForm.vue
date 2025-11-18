<template>
  <div>
    <h2>{{ isEdit ? 'Edit' : 'Create' }} User</h2>
    <form @submit.prevent="submit">
      <div>
        <label>Name</label>
        <input v-model="user.name" required />
      </div>
      <div>
        <label>Email</label>
        <input v-model="user.email" required />
      </div>
      <div>
        <button type="submit">Save</button>
        <router-link to="/">Cancel</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import { fetchUser, createUser, updateUser } from '../api/user'

export default {
  name: 'UserForm',
  props: ['id'],
  data() {
    return { user: { name: '', email: '' } }
  },
  computed: {
    isEdit() { return !!this.$route.params.id }
  },
  mounted() {
    const id = this.$route.params.id
    if (id) {
      fetchUser(id).then(r => { this.user = r.data }).catch(() => alert('Load user failed'))
    }
  },
  methods: {
    submit() {
      const id = this.$route.params.id
      if (id) {
        updateUser(id, this.user).then(() => this.$router.push('/')).catch(() => alert('Update failed'))
      } else {
        createUser(this.user).then(() => this.$router.push('/')).catch(() => alert('Create failed'))
      }
    }
  }
}
</script>
