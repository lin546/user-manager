<template>
  <el-container class="table-container">
    <el-main>
      <div class="table-header">
        <h2>用户列表</h2>
        <router-link to="/create">
          <el-button type="primary">新增用户</el-button>
        </router-link>
      </div>
      <el-table :data="users" style="width: 100%">
        <el-table-column prop="name" label="姓名" width="180" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button @click="openEditDialog(scope.row)" type="primary" size="small">编辑</el-button>
            <el-button @click="deleteUser(scope.row)" type="danger" size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-dialog v-model="editDialogVisible" 
        title="编辑用户"
        width="30%"
        align-center
      >
        <el-form :model="currentUser" label-width="80px">
          <el-form-item label="姓名">
            <el-input v-model="currentUser.name"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="currentUser.email"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button @click="editDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveUser">保存</el-button>
        </div>
      </el-dialog>
    </el-main>
  </el-container>
</template>

<script>
import { ref, reactive, onMounted } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  setup() {
    const users = ref([
      { id: 1, name: '测试用户1', email: 'test1@example.com' },
      { id: 2, name: '测试用户2', email: 'test2@example.com' },
    ]);
    const editDialogVisible = ref(false);
    const currentUser = reactive({});

    const fetchUsers = async () => {
      try {
        const response = await axios.get('/api/users');
        users.value = response.data.length > 0 ? response.data : [
          { id: 1, name: '测试用户1', email: 'test1@example.com' },
          { id: 2, name: '测试用户2', email: 'test2@example.com' },
        ];
      } catch (error) {
        console.error('获取用户数据失败:', error);
      }
    };

    const openEditDialog = (user) => {
      Object.assign(currentUser, user); // 克隆用户数据
      editDialogVisible.value = true;
    };

    const saveUser = async () => {
      try {
        await axios.put(`/api/users/${currentUser.id}`, currentUser);
        ElMessage.success('用户信息更新成功');
        editDialogVisible.value = false;
        fetchUsers();
      } catch (error) {
        console.error('编辑用户失败:', error);
        ElMessage.error('编辑用户失败');
      }
    };

    const deleteUser = async (user) => {
      try {
        await axios.delete(`/api/users/${user.id}`);
        ElMessage.success('用户删除成功');
        fetchUsers();
      } catch (error) {
        console.error('删除用户失败:', error);
        ElMessage.error('删除用户失败');
      }
    };

    onMounted(() => {
      fetchUsers();
    });

    return {
      users,
      editDialogVisible,
      currentUser,
      fetchUsers,
      openEditDialog,
      saveUser,
      deleteUser,
    };
  },
};
</script>

<style>
.table-container {
  display: flex;
  justify-content: center;
  margin-left: 20%;
  margin-right: 20%;
}

.table-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

</style>
