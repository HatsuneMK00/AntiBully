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
    </div>
</template>

<script>
    export default {
        name: "RepoManagement",
        data() {
            return {
                repos: [
                    {
                        repoId: 1,
                        title: 'repo 1',
                        exerciseNum: 30
                    },
                    {
                        repoId: 2,
                        title: 'repo 2',
                        exerciseNum: 30
                    },
                    {
                        repoId: 3,
                        title: 'repo 3',
                        exerciseNum: 30
                    }
                ]
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
                console.log(index, row);
            }
        }
    }
</script>

<style scoped>

</style>