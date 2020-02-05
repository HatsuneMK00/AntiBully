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
    import HeaderContent from "@/components/HeaderContent";

    export default {
        name: "MainContent",
        components: {
            HeaderContent,
        },
        created() {
        },
    }
</script>

<style scoped>

</style>