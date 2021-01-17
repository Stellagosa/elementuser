<template>
    <div>
        <el-container style="height: 60px">
            <el-header>
                <div id="icon">
                    <img src="../assets/logo.png" alt="">
                </div>
            </el-header>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "HeadTop"
    }
</script>

<style scoped>
    .el-header {
        position: relative;
        background-color: rgb(245, 245, 245);
        box-shadow: 0 2px 12px 0 rgba(102, 100, 100, 0.1);
        border-bottom: 1px solid #dcdfe6;
    }

    #icon {
        margin-left: 20px;
    }

    #icon img {
        float: left;
        width: 30px;
        height: auto;
    }
</style>