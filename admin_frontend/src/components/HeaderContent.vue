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
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-menu-item index="4">
            <a href="https://www.ele.me" target="_blank">订单管理</a>
        </el-menu-item>
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