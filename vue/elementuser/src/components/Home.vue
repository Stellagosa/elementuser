<template>
    <div class="home" style="height: 100%">
        <el-container>
            <el-header>
                <head-top></head-top>
            </el-header>
            <el-main>
                <el-container>
                    <el-aside width="200px" style="border-right: 1px solid #dcdfe6">
                        <el-menu
                                router
                                style="min-height: 100%;"
                                @open="handleOpen"
                                @close="handleClose">
                            <el-submenu index="1">
                                <template slot="title">
                                    <i class="el-icon-location"></i>
                                    <span>导航一</span>
                                </template>
                                <el-menu-item-group>
                                    <template slot="title">分组一</template>
                                    <el-menu-item index="/home">选项1</el-menu-item>
                                    <el-menu-item index="/home">选项2</el-menu-item>
                                </el-menu-item-group>
                                <el-menu-item-group title="分组2">
                                    <el-menu-item index="/home">选项3</el-menu-item>
                                </el-menu-item-group>
                                <el-submenu index="1-4">
                                    <template slot="title">选项4</template>
                                    <el-menu-item index="/home">选项1</el-menu-item>
                                </el-submenu>
                            </el-submenu>
                            <el-menu-item index="/home/userTable">
                                <i class="el-icon-menu"></i>
                                <span slot="title">用户管理</span>
                            </el-menu-item>
                            <el-menu-item index="/home">
                                <i class="el-icon-document"></i>
                                <span slot="title">导航三</span>
                            </el-menu-item>
                            <el-menu-item index="/home">
                                <i class="el-icon-setting"></i>
                                <span slot="title">导航四</span>
                            </el-menu-item>
                        </el-menu>
                    </el-aside>
                    <el-main>
                        <el-container>
                            <el-header height="40px" style="margin: 10px 20px">
                                <nav-bar></nav-bar>
                            </el-header>
                            <el-main style="margin: 0 20px">
                                <router-view></router-view>
                            </el-main>
                        </el-container>
                    </el-main>
                </el-container>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import HeadTop from "./HeadTop.vue";
    import NavBar from "./NavBar";

    export default {
        components: {
            HeadTop,
            NavBar
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    };
</script>

<style scoped>
    .el-aside .el-menu {
        border: none;
    }
    .home .el-container, .el-header, .el-aside, .el-main {
        margin: 0;
        padding: 0;
        height: 100%;
    }
</style>
