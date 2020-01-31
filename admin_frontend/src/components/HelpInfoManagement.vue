<template>
    <div>
        <el-container>
            <el-aside width="200px">
                <el-menu
                        default-active="1"
                        class="el-menu-vertical-demo"
                        router
                >
                    <el-menu-item index="1" route="/helpInfo/unhandled">
                        <span slot="title">未完成</span>
                    </el-menu-item>
                    <el-menu-item index="2" route="/helpInfo/handled">
                        <span slot="title">已完成</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>

            <el-main>
                <help-info-management-main-content/>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import HelpInfoManagementMainContent from "@/components/HelpInfoManagementMainContent";

    export default {
        name: "HelpInfoManagement",
        components: {
            HelpInfoManagementMainContent
        }
    }
</script>

<style scoped>

</style>