<template>
    <div class="components-container">
        <div class="filter-container">
[searchColumn]            <el-button style="margin-left: 10px;" @click="handleFilter" type="primary"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="primary"><i class="el-icon-refresh"></i></el-button>
            <el-button @click="showDialog('create')" type="primary"><i class="el-icon-plus"></i> 新增[[]]</el-button>
        </div>

        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="角色id" prop="id" align="center"></el-table-column>
            <el-table-column label="父角色id" prop="parentId" align="center"></el-table-column>
            <el-table-column label="角色名称" prop="name" align="center"></el-table-column>
            <el-table-column label="角色描述" prop="desc" align="center"></el-table-column>
            <el-table-column label="状态 0-正常 1-停用" prop="status" align="center"></el-table-column>
            <el-table-column label="类型 0-成员角色member 1-群组角色group" prop="type" align="center"></el-table-column>
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
                <el-form-item label="角色id">
                    <el-input v-model="createdForm.id" placeholder="请填写角色id"></el-input>
                </el-form-item>
                <el-form-item label="父角色id">
                    <el-input v-model="createdForm.parentId" placeholder="请填写父角色id"></el-input>
                </el-form-item>
                <el-form-item label="角色名称">
                    <el-input v-model="createdForm.name" placeholder="请填写角色名称"></el-input>
                </el-form-item>
                <el-form-item label="角色描述">
                    <el-input v-model="createdForm.desc" placeholder="请填写角色描述"></el-input>
                </el-form-item>
                <el-form-item label="状态 0-正常 1-停用">
                    <el-input v-model="createdForm.status" placeholder="请填写状态 0-正常 1-停用"></el-input>
                </el-form-item>
                <el-form-item label="类型 0-成员角色member 1-群组角色group">
                    <el-input v-model="createdForm.type" placeholder="请填写类型 0-成员角色member 1-群组角色group"></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input v-model="createdForm.createAt" placeholder="请填写创建时间"></el-input>
                </el-form-item>
                <el-form-item label="更新时间">
                    <el-input v-model="createdForm.updateAt" placeholder="请填写更新时间"></el-input>
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
        components: { jsonEditor },
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
                    parentId: null,
                    name: null,
                    desc: null,
                    status: null,
                    type: null,
                    createAt: null,
                    updateAt: null,
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
                this.$api.module.adminRole.list({
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
                this.createdForm.parentId = null;
                this.createdForm.name = null;
                this.createdForm.desc = null;
                this.createdForm.status = null;
                this.createdForm.type = null;
                this.createdForm.createAt = null;
                this.createdForm.updateAt = null;
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
                this.$api.module.adminRole.save({
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
                    this.$api.module.adminRole.delete({
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
