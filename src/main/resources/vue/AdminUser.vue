<template>
    <div class="components-container">
        <div class="filter-container">
            <el-input v-model="tableQuery.name" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="查询信息"></el-input>            <el-button style="margin-left: 10px;" @click="handleFilter" type="primary"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="primary"><i class="el-icon-refresh"></i></el-button>
            <el-button @click="showDialog('create')" type="primary"><i class="el-icon-plus"></i> 新增[[]]</el-button>
        </div>

        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="用户id" prop="id" align="center"></el-table-column>
            <el-table-column label="手机号" prop="phone" align="center"></el-table-column>
            <el-table-column label="姓名" prop="name" align="center"></el-table-column>
            <el-table-column label="邮箱" prop="email" align="center"></el-table-column>
            <el-table-column label="企业微信授权登录用户的名称" prop="fullName" align="center"></el-table-column>
            <el-table-column label="企业微信授权登录用户的头像" prop="avatar" align="center"></el-table-column>
            <el-table-column label="企业微信授权登录用户的职位" prop="position" align="center"></el-table-column>
            <el-table-column label="" prop="authKey" align="center"></el-table-column>
            <el-table-column label="" prop="passwordHash" align="center"></el-table-column>
            <el-table-column label="" prop="passwordResetToken" align="center"></el-table-column>
            <el-table-column label="状态 0-正常 1-停用" prop="status" align="center"></el-table-column>
            <el-table-column label="创建时间" prop="createAt" align="center"></el-table-column>
            <el-table-column label="更新时间" prop="updateAt" align="center"></el-table-column>
            <el-table-column label="创建人" prop="createBy" align="center"></el-table-column>
            <el-table-column label="更新人" prop="updateBy" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button size="small" @click="showDialog('update', scope.row)" type="primary"><i class="el-icon-edit"></i> </el-button>
                    <el-button size="small" @click="deleteRecord(scope.row.id)" type="danger"><i class="el-icon-delete"></i> </el-button>
                </template>
            </el-table-column>
        </el-table>

        <div class="pagination-container">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                           :current-page.sync="tableQuery.currentPage" :page-sizes="[10, 20, 50]"
                           :page-size="tableQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

        <el-dialog :title="formTitle" :visible.sync="formVisible" width="35%">
            <el-form :model="createdForm" label-position="left" label-width="70px" style="width: 80%; margin-left:0px;">
                <el-form-item label="手机号">
                    <el-input v-model="createdForm.phone" placeholder="请填写手机号"></el-input>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-input v-model="createdForm.name" placeholder="请填写姓名"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                    <el-input v-model="createdForm.email" placeholder="请填写邮箱"></el-input>
                </el-form-item>
                <el-form-item label="企业微信授权登录用户的名称">
                    <el-input v-model="createdForm.fullName" placeholder="请填写企业微信授权登录用户的名称"></el-input>
                </el-form-item>
                <el-form-item label="企业微信授权登录用户的头像">
                    <el-input v-model="createdForm.avatar" placeholder="请填写企业微信授权登录用户的头像"></el-input>
                </el-form-item>
                <el-form-item label="企业微信授权登录用户的职位">
                    <el-input v-model="createdForm.position" placeholder="请填写企业微信授权登录用户的职位"></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-input v-model="createdForm.authKey" placeholder="请填写"></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-input v-model="createdForm.passwordHash" placeholder="请填写"></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-input v-model="createdForm.passwordResetToken" placeholder="请填写"></el-input>
                </el-form-item>
                <el-form-item label="状态 0-正常 1-停用">
                    <el-input v-model="createdForm.status" placeholder="请填写状态 0-正常 1-停用"></el-input>
                </el-form-item>
                <el-form-item label="创建人">
                    <el-input v-model="createdForm.createBy" placeholder="请填写创建人"></el-input>
                </el-form-item>
                <el-form-item label="更新人">
                    <el-input v-model="createdForm.updateBy" placeholder="请填写更新人"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="formVisible = false">取 消</el-button>
                <el-button type="primary" :loading="formSubmiting" @click="commitForm">确 认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { confirmBox } from 'utils/message';
    import { isJson } from 'utils';

    export default {
        data() {
            return {
                tableQuery: {
                    limit: 10,
                    currentPage: 1,
                },
                total: null,
                tableData: [],
                tableLoading: true,
                formTitle: null,
                formVisible: false,
                formSubmiting: false,
                createdForm: {
                    id: null,
                    phone: null,
                    name: null,
                    email: null,
                    fullName: null,
                    avatar: null,
                    position: null,
                    authKey: null,
                    passwordHash: null,
                    passwordResetToken: null,
                    status: null,
                    createBy: null,
                    updateBy: null,
                },
                deletedForm: {
                    id: null
                },
            };
        },
        mounted() {
            this.getList();
        },
        methods: {
            getList() {
                this.tableLoading = true;
                this.$api.module.adminUser.list({
                    data: this.tableQuery
                }).then(response => {
                    this.total = response.data.data.total;
                    this.tableData = response.data.data.list;
                    this.tableLoading = false;
                }).catch(error => {
                    this.tableLoading = false;
                    console.log(error);
                });
            },
            handleFilter() {
                this.getList();
            },
            handleReset() {
                this.tableQuery.limit = 10;
                this.tableQuery.currentPage = 1;

                this.getList();
            },
            handleSizeChange(val) {
                this.tableQuery.limit = val;
                this.getList();
            },
            handleCurrentChange(val) {
                this.tableQuery.currentPage = val;
                this.getList();
            },
            clearDialog() {
                this.createdForm.id = null;
                this.createdForm.phone = null;
                this.createdForm.name = null;
                this.createdForm.email = null;
                this.createdForm.fullName = null;
                this.createdForm.avatar = null;
                this.createdForm.position = null;
                this.createdForm.authKey = null;
                this.createdForm.passwordHash = null;
                this.createdForm.passwordResetToken = null;
                this.createdForm.status = null;
                this.createdForm.createBy = null;
                this.createdForm.updateBy = null;
            },
            showDialog(type, row = null) {
                this.formVisible = true;
                this.clearDialog();
                if (type == 'create') {
                    this.formTitle = "新增[[]]信息";
                } else {
                    this.formTitle = "修改[[]]信息";
                    Object.assign(this.createdForm, row);
                    if (isJson(this.createdForm.value)) {
                        this.createdForm.value = JSON.parse(this.createdForm.value);
                    }
                }
            },
            commitForm() {
                this.formSubmiting = true;
                this.$api.module.adminUser.save({
                    data: this.createdForm
                }).then(response => {
                    this.formSubmiting = false;
                    this.formVisible = false;
                    this.$notify({
                        title: '成功',
                        message: '保存成功',
                        type: 'success',
                        duration: 1500,
                    });
                    this.handleFilter();
                    console.log(response);
                }).catch(error => {
                    this.formSubmiting = false;
                    this.$notify({
                        title: '错误',
                        message: '保存失败',
                        type: 'error',
                        duration: 3000,
                    });
                    console.log(error);
                });
            },
            deleteRecord(id) {
                confirmBox('[[]]').then(() => {
                    this.deletedForm.id = id;
                    this.$api.module.adminUser.delete({
                        data: this.deletedForm
                    }).then(response => {
                        this.$notify({
                            title: '成功',
                            message: '删除成功',
                            type: 'success',
                            duration: 1500,
                        });
                        this.handleFilter();
                        console.log(response);
                    }).catch(error => {
                        this.formSubmiting = false;
                        this.$notify({
                            title: '错误',
                            message: '删除失败',
                            type: 'error',
                            duration: 3000,
                        });
                        console.log(error);
                    });
                });
            },
        }
    };
</script>
