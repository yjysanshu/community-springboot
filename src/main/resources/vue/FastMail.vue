<template>
    <div class="components-container">
        <div class="filter-container">
            <el-input v-model="tableQuery.name" @keyup.enter.native="handleFilter" style="width: 200px;" placeholder="查询信息"></el-input>            <el-button style="margin-left: 10px;" @click="handleFilter" type="primary"><i class="el-icon-search"></i></el-button>
            <el-button @click="handleReset" type="primary"><i class="el-icon-refresh"></i></el-button>
            <el-button @click="showDialog('create')" type="primary"><i class="el-icon-plus"></i> 新增[[]]</el-button>
        </div>

        <el-table :data="tableData" v-loading.body="tableLoading" element-loading-text="拼命加载中" stripe border fit highlight-current-row style="width: 100%">
            <el-table-column label="快递ID" prop="id" align="center"></el-table-column>
            <el-table-column label="快递单号" prop="orderNo" align="center"></el-table-column>
            <el-table-column label="快递自定义单号" prop="customNo" align="center"></el-table-column>
            <el-table-column label="快递商" prop="business" align="center"></el-table-column>
            <el-table-column label="快递收件人房间ID" prop="receiveRoomId" align="center"></el-table-column>
            <el-table-column label="快递收件人姓名" prop="receiveName" align="center"></el-table-column>
            <el-table-column label="快递收件人联系方式" prop="receivePhone" align="center"></el-table-column>
            <el-table-column label="快递收件人详细地址" prop="receiveAddress" align="center"></el-table-column>
            <el-table-column label="快递状态 0-未收，1-已收" prop="status" align="center"></el-table-column>
            <el-table-column label="其他备注" prop="memo" align="center"></el-table-column>
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
                <el-form-item label="快递单号">
                    <el-input v-model="createdForm.orderNo" placeholder="请填写快递单号"></el-input>
                </el-form-item>
                <el-form-item label="快递自定义单号">
                    <el-input v-model="createdForm.customNo" placeholder="请填写快递自定义单号"></el-input>
                </el-form-item>
                <el-form-item label="快递商">
                    <el-input v-model="createdForm.business" placeholder="请填写快递商"></el-input>
                </el-form-item>
                <el-form-item label="快递收件人房间ID">
                    <el-input v-model="createdForm.receiveRoomId" placeholder="请填写快递收件人房间ID"></el-input>
                </el-form-item>
                <el-form-item label="快递收件人姓名">
                    <el-input v-model="createdForm.receiveName" placeholder="请填写快递收件人姓名"></el-input>
                </el-form-item>
                <el-form-item label="快递收件人联系方式">
                    <el-input v-model="createdForm.receivePhone" placeholder="请填写快递收件人联系方式"></el-input>
                </el-form-item>
                <el-form-item label="快递收件人详细地址">
                    <el-input v-model="createdForm.receiveAddress" placeholder="请填写快递收件人详细地址"></el-input>
                </el-form-item>
                <el-form-item label="快递状态 0-未收，1-已收">
                    <el-input v-model="createdForm.status" placeholder="请填写快递状态 0-未收，1-已收"></el-input>
                </el-form-item>
                <el-form-item label="其他备注">
                    <el-input v-model="createdForm.memo" placeholder="请填写其他备注"></el-input>
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
                    orderNo: null,
                    customNo: null,
                    business: null,
                    receiveRoomId: null,
                    receiveName: null,
                    receivePhone: null,
                    receiveAddress: null,
                    status: null,
                    memo: null,
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
                this.$api.module.fastMail.list({
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
                this.createdForm.orderNo = null;
                this.createdForm.customNo = null;
                this.createdForm.business = null;
                this.createdForm.receiveRoomId = null;
                this.createdForm.receiveName = null;
                this.createdForm.receivePhone = null;
                this.createdForm.receiveAddress = null;
                this.createdForm.status = null;
                this.createdForm.memo = null;
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
                this.$api.module.fastMail.save({
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
                    this.$api.module.fastMail.delete({
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
