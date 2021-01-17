<template>
    <div>
        <el-breadcrumb separator-class="el-icon-arrow-right" style="line-height: 40px">
            <el-breadcrumb-item v-for="(item)  in levelList" :key="item.path" :to="item.redirect||item.path">
                {{item.meta.title}}
            </el-breadcrumb-item>
            <!--      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
            <!--      <el-breadcrumb-item>活动管理</el-breadcrumb-item>-->
            <!--      <el-breadcrumb-item>活动列表</el-breadcrumb-item>-->
            <!--      <el-breadcrumb-item>活动详情</el-breadcrumb-item>-->
        </el-breadcrumb>
    </div>
</template>

<script>
    export default {
        name: "NavBar",
        data() {
            return {
                levelList: ''
            }
        },
        watch: {
            $route(to, from) {
                this.getBreadcrumb()
            }
        },
        created(){
            this.getBreadcrumb()
        },
        methods:{
            getBreadcrumb() {
                let matched = this.$route.matched.filter(item => item.meta.title)
                this.levelList = matched;
            },
        }
    }
</script>

<style scoped>

</style>