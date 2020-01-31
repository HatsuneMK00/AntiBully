<template>
    <div>
        <el-table
                :data="courses"
                style="width: 100%">
            <el-table-column
                    label="章节"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.chapter }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="课程ID"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.courseId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="视频URI"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.uri }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="课后习题编号"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.exercise }}</span>
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
        <el-button type="primary" @click="handleAddCourse()" class="additional-button">新增课程<i class="el-icon-upload el-icon--right"/></el-button>

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
                    <el-input v-model="form.exercise" autocomplete="off"/>
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
                    <el-input v-model="form.exercise" autocomplete="off"/>
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
    export default {
        name: "CourseManagement",
        data() {
            return {
                courses: [
                    {
                        chapter: 1,
                        courseId: 1,
                        uri: '1-1',
                        exercise: 1
                    },
                    {
                        chapter: 1,
                        courseId: 2,
                        uri: '1-2',
                        exercise: 2

                    },
                    {
                        chapter: 2,
                        courseId: 1,
                        uri: '2-1',
                        exercise: 3,
                    },
                    {
                        chapter: 2,
                        courseId: 2,
                        uri: '2-2',
                        exercise: 4
                    }
                ],
                editDialogFormVisible: false,
                addDialogFormVisible: false,
                formLabelWidth: "120px",
                form: {
                    chapter: '',
                    courseId: '',
                    uri: '',
                    exercise: ''
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
                alert("课程修改成功")
            }
        }
    }
</script>

<style>
    .additional-button{
        margin-top: 50px;
    }

</style>