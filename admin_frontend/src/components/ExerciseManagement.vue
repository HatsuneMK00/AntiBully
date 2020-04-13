<template>
    <div>
        <el-table
                :data="exercises.filter(exercise => !search || exercise.content.toLowerCase().includes(search.toLowerCase()))"
                style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="props">
                    <el-form label-position="left" class="demo-table-expand">
                        <el-form-item label="题干">
                            <span>{{ props.row.content }}</span>
                        </el-form-item>
                        <el-form-item label="选项A">
                            <span>{{ props.row.choiceA }}</span>
                        </el-form-item>
                        <el-form-item label="选项B">
                            <span>{{ props.row.choiceB }}</span>
                        </el-form-item>
                        <el-form-item label="选项C">
                            <span>{{ props.row.choiceC }}</span>
                        </el-form-item>
                        <el-form-item label="选项D">
                            <span>{{ props.row.choiceD }}</span>
                        </el-form-item>
                        <el-form-item label="正确答案">
                            <span>{{ props.row.answer }}</span>
                        </el-form-item>
                        <el-form-item label="理由">
                            <span>{{ props.row.reason }}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    label="题目ID"
                    width="220">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.exerciseId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="题目类型"
                    width="220">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.type }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="题干"
                    width="440">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.content }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    align="right">
                <template slot="header" slot-scope="scope">
                    <el-input
                            v-model="search"
                            size="mini"
                            placeholder="输入关键字搜索"/>
                </template>
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="handleAddExercise()" style="margin-top: 50px">新增题目<i
                class="el-icon-upload el-icon--right"/></el-button>

        <el-dialog title="题目编辑" :visible.sync="editDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="题目ID" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.exerciseId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="题干" :label-width="formLabelWidth">
                    <el-input v-model="form.content" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="题目类型" :label-width="formLabelWidth">
                    <el-input v-model="form.type" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项A" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceA" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项B" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceB" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项C" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceC" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项D" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceD" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="正确答案" :label-width="formLabelWidth">
                    <el-input v-model="form.answer" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="理由" :label-width="formLabelWidth">
                    <el-input v-model="form.reason" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleChangeUpload()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="添加题目" :visible.sync="addDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="题干" :label-width="formLabelWidth">
                    <el-input v-model="form.content" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="题目类型" :label-width="formLabelWidth">
                    <el-input v-model="form.type" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项A" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceA" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项B" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceB" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项C" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceC" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="选项D" :label-width="formLabelWidth">
                    <el-input v-model="form.choiceD" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="正确答案" :label-width="formLabelWidth">
                    <el-input v-model="form.answer" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="理由" :label-width="formLabelWidth">
                    <el-input v-model="form.reason" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpload()">添 加</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    import url from '../main.js'
    export default {
        name: "ExerciseManagement",
        data() {
            return {
                exercises: [],
                editDialogFormVisible: false,
                addDialogFormVisible: false,
                formLabelWidth: '120px',
                currentEditRow: null,
                form: {
                    exerciseId: '',
                    content: '',
                    answer: '',
                    type: '',
                    reason: '',
                    choiceA: '',
                    choiceB: '',
                    choiceC: '',
                    choiceD: '',
                },
                search: '',
            }
        },
        mounted() {
            let that = this;
            axios
                .get(url + "/admin/exercises?access_token=" + window.sessionStorage.getItem("access_token"))
                .then(response => {
                    that.exercises = response.data;
                })
        },
        methods: {
            handleEdit(index, row) {
                this.editDialogFormVisible = true;
                this.currentEditRow = row;
                for (let key in this.form) {
                    this.form[key] = row[key];
                }
            },
            handleDelete(index, row) {
                let that = this;
                axios.delete(
                    url + "/admin/exercise/" + row.exerciseId + "?access_token=" + window.sessionStorage.getItem("access_token"),
                ).then(response => {
                    if (response.data === 1) {
                        that.exercises.splice(index, 1);
                    }
                }).catch(reason => {
                    console.log(reason);
                });
            },
            handleAddExercise() {
                for (let key in this.form) {
                    this.form[key] = ''
                }
                this.addDialogFormVisible = true;
            },
            handleChangeUpload() {
                let that = this;
                axios({
                    url: url + "/admin/exercise?access_token=" + window.sessionStorage.getItem("access_token"),
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
                    })
                    .catch(error => {
                        console.log(error);
                    });
                this.editDialogFormVisible = false
            },
            handleUpload() {
                let that = this;
                axios({
                    url: url + "/admin/exercise?access_token=" + window.sessionStorage.getItem("access_token"),
                    method: "post",
                    data: JSON.stringify(that.form),
                    headers:
                        {
                            'Content-Type': 'application/json'
                        }
                }).then(response => {
                    //由于Exercise有自增主键，因此这里的处理方法有所不同
                    if (response.data !== null) {
                        that.exercises.push(response.data);
                    } else {
                    }
                }).catch(reason => {
                    console.log(reason)
                });
                that.addDialogFormVisible = false;
            }
        }
    }
</script>

<style>
    .demo-table-expand {
        font-size: 0;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>