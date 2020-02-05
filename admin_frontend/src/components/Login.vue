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
    import {client_id, client_secret, scope} from '@/settings'

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
                let params = new URLSearchParams();
                params.append("username", this.form.username);
                params.append("password", this.form.password);
                params.append("grant_type", "password");
                params.append("scope", scope);
                params.append("client_id", client_id);
                params.append("client_secret", client_secret);
                axios({
                    url: url + '/oauth/token',
                    data: params,
                    method: "post",
                }).then(response => {
                    window.sessionStorage.setItem("access_token", response.data.access_token);
                    that.$router.push("/course")
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