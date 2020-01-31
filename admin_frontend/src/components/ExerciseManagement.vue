<template>
    <div>
        <el-table
                :data="exercises"
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
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.exerciseId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="题目类型"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.type }}</span>
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

        <el-dialog title="题目编辑" :visible.sync="dialogFormVisible">
            <el-form :model="form">
                <el-form-item label="题目ID" :label-width="formLabelWidth">
                    <el-input disabled v-model="form.exerciseId" autocomplete="off"/>
                </el-form-item>
                <el-form-item label="题干" :label-width="formLabelWidth">
                    <el-input v-model="form.content" autocomplete="off"/>
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
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "ExerciseManagement",
        data() {
            return {
                exercises: [
                    {
                        exerciseId: 1,
                        content: "exercise 1",
                        answer: "A",
                        type: "choice4",
                        reason: "the reason why A is correct",
                        choiceA: "choice A",
                        choiceB: "choice B",
                        choiceC: "choice C",
                        choiceD: "choice D",
                    },
                    {
                        exerciseId: 1,
                        content: "exercise 1",
                        answer: "A",
                        type: "choice4",
                        reason: "the reason why A is correct",
                        choiceA: "choice A",
                        choiceB: "choice B",
                        choiceC: "choice C",
                        choiceD: "choice D",
                    },
                    {
                        exerciseId: 1,
                        content: "exercise 1",
                        answer: "A",
                        type: "choice4",
                        reason: "the reason why A is correct",
                        choiceA: "choice A",
                        choiceB: "choice B",
                        choiceC: "choice C",
                        choiceD: "choice D",
                    }
                ],
                dialogFormVisible: false,
                formLabelWidth: '120px',
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
                }
            }
        },
        methods: {
            handleEdit(index, row) {
                this.dialogFormVisible = true;
                for (let key in this.form) {
                    this.form[key] = row[key];
                }
                console.log(index, row);
            },
            handleDelete(index, row) {
                console.log(index, row);
            },
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