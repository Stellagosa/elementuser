<template>
    <div class="user">
        <div class="topButton">
            <el-button type="primary" size="mini" @click="showAddUser">添加用户</el-button>
            <el-button type="warning" size="mini" @click="showUpdateUser">修改</el-button>
            <el-button type="danger" size="mini" @click="deleteUser">删除</el-button>
        </div>
        <div class="list">
            <el-table
                    highlight-current-row
                    size="mini"
                    @current-change="handleTableSelectChange"
                    :data="tableData"
                    height="450px">
                <el-table-column
                        label="ID"
                        prop="id">
                </el-table-column>
                <el-table-column
                        label="用户名"
                        prop="username">
                </el-table-column>
                <el-table-column
                        label="性别"
                        prop="sex">
                </el-table-column>
                <el-table-column
                        label="生日"
                        prop="birthday">
                </el-table-column>
                <el-table-column
                        label="地址"
                        prop="address">
                </el-table-column>

            </el-table>
            <div class="page">
                <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handlePageChange"
                        :current-page="currentPage"
                        :page-sizes="[10, 20, 30, 40]"
                        :page-size="10"
                        layout="jumper, prev, pager, next,sizes,total "
                        :total="total">
                </el-pagination>
            </div>
        </div>

        <div id="addUserForm">
            <el-dialog
                    title="添加用户"
                    width="400px"
                    center
                    :close-on-press-escape="true"
                    :visible.sync="addUserDialogVisible"
                    @close="handleClose('addUserForm')">

                <el-form label-width="80px" :model="addForm" :rules="rules" ref="addUserForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="addForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio v-model="addForm.sex" label="男">男</el-radio>
                        <el-radio v-model="addForm.sex" label="女">女</el-radio>
                    </el-form-item>
                    <el-form-item label="生日" prop="birthday">
                        <el-date-picker v-model="addForm.birthday" type="date" placeholder="请选择日期"
                                        value-format="yyyy-MM-dd"
                                        :picker-options="pickerOptions" style="width: 100%"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="地址" prop="address">
                        <el-input v-model="addForm.address"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="resetForm('addUserForm')">重 置</el-button>
                    <el-button type="primary" @click="addUser('addUserForm')">确 定</el-button>
                </span>
            </el-dialog>
        </div>
        <div id="updateUserForm">
            <el-dialog
                    title="修改用户信息"
                    width="400px"
                    center
                    :close-on-press-escape="true"
                    :visible.sync="updateUserDialogVisible"
                    @close="handleClose('updateUserForm')">

                <el-form label-width="80px" :model="updateForm" :rules="rules" ref="updateUserForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="updateForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="性别">
                        <el-radio v-model="updateForm.sex" label="男">男</el-radio>
                        <el-radio v-model="updateForm.sex" label="女">女</el-radio>
                    </el-form-item>
                    <el-form-item label="生日" prop="birthday">
                        <el-date-picker v-model="updateForm.birthday" type="date" placeholder="请选择日期"
                                        value-format="yyyy-MM-dd"
                                        :picker-options="pickerOptions" style="width: 100%"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="地址" prop="address">
                        <el-input v-model="updateForm.address"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
        <el-button @click="resetForm('updateUserForm')">重 置</el-button>
        <el-button type="primary" @click="updateUser('updateUserForm')">确 定</el-button>
      </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserTable",
        data() {
            return {
                tableData: [],
                addForm: {
                    id: null,
                    username: '',
                    sex: '男',
                    birthday: '',
                    address: ''
                },
                updateForm: {
                    id: null,
                    username: '',
                    sex: '男',
                    birthday: '',
                    address: ''
                },
                addUserDialogVisible: false,
                updateUserDialogVisible: false,
                currentSelectId: null,
                currentPage: 1,
                pageSize: 10,
                total: null,
                formTitle: '',
                pickerOptions: {
                    disabledDate(time) {
                        return time.getTime() > Date.now();
                    }
                },
                rules: {
                    username: [
                        {
                            required: true,
                            message: '请输入用户名',
                            trigger: 'blur'
                        },
                        {
                            max: 10,
                            message: '不能大于10个字符',
                            trigger: 'blur'
                        }
                    ],
                    birthday: [
                        {
                            required: true,
                            message: '请输入用户生日',
                            trigger: 'blur'
                        }
                    ],
                    address: [
                        {
                            required: true,
                            message: '请输入用户地址',
                            trigger: 'blur'
                        }
                    ]
                },
            }
        },
        methods: {
            handleSizeChange(val) {
                this.pageSize = val;
                this.loadUsers(this.currentPage, this.pageSize);
            },
            handlePageChange(val) {
                this.currentPage = val;
                this.loadUsers(this.currentPage, this.pageSize);
            },
            handleTableSelectChange(currentRow, oldCurrentRow) {
                if (currentRow != null) {
                    this.currentSelectId = currentRow.id;
                }
            },
            showAddUser() {
                this.addUserDialogVisible = true;
            },
            addUser(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        let _this = this;
                        this.$axios.post('/user/save',
                            {
                                username: _this.addForm.username,
                                sex: _this.addForm.sex,
                                birthday: _this.addForm.birthday,
                                address: _this.addForm.address
                            }).then(res => {
                            if (res.data.status) {
                                _this.$message({
                                    message: res.data.msg,
                                    type: 'success'
                                });
                                _this.loadUsers(_this.currentPage, _this.pageSize);
                            } else {
                                _this.$message.error(res.data.msg);
                            }
                        }).catch(err => {
                            _this.$message.error('服务器连接失败！' + err);
                        });
                        this.addUserDialogVisible = false;
                    } else {
                        this.$message.error('请填写正确的信息');
                    }
                });
            },
            showUpdateUser() {
                this.updateUserDialogVisible = true;
                this.loadUserById(this.currentSelectId);
            },
            updateUser(formName) {
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        let _this = this;
                        this.$axios.put('/user/update',
                            {
                                id: _this.updateForm.id,
                                username: _this.updateForm.username,
                                sex: _this.updateForm.sex,
                                birthday: _this.updateForm.birthday,
                                address: _this.updateForm.address

                            }).then(res => {
                            if (res.data.status) {
                                _this.$message({
                                    message: res.data.msg,
                                    type: 'success'
                                });
                                _this.loadUsers(_this.currentPage, _this.pageSize);
                            } else {
                                _this.$message.error(res.data.msg);
                            }
                        }).catch(err => {
                            _this.$message.error('服务器连接失败！' + err);
                        });
                        this.updateUserDialogVisible = false;
                    } else {
                        console.log('error submit!!');
                        this.$message.error('请填写正确的信息');
                    }
                });
            },
            deleteUser() {
                if (this.currentSelectId != null) {
                    let _this = this;
                    _this.$axios.delete('/user/delete',
                        {
                            params:
                                {
                                    id: _this.currentSelectId
                                }
                        }).then(res => {
                        if (res.data.status) {
                            _this.$message({
                                type: 'success',
                                message: res.data.msg
                            });
                            _this.loadUsers(_this.currentPage, _this.pageSize);
                        } else {
                            _this.$message.error(res.data.msg);
                        }
                    }).catch(err => {
                        _this.$message.error("服务器连接失败!" + err);
                    });
                } else {
                    this.$message.error("请选择需要删除的目标！");
                }
            },
            handleClose(formName) {
                this.$nextTick(this.resetForm(formName));
                if (formName == 'updateUserForm') {
                    this.updateUserDialogVisible = false;
                }
                if (formName == 'addUserForm') {
                    this.addUserDialogVisible = false;
                }
            },
            loadUsers(currentPage, pageSize) {
                let _this = this;
                _this.$axios.get('/user/queryByPage',
                    {
                        params: {
                            currentPage: currentPage,
                            pageSize: pageSize
                        }
                    }).then(res => {
                    _this.tableData = res.data.users;
                    _this.total = res.data.count;
                }).catch(error => {
                    _this.$message.error("服务器连接失败！" + error);
                });
            },
            loadUserById(id) {
                this.$axios.get("/user/queryById",
                    {
                        params: {
                            id: id
                        }
                    }).then(res => {
                    this.updateForm = res.data;
                }).catch(error => {
                    this.$message.error("服务器连接失败！" + error);
                });
            },
            resetForm(formName) {
                if (formName == 'updateUserForm') {
                    this.updateForm = {sex: '男'};
                }
                if (formName == 'addUserForm') {
                    this.addForm = {sex: '男'};
                }
                this.$refs[formName].clearValidate();
            }
        },
        created() {
            this.loadUsers(1, 10);
        }
    }
</script>

<style scoped>
    .user {
        position: relative;
    }

    .user .topButton {
        margin-bottom: 10px;
    }

    .user .list {
        position: relative;
    }

    .user .page {
        position: absolute;
        right: 30px;
        bottom: -50px;
    }

    .topButton .el-button {
        margin: 0 15px 0 0;
    }

    .el-radio {
        margin: 0 20px 0 50px;
    }
</style>
<style>
    .user .list .el-table th, .el-table td {
        text-align: center;
    }
</style>