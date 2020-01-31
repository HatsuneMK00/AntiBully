<template>
    <div>
        <el-table
                :data="helpInfos"
                style="width: 100%">
            <el-table-column
                    label="求助信息ID"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.infoId }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="联系方式"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.contact }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    label="描述"
                    width="240">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.describe }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            type="primary"
                            @click="handleDone(scope.$index, scope.row)">完成
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "HelpInfoManagementMainContent",
        data() {
            return {
                helpInfosHandled: [
                    {
                        infoId: 1,
                        contact: '13113111311',
                        describe: 'i feel bad',
                        status: 'handled'
                    },
                    {
                        infoId: 2,
                        contact: '13113111311',
                        describe: 'i feel bad',
                        status: 'handled'
                    },
                    {
                        infoId: 3,
                        contact: '13113111311',
                        describe: 'i feel bad',
                        status: 'handled'
                    }
                ],
                helpInfosUnhandled: [
                    {
                        infoId: 4,
                        contact: '13113111311',
                        describe: 'i feel bad',
                        status: 'unhandled'
                    },
                    {
                        infoId: 5,
                        contact: '13113111311',
                        describe: 'i feel bad',
                        status: 'unhandled'
                    },
                ],
                helpInfos: []
            }
        },
        created() {
            this.helpInfos = this.helpInfosUnhandled
        },
        watch: {
            /*
            * 监听路由变化 更新helpInfos的内容
            * */
            $route: {
                handler: function(val, oldVal){
                    if (val.params.status === 'unhandled') {
                        this.helpInfos = this.helpInfosUnhandled;
                    } else {
                        this.helpInfos = this.helpInfosHandled;
                    }
                },
                // 深度观察监听
                deep: true
            }
        }
    }
</script>

<style scoped>

</style>