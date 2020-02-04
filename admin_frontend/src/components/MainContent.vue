<template>
    <div>
        <el-container>
            <el-header height="">
                <!-- Header content -->
                <header-content/>
            </el-header>
            <el-main height="">
                <!-- Main content -->
                <router-view/>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import SideBarContent from "@/components/SideBarContent";
    import HeaderContent from "@/components/HeaderContent";

    export default {
        name: "MainContent",
        components: {
            SideBarContent,
            HeaderContent,
        },
        created() {
        },
    }
</script>

<style scoped>

</style>