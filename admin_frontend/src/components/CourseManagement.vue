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
                    label="URI"
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
                            type="primary"
                            @click="handleAddExercise(scope.$index, scope.row)">添加题目
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
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
                ]
            }
        },
        methods: {
            handleEdit(index, row) {
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            },
            handleAddExercise(index, row) {
                console.log(index, row);
            }
        }
    }
</script>

<style scoped>

</style>