<template>
    <div>
        <el-table
                :data="questionnaires"
                style="width: 100%">
            <el-table-column
                    label="问卷ID"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.questionnaireId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="问卷URI"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.uri }}</span>
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
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-button type="primary" @click="handleAddQuestionnaire()" style="margin-top: 50px">新增问卷<i
                class="el-icon-upload el-icon--right"/></el-button>

        <el-dialog title="问卷编辑" :visible.sync="editDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="问卷ID" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.questionnaireId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="问卷地址" :label-width="formLabelWidth">
                    <el-input v-model="form.uri" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleChangeUpload()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="新增问卷" :visible.sync="addDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="问卷地址" :label-width="formLabelWidth">
                    <el-input v-model="form.uri" autocomplete="off"/>
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
        name: "QuestionnaireManagement",
        data() {
            return {
                questionnaires: [],
                editDialogFormVisible: false,
                addDialogFormVisible: false,
                currentEditRow: null,
                formLabelWidth: "120px",
                form: {
                    questionnaireId: null,
                    uri: ''
                }
            }
        },
        mounted() {
            let that = this;
            axios
                .get(url + "/admin/questionnaires?access_token=" + window.sessionStorage.getItem("access_token"))
                .then(function (response) {
                    that.questionnaires = response.data;
                })
        },
        methods: {
            handleEdit(index, row) {
                this.currentEditRow = row;
                for (let key in this.form) {
                    this.form[key] = row[key];
                }
                this.editDialogFormVisible = true;
            },
            handleDelete(index, row) {
                let that = this;
                axios.delete(
                    url + "/admin/questionnaire/" + row.questionnaireId + '?access_token=' +  + window.sessionStorage.getItem("access_token"),
                ).then(response => {
                    if (response.data === 1) {
                        that.questionnaires.splice(index, 1);
                    } else {
                    }
                }).catch(reason => {
                    console.log(reason);
                });
            },
            handleUpload() {
                let that = this;
                axios({
                    url: url + "/admin/questionnaire?access_token=" +  + window.sessionStorage.getItem("access_token"),
                    method: "post",
                    data: JSON.stringify(that.form),
                    headers:
                        {
                            'Content-Type': 'application/json'
                        }
                }).then(response => {
                    //由于Questionnaire有自增主键，因此这里的处理方法有所不同
                    //FIXME:必须要用状态码来让前端判断后端处理情况，不能用是否为null判断
                    if (response.data !== null) {
                        that.questionnaires.push(response.data);
                    } else {
                    }
                }).catch(reason => {
                    console.log(reason);
                });
                that.addDialogFormVisible = false;
            },
            handleAddQuestionnaire() {
                for (let key in this.form) {
                    this.form[key] = ''
                }
                this.addDialogFormVisible = true;
            },
            handleChangeUpload() {
                let that = this;
                axios({
                    url: url + "/admin/questionnaire?access_token=" + window.sessionStorage.getItem("access_token"),
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
                        } else {
                        }
                    });
                this.editDialogFormVisible = false;
            }
        }
    }
</script>

<style scoped>

</style>