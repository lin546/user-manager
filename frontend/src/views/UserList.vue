<template>
      <div class="table-header">
        <h2>用户列表</h2>
        <router-link to="/create">
          <el-button type="primary">新增用户</el-button>
        </router-link>
      </div>
      <!-- TODO: 添加搜索/标签筛选栏 -->

      <div class="filters">
        <el-input v-model="keyword" placeholder="按姓名/邮箱搜索" clearable @clear="loadUsers" @keyup.enter="loadUsers" />
        <el-input v-model="tag" placeholder="按标签筛选" clearable @clear="loadUsers" @keyup.enter="loadUsers" />
        <el-button type="primary" @click="loadUsers">查询</el-button>
      </div>

      <el-table :data="users" style="width: 100%">
        <el-table-column prop="name" label="姓名" width="180" />
        <el-table-column prop="email" label="邮箱" />
        <!-- TODO: 展示用户标签 -->

        <el-table-column prop="tags" label="标签">
          <template #default="{ row }">
            <el-tag v-for="t in (row.tags ? row.tags.split(',') : [])" :key="t" class="tag">{{ t }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column label="操作">
          <template #default="scope">
            <el-button @click="openEditDialog(scope.row)" type="primary" size="small">编辑</el-button>
            <!-- TODO: 编辑标签按钮 -->
            <el-button @click="editTags(scope.row)" size="small">标签</el-button>
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
      <!-- TODO: 标签编辑弹窗 -->
      <el-dialog v-model="tagsDialogVisible" title="编辑标签">
        <el-input v-model="editingTags" placeholder="用逗号分隔，如：VIP,内部"></el-input>
        <template #footer>
          <el-button @click="tagsDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveTags">保存</el-button>
        </template>
      </el-dialog>
</template>

<script>
import { ref, reactive, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { fetchUsers, updateUser, deleteUser, updateUserTags } from '../api/user';

export default {
  setup() {
    const placeholderUsers = [
      { id: 1, name: '测试用户1', email: 'test1@example.com' },
      { id: 2, name: '测试用户2', email: 'test2@example.com' },
    ];
    const users = ref(placeholderUsers);
    // TODO: 搜索/标签筛选状态
    const keyword = ref('');
    const tag = ref('');
    const editDialogVisible = ref(false);
    const currentUser = reactive({});
    // TODO: 标签编辑状态
    const tagsDialogVisible = ref(false);
    const editingUserId = ref(null);
    const editingTags = ref('');

    const loadUsers = async () => {
      try {
        // TODO: 带查询参数调用
        const response = await fetchUsers({ keyword: keyword.value, tag: tag.value });
        // const response = await fetchUsers();
        users.value = response.data.length > 0 ? response.data : placeholderUsers;
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
        await updateUser(currentUser.id, currentUser);
        ElMessage.success('用户信息更新成功');
        editDialogVisible.value = false;
        loadUsers();
      } catch (error) {
        console.error('编辑用户失败:', error);
        ElMessage.error('编辑用户失败');
      }
    };

    const removeUser = async (user) => {
      try {
        await deleteUser(user.id);
        ElMessage.success('用户删除成功');
        loadUsers();
      } catch (error) {
        console.error('删除用户失败:', error);
        ElMessage.error('删除用户失败');
      }
    };
    // TODO: 标签编辑方法
    const editTags = (user) => {
      editingUserId.value = user.id;
      editingTags.value = user.tags || '';
      tagsDialogVisible.value = true;
    };
    const saveTags = async () => {
      await updateUserTags(editingUserId.value, editingTags.value);
      tagsDialogVisible.value = false;
      loadUsers();
    };

    onMounted(() => {
      loadUsers();
    });

    return {
      users,
      loadUsers,
      // TODO: 返回搜索/标签筛选状态
      keyword,
      tag,
      editDialogVisible,
      currentUser,
      openEditDialog,
      // TODO: 暴露标签编辑方法
      editTags,
      saveTags,
      tagsDialogVisible,
      editingTags,
      saveUser,
      deleteUser: removeUser,
    };
  },
};
</script>

<style>

</style>
