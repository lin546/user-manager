import { createRouter, createWebHistory } from 'vue-router'
import UserList from '../views/UserList.vue'
import UserForm from '../views/UserForm.vue'

// TODO:新增“创建新用户页面”路由
const routes = [
  { path: '/', component: UserList },
  { path: '/edit/:id', component: UserForm, props: true }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
