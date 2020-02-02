<template>
    <div>
        <el-table
                :data="helpInfos.filter(helpInfo => helpInfo.status.toLowerCase() === currentNav.toLowerCase())"
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
                            :disabled = "buttonDisabled"
                            @click="handleDone(scope.$index, scope.row)">完成
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    import url from "@/main";
    import axios from "axios"

    export default {
        name: "HelpInfoManagementMainContent",
        data() {
            return {
                currentNav: 'unhandled',
                buttonDisabled: false,
                helpInfos: []
            }
        },
        watch: {
            /*
            * 监听路由变化 更新currentNav的内容
            * */
            $route: {
                handler: function(val, oldVal){
                    this.currentNav = val.params.status;
                    // 设置button是否可用 已完成的求助信息里button不可用
                    this.buttonDisabled = val.params.status !== 'unhandled';
                },
                // 深度观察监听
                deep: true
            }
        },
        methods: {
            handleDone(index, row) {
                axios.put(
                    url + '/admin/helpInfoHandled/' + row.infoId
                ).then(response => {
                    if (response.data === 1) {
                        row.status = 'handled';
                        console.log("后台求助信息处理成功");
                    } else {
                        console.log("后台返回值" + response.data)
                    }
                }).catch(reason => {
                    console.log(reason);
                });
            }
        },
        mounted() {
            let that = this;
            axios
                .get(url + "/admin/helpInfos")
                .then(function (response) {
                    that.helpInfos = response.data;
                })
        }
    }
</script>

<style scoped>

</style>