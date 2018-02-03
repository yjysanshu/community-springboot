<template>
    <div class="components-container">
        <div class="filter-container">
            <el-input v-model="tableQuery.name" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="查询信息"></el-input>            <el-button style="margin-left: 10px;" @click="handleFilter" type="primary"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="primary"><i class="el-icon-refresh"></i></el-button>
            <el-button @click="showDialog('create')" type="primary"><i class="el-icon-plus"></i> 新增[[]]</el-button>
        </div>

        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="报修ID" prop="id" align="center"></el-table-column>
            <el-table-column label="报修单号" prop="code" align="center"></el-table-column>
            <el-table-column label="报修房间ID" prop="roomId" align="center"></el-table-column>
            <el-table-column label="报修范围ID" prop="repairRangeId" align="center"></el-table-column>
            <el-table-column label="报修联系方式" prop="phone" align="center"></el-table-column>
            <el-table-column label="报修状态 0-未处理，1-已修复，2-其他问题" prop="status" align="center"></el-table-column>
            <el-table-column label="报修处理人" prop="adminUserId" align="center"></el-table-column>
            <el-table-column label="报修详细描述" prop="description" align="center"></el-table-column>
            <el-table-column label="创建时间" prop="createAt" align="center"></el-table-column>
            <el-table-column label="更新时间" prop="updateAt" align="center"></el-table-column>
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
                <el-form-item label="报修单号">
                    <el-input v-model="createdForm.code" placeholder="请填写报修单号"></el-input>
                </el-form-item>
                <el-form-item label="报修房间ID">
                    <el-input v-model="createdForm.roomId" placeholder="请填写报修房间ID"></el-input>
                </el-form-item>
                <el-form-item label="报修范围ID">
                    <el-input v-model="createdForm.repairRangeId" placeholder="请填写报修范围ID"></el-input>
                </el-form-item>
                <el-form-item label="报修联系方式">
                    <el-input v-model="createdForm.phone" placeholder="请填写报修联系方式"></el-input>
                </el-form-item>
                <el-form-item label="报修状态 0-未处理，1-已修复，2-其他问题">
                    <el-input v-model="createdForm.status" placeholder="请填写报修状态 0-未处理，1-已修复，2-其他问题"></el-input>
                </el-form-item>
                <el-form-item label="报修处理人">
                    <el-input v-model="createdForm.adminUserId" placeholder="请填写报修处理人"></el-input>
                </el-form-item>
                <el-form-item label="报修详细描述">
                    <el-input v-model="createdForm.description" placeholder="请填写报修详细描述"></el-input>
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
                    code: null,
                    roomId: null,
                    repairRangeId: null,
                    phone: null,
                    status: null,
                    adminUserId: null,
                    description: null,
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
                this.$api.module.repairOrder.list({
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
                this.createdForm.code = null;
                this.createdForm.roomId = null;
                this.createdForm.repairRangeId = null;
                this.createdForm.phone = null;
                this.createdForm.status = null;
                this.createdForm.adminUserId = null;
                this.createdForm.description = null;
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
                this.$api.module.repairOrder.save({
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
                    this.$api.module.repairOrder.delete({
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
