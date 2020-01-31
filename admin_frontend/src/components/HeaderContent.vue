<template>
    <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
            router
            @select="handleSelect"
    >
        <el-menu-item index="1" route="/course">课程管理</el-menu-item>
        <el-menu-item index="2" route="/exercise">题目管理</el-menu-item>
        <el-menu-item index="3" route="/repo">题库管理</el-menu-item>
        <el-menu-item index="4" route="/helpInfo/unhandled">求助信息管理</el-menu-item>
        <el-menu-item index="5" route="/questionnaire">问卷管理</el-menu-item>
    </el-menu>
</template>

<script>
    export default {
        name: "HeaderContent",
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>

<style scoped>

</style>