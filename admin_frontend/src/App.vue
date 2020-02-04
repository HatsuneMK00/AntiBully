<template>
    <div id="app">
        <h1>Anti Bully</h1>
        <router-view/>
    </div>
</template>

<script>
    import SideBarContent from "@/components/SideBarContent";
    import MainContent from "@/components/MainContent";
    import HeaderContent from "@/components/HeaderContent";

    export default {
        name: 'app',
        created() {
            console.log("hello from app")
        }
    }
</script>

<style>
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
