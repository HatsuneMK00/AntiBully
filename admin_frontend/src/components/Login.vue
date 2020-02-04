<template>
    <div>
        <el-container>
            <el-main>
                <el-form :model="form" ref="form" label-width="100px" class="login-form">
                    <el-form-item label="用户名">
                        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="密码">
                        <el-input v-model="form.password" placeholder="请输入密码" type="password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="loginAdmin('ruleForm')">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import axios from 'axios'
    import url from '@/main'

    export default {
        name: "Login",
        data() {
            return {
                form: {
                    username: 'admin',
                    password: '123456'
                },
            }
        },
        methods: {
            loginAdmin() {
                let that = this;
                let params = new FormData();
                params.append("username", this.form.username);
                params.append("password", this.form.password);
                axios({
                    url: url + '/login',
                    data: params,
                    method: "post",
                }).then(response => {
                    console.log(response.data);
                }).catch(reason => {
                    console.log(reason);
                })
            }
        }
    }
</script>

<style>
.login-form{

}
</style>