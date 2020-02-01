<template>
    <div>
        <el-table
                :data="courses"
                style="width: 100%">
            <el-table-column
                    label="章节"
                    width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.chapter }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="课程ID"
                    width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.courseId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="视频URI"
                    width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.uri }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="视频URI"
                    width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.duration }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="课后习题编号"
                    width="200">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.exerciseId }}</span>
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
        <el-button type="primary" @click="handleAddCourse()" class="additional-button">新增课程<i
                class="el-icon-upload el-icon--right"/></el-button>

        <el-dialog title="课程编辑" :visible.sync="editDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="章节" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.chapter" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课程ID" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.courseId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="视频URI" :label-width="formLabelWidth">
                    <el-input v-model="form.uri" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课后习题ID" :label-width="formLabelWidth">
                    <el-input v-model="form.exerciseId" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleChangeUpload()">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="课程上传" :visible.sync="addDialogFormVisible">
            <el-form :model="form">
                <el-form-item label="章节" :label-width="formLabelWidth">
                    <el-input v-model="form.chapter" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课程ID" :label-width="formLabelWidth">
                    <el-input v-model="form.courseId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="视频URI" :label-width="formLabelWidth">
                    <el-input v-model="form.uri" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="课后习题ID" :label-width="formLabelWidth">
                    <el-input v-model="form.exerciseId" autocomplete="off"/>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addDialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleUpload()">上 传</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import axios from 'axios'
    import url from '../main.js'

    export default {
        name: "CourseManagement",
        data() {
            return {
                courses: [],
                editDialogFormVisible: false,
                addDialogFormVisible: false,
                formLabelWidth: "120px",
                form: {
                    chapter: '',
                    courseId: '',
                    uri: '',
                    exerciseId: ''
                }
            }
        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
                this.editDialogFormVisible = true;
                for (let key in this.form) {
                    this.form[key] = row[key];
                }
            },
            handleDelete(index, row) {
                alert("删除成功");
                console.log(index, row);
            },
            handleAddCourse() {
                for (let key in this.form) {
                    this.form[key] = ''
                }
                this.addDialogFormVisible = true;
            },
            handleUpload() {
                alert("新增课程成功");
            },
            handleChangeUpload() {
                let that = this;
                axios({
                    url: url + "/admin/course",
                    method: "put",
                    data: JSON.stringify(that.form),
                    headers:
                       {
                         'Content-Type': 'application/json'
                       }
                })
                    .then(response => (console.log(response)));
                alert("课程修改成功");
                this.editDialogFormVisible = false;
            },
        },
        mounted() {
            //TODO: 记笔记 这里this指向在then里面就变了 需要在外面存一下this
            let that = this;
            console.log(url + '/admin/courses');
            axios
                .get(url + "/admin/courses")
                .then(function (response) {
                    console.log(response.data);
                    that.courses = response.data;
                })
        }
    }
</script>

<style>
    .additional-button {
        margin-top: 50px;
    }

</style>