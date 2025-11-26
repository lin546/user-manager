<template>
  <el-container class="app-container">

    <el-header class="app-header">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
      >    
        <el-menu-item index="0">
          <!-- <img
            style="width: 100px"
            src="src/assets/images/user-manager-logo.svg"
            alt="User Manager Logo"
          /> -->
        </el-menu-item>
        <el-menu-item index="1">用户管理系统</el-menu-item>
      </el-menu>
     
    </el-header>
    <el-main class="app-main">
      <el-row>
        <el-col :span="6">
          <div class="grid-content ep-bg-purple" />
        </el-col>
        <el-col :span="12">
        <router-view />
        </el-col>
        <el-col :span="6">
          <div class="grid-content ep-bg-purple" />
        </el-col>
      </el-row>
      
    </el-main>
    <el-footer>
    <el-row>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple" />
      </el-col>
      <el-col :span="8">
      <p>&copy; 2025 用户管理系统. All rights reserved.</p>
      </el-col>
      <el-col :span="8">
        <div class="grid-content ep-bg-purple" />
      </el-col>
    </el-row>
  </el-footer>
  </el-container>
</template>

<style>
.app-container {
  min-height: 100vh;
  background: linear-gradient(to bottom, #e3f2fd, #f5f5f5); /* 添加渐变背景 */
  color: #333;
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial;
}


</style>