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
            <el-table-column
                    label="题目数量"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.exerciseNum }}</span>
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

        <el-dialog title="管理题目" :visible.sync="manageDialogVisible">
            <el-transfer
                    style="text-align: left; display: inline-block"
                    :titles="['可选择题目', '已在题库中']"
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
                exercises: [],
                selected: [],
                currentEditRow: null,
            }
        },
        methods: {
            handleDelete(index, row) {
                console.log(index, row);
            },
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleManageExercises(index, row) {
                let that = this;
                that.exercises = [];
                that.selected = [];
                axios.get(
                    url + '/admin/exercises'
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
                    url + '/admin/repo/' + row.repoId
                ).then(response => {
                    console.log(response.data);
                    that.selected = response.data;
                }).catch(reason => {
                    console.log(reason);
                });

                this.currentEditRow = row;
                this.manageDialogTableVisible = true;
            },
            handleManageExercisesUpload() {
                let that = this;
                axios({
                    url: url + '/admin/repo/bindRepoAndExercise',
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
                        console.log("后端更新绑定成功");
                    } else {
                        console.log(response.data)
                    }
                }).catch(reason => {
                    console.log(reason);
                });

                this.manageDialogTableVisible = false;
            }
        },
        mounted() {
            let that = this;
            axios
                .get(url + "/admin/repos")
                .then(function (response) {
                    that.repos = response.data;
                })
        }
    }
</script>

<style scoped>

</style>