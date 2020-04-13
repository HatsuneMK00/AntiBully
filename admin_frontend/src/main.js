import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import router from "@/router";

Vue.config.productionTip = false;

Vue.use(ElementUI);

let url = "http://119.23.188.171:8080";
export default url;

let redirectToLogin = function (vue) {
    vue.push("/login")
};

new Vue({
    render: h => h(App),
    router
}).$mount('#app');
