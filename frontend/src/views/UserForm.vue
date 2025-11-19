<template>
  <div>
    <h2>{{ isEdit ? '编辑用户' : '创建用户' }}</h2>
    <el-form :model="user" label-width="80px" @submit.prevent="submit">
      <el-form-item label="姓名">
        <el-input v-model="user.name" required></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="user.email" required></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submit">保存</el-button>
        <router-link to="/">
          <el-button>取消</el-button>
        </router-link>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { fetchUser, createUser, updateUser } from '../api/user';

export default {
  name: 'UserForm',
  props: ['id'],
  data() {
    return { user: { name: '', email: '' } };
  },
  computed: {
    isEdit() {
      return !!this.$route.params.id;
    },
  },
  mounted() {
    const id = this.$route.params.id;
    if (id) {
      fetchUser(id)
        .then((r) => {
          this.user = r.data;
        })
        .catch(() => alert('加载用户失败'));
    }
  },
  methods: {
    submit() {
      const id = this.$route.params.id;
      if (id) {
        updateUser(id, this.user)
          .then(() => this.$router.push('/'))
          .catch(() => alert('更新失败'));
      } else {
        createUser(this.user)
          .then(() => this.$router.push('/'))
          .catch(() => alert('创建失败'));
      }
    },
  },
};
</script>

<style>
/* 添加一些样式以适配 Element Plus */
</style>
