import Vue from 'vue'
import VueRouter from 'vue-router'
import CourseManagement from "@/components/CourseManagement";
import ExerciseManagement from "@/components/ExerciseManagement";
import RepoManagement from "@/components/RepoManagement";
import HelpInfoManagement from "@/components/HelpInfoManagement";
import QuestionnaireManagement from "@/components/QuestionnaireManagement";
import Statistic from "@/components/Statistic";
import Login from "@/components/Login";
import MainContent from "@/components/MainContent";

Vue.use(VueRouter);

const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/',
        name: "main",
        component: MainContent,
        children: [
            {
                path: '/course',
                name: 'course',
                component: CourseManagement
            },
            {
                path: '/',
                name: 'home',
                redirect: '/course'
            },
            {
                path: '/exercise',
                name: 'exercise',
                component: ExerciseManagement
            },
            {
                path: '/repo',
                name: 'repo',
                component: RepoManagement
            },
            {
                path: '/helpInfo/:status',
                name: 'helpInfo',
                component: HelpInfoManagement,
            },
            {
                path: '/helpInfo',
                name: 'helpInfoInit',
                redirect: '/helpInfo/unhandled',
            },
            {
                path: '/questionnaire',
                name: 'questionnaire',
                component: QuestionnaireManagement
            },
            {
                path: '/statistic',
                name: 'statistic',
                component: Statistic
            }
        ]
    },
];

const router = new VueRouter({
    routes
});

router.beforeEach((to, from, next) => {
    if (to.path === '/login') {
        return next();
    } else {
        let token = window.sessionStorage.getItem("access_token");
        if (token == null) {
            return next('/login');
        } else {
            return next();
        }
    }
});

export default router

