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
    </div>
</template>

<script>
    export default {
        name: "QuestionnaireManagement",
        data() {
            return {
                questionnaires: [
                    {
                        questionnaireId: 1,
                        uri: 'q-1'
                    },
                    {
                        questionnaireId: 2,
                        uri: 'q-2'
                    },
                    {
                        questionnaireId: 3,
                        uri: 'q-3'
                    },
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
        }
    }
</script>

<style scoped>

</style>