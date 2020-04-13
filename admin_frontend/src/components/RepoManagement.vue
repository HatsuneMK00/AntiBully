<template>
    <div>
        <el-table
                :data="repos"
                style="width: 100%">
            <el-table-column
                    label="题库ID"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.repoId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="题库名称"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.title }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="primary"
                            @click="handleManageExercises(scope.$index, scope.row)">管理题目
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="handleAddRepo()" style="margin-top: 50px">新增题库<i
                class="el-icon-upload el-icon--right"/></el-button>

        <el-dialog title="题库编辑" :visible.sync="editDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="题库ID" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.repoId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="题库名称" :label-width="formLabelWidth">
                    <el-input v-model="form.title" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleChangeUpload()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="管理题目" :visible.sync="manageDialogVisible">
            <el-transfer
                    style="text-align: left; display: inline-block"
                    :titles="['已在题库中', '可选择题目']"
                    filterable
                    filter-placeholder="请输入题干搜索"
                    v-model="selected"
                    :data="exercises">
            </el-transfer>

            <span slot="footer" class="dialog-footer">
                <el-button @click="manageDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleManageExercisesUpload()">确 定</el-button>
            </span>
        </el-dialog>

        <el-dialog title="新增题库" :visible.sync="addDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="名称" :label-width="formLabelWidth">
                    <el-input v-model="form.title" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpload()">完 成</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import url from "@/main";
    import axios from 'axios'

    export default {
        name: "RepoManagement",
        data() {
            return {
                repos: [],
                manageDialogVisible: false,
                editDialogFormVisible: false,
                addDialogFormVisible: false,
                exercises: [],
                selected: [],
                currentEditRow: null,
                formLabelWidth: "120px",
                form: {
                    repoId: null,
                    title: '',
                }
            }
        },
        methods: {
            handleDelete(index, row) {
                let that = this;
                axios.delete(
                    url + "/admin/repo/" + row.repoId + '?access_token=' + window.sessionStorage.getItem("access_token"),
                ).then(response => {
                    if (response.data === 1) {
                        that.repos.splice(index, 1);
                    }
                }).catch(reason => {
                    console.log(reason);
                });
            },
            handleEdit(index, row) {
                this.editDialogFormVisible = true;
                this.currentEditRow = row;
                for (let key in this.form) {
                    this.form[key] = row[key];
                }
            },
            handleAddRepo() {
                for (let key in this.form) {
                    this.form[key] = ''
                }
                this.addDialogFormVisible = true;
            },
            handleUpload() {
                let that = this;
                axios({
                    url: url + "/admin/repo?access_token=" + window.sessionStorage.getItem("access_token"),
                    method: "post",
                    data: JSON.stringify(that.form),
                    headers:
                        {
                            'Content-Type': 'application/json'
                        }
                }).then(response => {
                    //由于Repo有自增主键，因此这里的处理方法有所不同
                    //这边即使是错误的情况 后端返回null response.data依旧是非null的值 很奇怪
                    //FIXME:必须要用状态码来让前端判断后端处理情况，不能用是否为null判断
                    if (response.data !== null) {
                        that.repos.push(response.data);
                    } else {
                    }
                }).catch(reason => {
                    console.log(reason)
                });
                that.addDialogFormVisible = false;
            },
            handleChangeUpload() {
                let that = this;
                axios({
                    url: url + "/admin/repo?access_token=" + window.sessionStorage.getItem("access_token"),
                    method: "put",
                    data: JSON.stringify(that.form),
                    headers:
                        {
                            'Content-Type': 'application/json'
                        }
                })
                    .then(response => {
                        if (response.data === 1) {
                            for (let key in that.form) {
                                that.currentEditRow[key] = that.form[key];
                            }
                        }
                    });
                this.editDialogFormVisible = false;
            },
            handleManageExercises(index, row) {
                let that = this;
                that.exercises = [];
                that.selected = [];
                axios.get(
                    url + '/admin/exercises?access_token=' + window.sessionStorage.getItem("access_token")
                ).then(response => {
                    response.data.forEach((exercise, index) => {
                        that.exercises.push({
                            label: exercise.content,
                            key: exercise.exerciseId
                        })
                    })
                }).catch(reason => {
                    console.log(reason);
                });
                axios.get(
                    url + '/admin/repo/' + row.repoId + '?access_token=' + window.sessionStorage.getItem("access_token")
                ).then(response => {
                    that.selected = response.data;
                }).catch(reason => {
                    console.log(reason);
                });

                this.currentEditRow = row;
                this.manageDialogVisible = true;
            },
            handleManageExercisesUpload() {
                let that = this;
                axios({
                    url: url + '/admin/repo/bindRepoAndExercise?access_token=' + window.sessionStorage.getItem("access_token"),
                    method: "post",
                    data: JSON.stringify({
                        selected: that.selected,
                        repoId: that.currentEditRow.repoId
                    }),
                    headers:
                        {
                            'Content-Type': 'application/json'
                        }
                }).then(response => {
                    if (response.data >= 1) {
                    } else {
                    }
                }).catch(reason => {
                    console.log(reason);
                });

                this.manageDialogVisible = false;
            }
        },
        mounted() {
            let that = this;
            axios
                .get(url + "/admin/repos?access_token=" + window.sessionStorage.getItem("access_token"))
                .then(function (response) {
                    that.repos = response.data;
                })
        }
    }
</script>

<style scoped>

</style>