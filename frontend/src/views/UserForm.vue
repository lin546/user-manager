<template>
  
  <h2 class="form-title">创建用户</h2>
    <el-form :model="user" label-width="80px" @submit.prevent="submit" class="form-content">
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
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { fetchUser, createUser, updateUser } from '../api/user';
import { useRoute, useRouter } from 'vue-router';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();

    const user = reactive({ name: '', email: '' });

    const fetchUserData = async () => {
      const id = route.params.id;
      if (id) {
        try {
          const response = await fetchUser(id);
          Object.assign(user, response.data);
        } catch (error) {
          alert('加载用户失败');
        }
      }
    };

    const submit = async () => {
      const id = route.params.id;
      try {
        if (id) {
          await updateUser(id, user);
        } else {
          await createUser(user);
        }
        router.push('/');
      } catch (error) {
        alert(id ? '更新失败' : '创建失败');
      }
    };

    onMounted(() => {
      fetchUserData();
    });

    return {
      user,
      submit,
    };
  },
};
</script>

<style>


.form-content {
  width: 100%;
  max-width: 500px; /* 限制表单宽度 */
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
}
</style>
