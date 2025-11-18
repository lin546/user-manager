import { createRouter, createWebHistory } from 'vue-router'
import UserList from '../views/UserList.vue'
import UserForm from '../views/UserForm.vue'

const routes = [
  { path: '/', component: UserList },
  { path: '/create', component: UserForm },
  { path: '/edit/:id', component: UserForm, props: true }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
