<template>
  <el-table :data="users" style="width: 100%">
    <el-table-column prop="name" label="姓名" width="180" />
    <el-table-column prop="email" label="邮箱" />
    <el-table-column label="操作">
      <template #default="scope">
        <el-button @click="editUser(scope.row)" type="primary" size="small">编辑</el-button>
        <el-button @click="deleteUser(scope.row)" type="danger" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import axios from 'axios';
import { ElMessageBox, ElMessage } from 'element-plus';

export default {
  data() {
    return {
      users: [{ id: 1, name: '测试用户1', email: 'test1@example.com' },
          { id: 2, name: '测试用户2', email: 'test2@example.com' }], // 初始化为空数组
    };
  },
  methods: {
    async fetchUsers() {
      try {
        const response = await axios.get('/api/users'); // 调用后端接口
        this.users = response.data.length > 0 ? response.data : [
          { id: 1, name: '测试用户1', email: 'test1@example.com' },
          { id: 2, name: '测试用户2', email: 'test2@example.com' },
        ]; // 如果数据为空，添加测试数据
      } catch (error) {
        console.error('获取用户数据失败:', error);
      }
    },
    async editUser(user) {
      try {
        const updatedUser = await ElMessageBox.prompt(
          '请输入新的用户信息',
          '编辑用户',
          {
            confirmButtonText: '保存',
            cancelButtonText: '取消',
            inputValue: JSON.stringify(user, null, 2),
            inputType: 'textarea',
          }
        );

        const parsedUser = JSON.parse(updatedUser.value);
        await axios.put(`/api/users/${user.id}`, parsedUser);
        ElMessage.success('用户信息更新成功');
        this.fetchUsers(); // 重新加载用户数据
      } catch (error) {
        if (error !== 'cancel') {
          console.error('编辑用户失败:', error);
          ElMessage.error('编辑用户失败');
        }
      }
    },
    async deleteUser(user) {
      try {
        await ElMessageBox.confirm(
          `确定要删除用户 ${user.name} 吗？`,
          '删除用户',
          {
            confirmButtonText: '删除',
            cancelButtonText: '取消',
            type: 'warning',
          }
        );

        await axios.delete(`/api/users/${user.id}`);
        ElMessage.success('用户删除成功');
        this.fetchUsers(); // 重新加载用户数据
      } catch (error) {
        if (error !== 'cancel') {
          console.error('删除用户失败:', error);
          ElMessage.error('删除用户失败');
        }
      }
    },
  },
  mounted() {
    // this.fetchUsers(); // 组件加载时调用接口获取数据
  },
};
</script>

<style>
/* 添加一些样式以适配 Element Plus */
</style>
